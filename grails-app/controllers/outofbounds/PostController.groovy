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
		
        redirect controller: 'question', action:'show', params: ['question_id': question.id]
	}


	@Secured(['IS_AUTHENTICATED_FULLY'])	
	def upVote()
	{
		def user = getAuthenticatedUser()
		Post post = PostService.upVote(Integer.parseInt(params.post_id), user)

		Question question = PostService.findQuestionPost(post)

        redirect controller: 'question', action:'show', params: ['question_id': question.id]
	}
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def downVote()
	{
		def user = getAuthenticatedUser()
		Post post = PostService.downVote(Integer.parseInt(params.post_id), user)
		
		Question question = PostService.findQuestionPost(post)
		
        redirect controller: 'question', action:'show', params: ['question_id': question.id]
	}
}
