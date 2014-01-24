package outofbounds



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import com.megatome.grails.RecaptchaService

@Transactional(readOnly = true)
class UserController {
    def springSecurityService
    
     RecaptchaService recaptchaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
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
                flash.message = message(code: 'default.created.message', args: [message(code: 'userInstance.label', default: 'User'), userInstance.id])
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
        print userInstance
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
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
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userInstance.label', default: 'User'), params.id])
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
