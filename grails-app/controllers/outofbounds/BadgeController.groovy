package outofbounds

import static org.springframework.http.HttpStatus.*
import outofbounds.Configuration;
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class BadgeController {

    def badgeService

    def index() {
        //redirect is flushing flash dictionnary.. so we reset it (I agree,
        //this line is weird)
        flash.message = flash.message

        redirect action: "badges"
    }

    def show(Badge badgeInstance) {
        def badge = Badge.findById(params.int('badge_id'))
        
        //if no question selected, go back to index
        if (badge == null) {
            redirect action: "badges"
            return
        }

        return [badgeInstance: badge]
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
