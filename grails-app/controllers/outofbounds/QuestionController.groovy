package outofbounds



import static org.springframework.http.HttpStatus.*
import outofbounds.Configuration
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
		def max = params?.max ?: Configuration.NUMBER_POSTS_PER_PAGE
        
		render(   
            view: 'index',
            model: [ 
                questions: questionService.newestQuestions(offset, max), 
                total: Question.count, choice: "newest", layout: "question"
            ]
        )	
    }
	
	def voteQuestions() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_POSTS_PER_PAGE
		
		render(   
            view: 'index',
            model: [ 
                questions: questionService.voteQuestions(offset, max), 
                total: Question.count, choice: "votes", layout: "question"
            ]
        )
	}

	def unansweredQuestions() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_POSTS_PER_PAGE
		
		render(
            view: 'index',
			model: [ 
                questions: questionService.unansweredQuestions(offset, max), 
                total: Question.count, choice: "unanswered", layout: "question"
            ]
        )
	}

    def show() {
        def currentLoggedInUser = getAuthenticatedUser();
		def question = Question.findById(params.int('question_id'))
        
        //if no question selected, go back to index
        if (question == null) {
            flash.message = message(code: 'default.invalid_URL')
            index()
            return
        }

		questionService.addView(question)
		return [questionInstance: question, currentLoggedInUser: currentLoggedInUser]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def create() {
		render(
			view: 'create',
			model: [ layout: "ask" ]
		)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def saveQuestion() {
		def user = getAuthenticatedUser()

        if (params.title == null) {
            flash.message = message(code: 'question.force_create_URL_issue')
            index()
            return
        }

        def question = questionService.saveQuestion(params.title, 
            params.text, params.question_tags, user) 
        
        if (question == null || question.id == null) {
            respond(
                question.errors, 
                view:'create',
                model: [ tags: params.question_tags]
            )
            return
        }

        redirect action:'show', params: ['question_id': question.id]
	}

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit() {
        def question = Question.findById(params.int('question_id'))
        if (question == null) {
            notFound()
            return
        }

        if (question.canUserEditPost(getAuthenticatedUser())) {
            respond(
                question,
                model: [tags: question.tags?.sort{a,b-> a.creationDate.compareTo(b.creationDate)}*.name?.join(" ")]
            )
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

        flash.message = message(code: 'default.updated.message', args: [message(code: 'question.Question'), questionInstance.id])

        redirect action:'show', params: ['question_id': questionInstance.id]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    @Transactional
    def deleteQuestion() {
        def question = Question.findById(params.int('question_id'))

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
            redirect action:"show", params: ['question_id': question.id]
        }
    }


    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def accept() {
        def answer = Answer.findById(params.int('answer_id'))
        
        if (answer == null) {
            notFound()
            return
        }
        def question = answer.question

        changeCorrectAnswer(question, answer)
    }
    
    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def unaccept() {
        def answer = Answer.findById(params.int('answer_id'))

        if (answer == null) {
            notFound()
            return
        }
        def question = answer.question

        changeCorrectAnswer(question, null)
    }

    // When owner of question select a/another answer as the 'correct' answer
    protected void changeCorrectAnswer(Question question, Answer newCorrect) {
        if (question.user != getAuthenticatedUser()) {
            flash.message = message(code: 'question.accept_answer_error')
            redirect action:"show", params: ['question_id': question.id]
            return
        }

        question.correctAnswer = newCorrect
        question.save(failOnError: true)
        redirect action:'show', params: ['question_id': question.id]
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
