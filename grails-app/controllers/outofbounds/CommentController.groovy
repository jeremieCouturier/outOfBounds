package outofbounds



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class CommentController {

    def springSecurityService
	def postService
	def commentService

    static allowedMethods = [update: "PUT"]

    def show(Comment commentInstance) {
        respond commentInstance
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def edit() {
        def comment = Comment.findById(params.int('comment_id'))
        if (comment == null) {
            notFound()
            return
        }

        if (comment.canUserEditPost(getAuthenticatedUser())) {
            [commentInstance: comment, questionInstance: postService.findQuestionPost(comment)]
        } else {
            flash.message = message(code: 'post.edit_not_authorized', args: ['comment'])
            Post p = Post.findById(comment.post.id)
            if (p instanceof Answer) {
                p = p.question
            }
            redirect controller:'question', action:'show', params: ['question_id': p.id]
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    @Transactional
    def update(Comment commentInstance) {
        if (commentInstance == null) {
            notFound()
            return
        }

        if (commentInstance.hasErrors()) {
            respond commentInstance.errors, view:'edit'
            return
        }

        commentInstance.save flush:true

        // go back to the question
        flash.message = message(code: 'post.update_success', args: ['comment'])
        Post p = Post.findById(commentInstance.post.id)
        if (p instanceof Answer) {
            p = p.question
        }
        redirect controller:'question', action:'show', params: ['question_id': p.id]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    @Transactional
    def deleteComment() {
		def comment = Comment.findById(params.int('comment_id'))
		def question = postService.findQuestionPost(comment)

		if (comment && comment.canUserDeletePost(getAuthenticatedUser())) {
			commentService.delete(comment)
			flash.message = message(code: 'post.delete_success', args: ["comment"])
		} else {
			flash.message = message(code: 'post.delete_not_authorized', args: ["comment"])
		}
        redirect controller: 'question', action:'show', params: ['question_id': question.id]
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'post.not_found', args: ['comment'])
                redirect controller: "question", action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
