package outofbounds



import static org.springframework.http.HttpStatus.*
import outOfBounds.Configuration;
import grails.transaction.Transactional

import com.megatome.grails.RecaptchaService

@Transactional(readOnly = true)
class UserController {
    def springSecurityService
	def UserService
    
    RecaptchaService recaptchaService 

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect action: "newUsers"
    }
	
	def newUsers() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE*4

		render(   
            view: '/user/index',
            model: [ 
                users: UserService.newUsers(offset, max), 
                total: User.count, choice: "new", layout: "user"
            ]
        )	
    }
	
	def login() {
		
	}
	
    def show() {		
		def user_id = params?.user_id?:getAuthenticatedUser().id
		redirect (
			action: "userQuestions",
			params: [user_id: user_id]
		)
    }
	
	def userQuestions() {
		def user = User.findById(params.user_id)
		def questions = UserService.userQuestions(user)
		
		render(
			view: '/user/show',
			model: [
				userInstance: user,
				questions: questions,
				choice: "questions"
			]
		)
	}
	
	def userAnswers() {
		def user = User.findById(params.user_id)
		def questions = UserService.userAnswers(user)
		
		render(
			view: '/user/show',
			model: [
				userInstance: user,
				questions: questions,
				choice: "answers"
			]
		)
	}


    def create() {
        

   
        def newUser = new User(
            username: params.username,
            password: params.password,
            enabled: true,
            accountExpired: false,
            accountLocked: false,
            passwordExpired: false
        )
        


        respond newUser
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        if(recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params) && userInstance.save(flush:true)) {
            recaptchaService.cleanUp(session)
            
            def userRole = Role.findByAuthority("ROLE_USER")?: 
            new Role(authority:"ROLE_USER").save(failOnError:true)
            def adminRole = Role.findByAuthority("ROLE_ADMIN") ?: 
            new Role(authority:"ROLE_ADMIN").save(failOnError:true)

            UserRole.create userInstance, userRole

            request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.User'), userInstance.id])
                //redirect userInstance
                redirect(uri: "")
            }
            '*' { respond userInstance, [status: CREATED] }
            }
        }
        else {
            respond userInstance.errors, view:'create'
            return
        }
   
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }
        UserRole.delete userInstance, userRole, flush:true

        userInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def profile(User userInstance) {
        userInstance = springSecurityService.getCurrentUser();
        respond userInstance, view:'profile'
    } 
}
