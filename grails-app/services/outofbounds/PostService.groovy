package outofbounds

import grails.transaction.Transactional

@Transactional
class PostService {

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
	
	def upVote(int post_id)
	{
		def post = Post.findById(post_id)
		
		post.mark = post.mark + 1;
		post.save(failOnError: true)
	}
	
	def downVote(int post_id)
	{
		def post = Post.findById(post_id)
		
		post.mark = post.mark - 1;
		post.save(failOnError: true)
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
