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
		def post = Post.findById(Integer.parseInt(params.post_id))

		def no_own_post = user.posts.find({obj -> obj == post})
		if( no_own_post == null || no_own_post == []) 
			post = PostService.upVote(post, user)
		else
		{
			flash.error = message(code:"post.vote_error")
			flash.args = "[" + post.getClass() + ", " + params.post_id + "]"
		}
			
		
		Question question = PostService.findQuestionPost(post)

        redirect controller: 'question', action:'show', params: ['question_id': question.id]
	}
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def downVote()
	{
		def user = getAuthenticatedUser()
		def post = Post.findById(Integer.parseInt(params.post_id))
		def no_own_post = user.posts.find({obj -> obj == post})
		if( no_own_post == null || no_own_post == []) 
			post = PostService.downVote(post, user)
		else
		{
			flash.error = message(code:"post.vote_error")
			flash.args = "[" + post.getClass() + ", " + params.post_id + "]"
		}
			
		
		Question question = PostService.findQuestionPost(post)
		
        redirect controller: 'question', action:'show', params: ['question_id': question.id]
	}
}
