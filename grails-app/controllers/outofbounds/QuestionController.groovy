package outofbounds



import static org.springframework.http.HttpStatus.*
import outOfBounds.Configuration;
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class QuestionController {
    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

	def questionService
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
		redirect(uri: "/question/newQuestions")
    }
	
	def newQuestions()
	{
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
		
		render(view: '/question/index',
				model: [ questions: questionService.newQuestions(offset, max), total: Question.count, choice: "newest"])	}
	
	def voteQuestions()
	{
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
		
		render(view: '/question/index',
			model: [ questions: questionService.voteQuestions(offset, max), total: Question.count, choice: "votes" ])
	}

	def unansweredQuestions()
	{
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
		
		render(view: '/question/index',
			model: [ questions: questionService.unansweredQuestions(offset, max), total: Question.count, choice: "unanswered" ])
	}

    def show() {
        def currentLoggedInUser = springSecurityService.getCurrentUser();
		def question = Question.findById(params.question_id)
		questionService.addView(question)

		return [questionInstance: question, currentLoggedInUser: currentLoggedInUser]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def create() {
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def saveQuestion() {
		def user = getAuthenticatedUser()
        def question = questionService.saveQuestion(params.question_title, params.question_text, params.question_tags, user) 
        
		redirect(uri: "/question/show?question_id=${question.id}")
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit() {
        def question = Question.findById(params.question_id)
        respond question
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def update(Question questionInstance) {
        if (questionInstance == null) {
            notFound()
            return
        }

        if (questionInstance.hasErrors()) {
            respond questionInstance.errors, view:'edit'
            return
        }

        questionInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Question.label', default: 'Question'), questionInstance.id])
                redirect questionInstance
            }
            '*'{ respond questionInstance, [status: OK] }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def deleteQuestion(Question questionInstance) {
        def question = Question.findById(params.question_id)
        
        if (question == null) {
            notFound()
            return
        }

        question.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Question.label', default: 'Question'), question.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionInstance.label', default: 'Question'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
