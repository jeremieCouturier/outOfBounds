package outofbounds



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class CommentController {

    def springSecurityService
	def PostService
	def CommentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Comment.list(params), model:[commentInstanceCount: Comment.count()]
    }

    def show(Comment commentInstance) {
        respond commentInstance
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    @Transactional
    def save(Comment commentInstance) {
        if (commentInstance == null) {
            notFound()
            return
        }

        if (commentInstance.hasErrors()) {
            respond commentInstance.errors, view:'create'
            return
        }

        commentInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comment.Comment'), commentInstance.id])
                redirect commentInstance
            }
            '*' { respond commentInstance, [status: CREATED] }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def edit() {
        def comment = Comment.findById(params.int('comment_id'))
        if (comment == null) {
            notFound()
            return
        }

        if (comment.canUserEditPost(getAuthenticatedUser())) {
            [commentInstance: comment, questionInstance: PostService.findQuestionPost(comment)]
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
		def question = PostService.findQuestionPost(comment)

		if (comment && comment.canUserDeletePost(getAuthenticatedUser())) {
			CommentService.delete(comment)
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
