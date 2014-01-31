package outofbounds


import outofbounds.Post

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class AnswerController {
	
	def AnswerService

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Answer.list(params), model:[answerInstanceCount: Answer.count()]
    }

    def show(Answer answerInstance) {
        respond answerInstance
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def create() {
		def user = getAuthenticatedUser()
		def answer = AnswerService.create(Integer.parseInt(params.id), params.text, user)
		
        redirect controller: 'question', action:'show', params: ['question_id': answer.question.id]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    @Transactional
    def save(Answer answerInstance) {
        if (answerInstance == null) {
            notFound()
            return
        }

        if (answerInstance.hasErrors()) {
            respond answerInstance.errors, view:'create'
            return
        }

        answerInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'answer.Answer'), answerInstance.id])
                redirect answerInstance
            }
            '*' { respond answerInstance, [status: CREATED] }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def edit() {
		def answer = Answer.findById(params.answer_id)
		def question = answer.question

		return [questionInstance: question, answerInstance: answer]
    }
	
    @Secured(['IS_AUTHENTICATED_FULLY'])    
	def updateAnswer() {
		def answer = AnswerService.updateAnswer(Integer.parseInt(params.id), params.answer_text)
		
        redirect controller: 'question', action:'show', params: ['question_id': answer.question.id]
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def deleteAnswer() {

        def answer = Answer.findById(params.answer_id)
        def question = answer.question      
		if (answer && answer.canUserDeletePost(getAuthenticatedUser())) {
            AnswerService.delete(answer)
            flash.message = message(code: 'post.delete_success', args: ["answer"])
        } else {
            flash.message = message(code: 'post.delete_not_authorized', args: ["answer"])
        }
       redirect controller: 'question', action: 'show', params: ['question_id': question.id]
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.Answer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
