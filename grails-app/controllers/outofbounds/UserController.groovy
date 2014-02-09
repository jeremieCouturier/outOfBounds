package outofbounds



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import com.megatome.grails.RecaptchaService

@Transactional(readOnly = true)
class UserController {
    def springSecurityService
	def userService
    
    RecaptchaService recaptchaService 

    static allowedMethods = [save: "POST"]

    /*def index() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_TAGS_PER_PAGE

		render(
            view: 'index',
            model: [ 
                users: userService.newUsers(offset, max), 
                total: User.count, choice: "new", layout: "user"
            ]
        )	
    }*/
	
	def index() {
		flash.message = flash.message
		redirect action: "newUser"
	}
	
	def reputationUser() {
		def users = userService.reputationUser(params.offset?: 0,
			params.max?: Configuration.NUMBER_TAGS_PER_PAGE)
		getUsers("reputation", users)
	}

	def usernameUser() {
		def users = userService.usernameUser(params.offset?: 0,
			params.max?: Configuration.NUMBER_TAGS_PER_PAGE)
		getUsers("username", users)
	}
	
	def newUser() {
		def users = userService.newUser(params.offset?: 0,
			params.max?: Configuration.NUMBER_TAGS_PER_PAGE)
		getUsers("new", users)
	}
	
	protected def getUsers(name, users) {
		render(
			view: '/user/index',
			model: [
				users: users,
				total: User.count, choice: name, layout: "user"
			]
		)
	}
	
		
    def show() {

		def user_id = params.user_id?: getAuthenticatedUser()?.id
    
        if (user_id == null) {
            notFound()
            return
        }

		redirect (
			action: "userQuestions",
			params: [user_id: user_id]
		)
    }
	
	def userQuestions() {
		def user = User.findById(params.int('user_id'))
		
		if (user == null) {
			notFound()
			return
		}
		
		def questions = userService.userQuestions(user)
		
		render(
			view: 'show',
			model: [
				userInstance: user,
				questions: questions,
				choice: "questions"
			]
		)
	}
	
	def userAnswers() {
		def user = User.findById(params.int('user_id'))
		
		if (user == null) {
			notFound()
			return
		}
		
		def questions = userService.userAnswers(user)
		
		render(
			view: 'show',
			model: [
				userInstance: user,
				questions: questions,
				choice: "answers"
			]
		)
	}
	
	def userBadges() {
		def user = User.findById(params.user_id)
		
		if (user == null) {
			notFound()
			return
		}
		
		def badges = user?.badges
		
		render(
			view: 'show',
			model: [
				userInstance: user,
				badges: badges,
				choice: "badges"
			]
		)
	}

	def userTags() {
		def user = User.findById(params.user_id)
		if (user == null) {
			notFound()
			return
		}

		def tags = new ArrayList<Tag>()
		for (Question question : userService.userQuestions(user)) {
			for (Tag tag : question.tags)
				tags << tag
		}
		
		render(
			view: 'show',
			model: [
				userInstance: user,
				tags: tags,
				choice: "tags"
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
                redirect controller: 'login', action: 'index'
            }
            '*' { respond userInstance, [status: CREATED] }
            }
        }
        else {
            respond userInstance.errors, view:'create'
            return
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'post.not_found', args: ['user'])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
