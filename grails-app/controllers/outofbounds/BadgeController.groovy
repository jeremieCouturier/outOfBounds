package outofbounds

import static org.springframework.http.HttpStatus.*
import outofbounds.Configuration;
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class BadgeController {

    def springSecurityService
    def badgeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        //redirect is flushing flash dictionnary.. so we reset it (I agree,
        //this line is weird)
        flash.message = flash.message

        redirect action: "badges"
    }

    def show(Badge badgeInstance) {
        respond badgeInstance
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    @Transactional
    def save(Badge badgeInstance) {
        if (badgeInstance == null) {
            notFound()
            return
        }

        if (badgeInstance.hasErrors()) {
            respond badgeInstance.errors, view:'create'
            return
        }

        badgeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'badgeInstance.label', default: 'Badge'), badgeInstance.id])
                redirect badgesInstance
            }
            '*' { respond badgeInstance, [status: CREATED] }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    def edit(Badge badgeInstance) {
        respond badgeInstance
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    @Transactional
    def update(Badge badgeInstance) {
        if (badgeInstance == null) {
            notFound()
            return
        }

        if (badgeInstance.hasErrors()) {
            respond badgeInstance.errors, view:'edit'
            return
        }

        badgeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Badge.label', default: 'Badge'), badgeInstance.id])
                redirect badgeInstance
            }
            '*'{ respond badgeInstance, [status: OK] }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])    
    @Transactional
    def delete(Badge badgeInstance) {

        if (badgeInstance == null) {
            notFound()
            return
        }

        badgeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Bagde.label', default: 'Badge'), badgeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'badgeInstance.label', default: 'Badge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    /**
     * Get the list of badges (filtered on medals)
     */
    def badges() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
        def medal = params?.medal ?: "all"
        
        def badges
        
        switch (medal) {
            case "bronze" : badges = Badge.findAllByMedal("Bronze"); break
            case "silver" : badges = Badge.findAllByMedal("Silver"); break
            case "gold" : badges = Badge.findAllByMedal("Gold"); break
            default : badges = Badge.all; break
        }
        
        render(
            view: '/badge/index',
            model: [
                badges: badges,
                total: badges?.count ?: 0, choice: medal, layout: "badge"
            ]
        )
    }

}
