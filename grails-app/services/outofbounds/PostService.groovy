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

	
	def upVote(int post_id, User user)
	{
		def post = Post.findById(post_id)

		def no_own_post = user.posts.find({obj -> obj == post})
		if( no_own_post == null || no_own_post == []) 
		{			
				
			def upVote = user.upVoted.find({obj -> obj.post == post})
			if(upVote == null  || upVote == [])
			{
				def downVote = user.downVoted.find({obj -> obj.post == post})
				if( downVote != null && downVote != []) 
				{
					post.mark = post.mark + 2
					user.removeFromDownVoted(downVote)
					user.save(failOnError: true)
				}
				else
					post.mark = post.mark + 1				
				user.addToUpVoted(new UpVote(
									post:post
									
								).save(failOnError: true))
			}
			else
			{
				post.mark = post.mark - 1
				user.removeFromUpVoted(upVote)
				user.save(failOnError: true)
			}
			post.save(failOnError: true)			
		}
		return post		
	}
	
	def downVote(int post_id, User user)
	{
		def post = Post.findById(post_id) 

		def no_own_post = user.posts.find({obj -> obj == post})
		if( no_own_post == null || no_own_post == []) 
		{
			def downVote = user.downVoted.find({obj -> obj.post == post})
			if( downVote == null || downVote == []) 
			{
				def upVote = user.upVoted.find({obj -> obj.post == post})
				if( upVote != null && upVote != []) 
				{
					post.mark = post.mark - 2
					user.removeFromUpVoted(upVote)
					user.save(failOnError: true)
				}
				else
					post.mark = post.mark - 1
				user.addToDownVoted(new DownVote(
									post:post
								).save(failOnError: true))
			}
			else
			{
				post.mark = post.mark + 1
				user.removeFromDownVoted(downVote)
				user.save(failOnError: true)
			}
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
