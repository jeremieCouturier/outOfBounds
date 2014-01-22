package outofbounds

import grails.transaction.Transactional

@Transactional
class PostService {

    def serviceMethod() {

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
