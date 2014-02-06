package outofbounds


import outofbounds.Post

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class AnswerController {
	
	def answerService

    def springSecurityService

    static allowedMethods = [update: "PUT", delete: "DELETE"]

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def create() {
		def user = getAuthenticatedUser()

        if (params.text != null && params.text.size() != 0) {
        	def answer = answerService.create(params.int('id'), params.text, user)

            // question does not exist!
            if (answer == null) {
                notFound()
                return
            }
        } else {
            flash.message = message(code: "answer.text.size.toosmall")
        }

		
        redirect controller: 'question', action:'show', params: ['question_id': params.int('id')]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def edit() {
		def answer = Answer.findById(params.int('answer_id'))
		def question = answer.question
        def user = getAuthenticatedUser()
        if (answer.canUserEditPost(user)) {
            return [questionInstance: question, answerInstance: answer]
        } else {
            flash.message = message(code: 'post.edit_not_authorized', args: ["answer"])
            redirect controller: 'question', action: 'show', params: ['question_id': question.id]
        }
    }
	
    @Secured(['IS_AUTHENTICATED_FULLY'])    
	def updateAnswer() {
		def answer = answerService.updateAnswer(Integer.parseInt(params.id), params.answer_text)
		
        redirect controller: 'question', action:'show', params: ['question_id': answer.question.id]
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def deleteAnswer() {

        def answer = Answer.findById(params.int('answer_id'))
        if (answer == null) {
            notFound()
            return
        }

        def question = answer.question      
        if (answer && answer.canUserDeletePost(getAuthenticatedUser())) {
            answerService.delete(answer)
            flash.message = message(code: 'post.delete_success', args: ["answer"])
        } else {
            flash.message = message(code: 'post.delete_not_authorized', args: ["answer"])
        }
       redirect controller: 'question', action: 'show', params: ['question_id': question.id]
    }

    protected void notFound() {
        flash.message = message(code: "answer.question_404")
        redirect controller: 'question', action:'index'
    }
}
