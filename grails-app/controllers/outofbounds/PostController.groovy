package outofbounds

import grails.plugin.springsecurity.annotation.Secured

class PostController {
	
	def PostService
	
	def springSecurityService

    def index() { }
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def addComment()
	{
		def user = getAuthenticatedUser()
		def post = PostService.addComment(Integer.parseInt(params.id), params.text, user)
		
		Question question = PostService.findQuestionPost(post)
		
		redirect(uri: "/question/show?question_id=${question.id}")
	}


	@Secured(['IS_AUTHENTICATED_FULLY'])	
	def upVote()
	{
		Post post = PostService.upVote(Integer.parseInt(params.post_id))
		
		Question question = PostService.findQuestionPost(post)
		
		redirect(uri: "/question/show?question_id=${question.id}")
	}
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def downVote()
	{
		Post post = PostService.downVote(Integer.parseInt(params.post_id))
		
		Question question = PostService.findQuestionPost(post)
		
		redirect(uri: "/question/show?question_id=${question.id}")
	}
}
