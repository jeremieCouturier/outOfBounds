package outofbounds

import grails.transaction.Transactional

@Transactional
class PostService {

	def UserService

    def serviceMethod() {

    }
	
	def addComment(int post_id, String text, User user)
	{
		def post = Post.findById(post_id)
		
		Comment comment = new Comment(
			post:post,
			text:text,
			user:user
		).save(failOnError: true)

		return comment
	}

	
	def upVote(Post post, User user)
	{
		def upVote = user.upVoted.find({obj -> obj.post == post})
		if(upVote == null  || upVote == [])
		{
			def downVote = user.downVoted.find({obj -> obj.post == post})
			if( downVote != null && downVote != []) 
			{
				user.removeFromDownVoted(downVote).save(failOnError: true)
			}			
			else
			{			
				user.addToUpVoted(new UpVote(
									post:post									
									).save(failOnError: true))
			}
			post.mark = post.mark + 1
			post.save(failOnError: true)
		}
		return post						
	}
	
	def downVote(Post post, User user)
	{		
		def downVote = user.downVoted.find({obj -> obj.post == post})
		if( downVote == null || downVote == []) 
		{
			def upVote = user.upVoted.find({obj -> obj.post == post})
			if( upVote != null && upVote != []) 
			{
				user.removeFromUpVoted(upVote).save(failOnError: true)
			}
			else
			{
				user.addToDownVoted(new DownVote(
								post:post
								).save(failOnError: true))
			}
			post.mark = post.mark - 1
			post.save(failOnError: true)			
		}		
		return post
	}
	
	def findQuestionPost(Post post)
	{
		Question question = null
		
		if (post.instanceOf(Question))
			question = post
		else if (post.instanceOf(Answer))
			question = post.question
		else
			question = findQuestionPost(post.post)
			
		return question
	}
}
