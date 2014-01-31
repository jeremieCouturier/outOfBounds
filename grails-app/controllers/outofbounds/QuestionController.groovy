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
        //redirect is flushing flash dictionnary.. so we reset it (I agree,
        //this line is weird)
        flash.message = flash.message

        redirect action: "newestQuestions"
    }
	
	def newestQuestions() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
        
		render(   
            view: '/question/index',
            model: [ 
                questions: questionService.newestQuestions(offset, max), 
                total: Question.count, choice: "newest", layout: "question"
            ]
        )	
    }
	
	def voteQuestions() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
		
		render(   
            view: '/question/index',
            model: [ 
                questions: questionService.voteQuestions(offset, max), 
                total: Question.count, choice: "votes", layout: "question"
            ]
        )
	}

	def unansweredQuestions() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
		
		render(
            view: '/question/index',
			model: [ 
                questions: questionService.unansweredQuestions(offset, max), 
                total: Question.count, choice: "unanswered", layout: "question"
            ]
        )
	}

    def show() {
        def currentLoggedInUser = springSecurityService.getCurrentUser();
		def question = Question.findById(params.question_id)
        
        //if no question selected, go back to index
        if (question == null) {
            index()
            return
        }

		questionService.addView(question)
		return [questionInstance: question, currentLoggedInUser: currentLoggedInUser]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def create() {
		render(
			view: '/question/create',
			model: [ layout: "ask" ]
		)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def saveQuestion() {
		def user = getAuthenticatedUser()
        def question = questionService.saveQuestion(params.title, 
            params.text, params.question_tags, user) 
        
        if (question == null || question.id == null) {
            respond question.errors, view:'create'
            return
        }

        redirect action:'show', params: ['question_id': question.id]
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit() {
        def question = Question.findById(params.question_id)
        if (question == null) {
            notFound()
            return
        }

        if (question.canUserEditPost(getAuthenticatedUser())) {
            respond question
        } else {
            flash.message = message(code: 'post.edit_not_authorized', args: ['question'])
            redirect action:'show', params: ['question_id': question.id]
        }
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
        // reset tags
        questionService.setTags(params.question_tags, questionInstance)

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.Question'), questionInstance.id])
                redirect questionInstance
            }
            '*'{ respond questionInstance, [status: OK] }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def deleteQuestion() {
        def question = Question.findById(params.question_id)

        if (question == null) {
            notFound()
            return
        }

        if (question && question.canUserDeletePost(getAuthenticatedUser())) {
            questionService.deleteQuestion(question)
            flash.message = message(code: 'post.delete_success', args: ['question'])
            redirect action:"index"
        } else {
            flash.message = message(code: 'post.delete_not_authorized', args: ['question'])
            redirect action:"show", params: question.id
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'post.not_found', args: ['question'])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
