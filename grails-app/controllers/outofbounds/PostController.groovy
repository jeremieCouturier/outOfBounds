package outofbounds

class PostController {
	
	def PostService

    def index() { }
	
	def upVote()
	{
		Post post = PostService.upVote(Integer.parseInt(params.post_id))
		
		Question question = PostService.findQuestionPost(post)
		
		redirect(uri: "/question/show?question_id=${question.id}")
	}
	
	def downVote()
	{
		Post post = PostService.downVote(Integer.parseInt(params.post_id))
		
		Question question = PostService.findQuestionPost(post)
		
		redirect(uri: "/question/show?question_id=${question.id}")
	}
}
