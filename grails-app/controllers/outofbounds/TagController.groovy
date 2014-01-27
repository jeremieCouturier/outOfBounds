package outofbounds



import static org.springframework.http.HttpStatus.*
import outOfBounds.Configuration;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TagController {
	
	def TagService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect action: "popularTags"
    }
	
	def popularTags() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE*4

		render(   
            view: '/tag/index',
            model: [ 
                tags: TagService.popularTags(offset, max), 
                total: Tag.count, choice: "popular", layout: "tag"
            ]
        )	
    }
	
	

    def show() {
		def tag_id = params.tag_id
		redirect (
			action: "unansweredTags",
			params: [tag_id: tag_id]
		)
    }
	
	def unansweredTags() {
		def offset = params?.offset ?: 0
		def max = params?.max ?: Configuration.NUMBER_ITEM_PER_PAGE
		def tag = Tag.findById(params.tag_id)
		def questions = TagService.unansweredTags(tag)

		render(
			view: '/tag/show',
			model: [
				questions: questions.subList(offset, Math.min(offset + max, questions.size())),
				total: questions.size(), choice: "unanswered", layout: "tag"
			]
		)
	}

    def create() {
        respond new Tag(params)
    }

    @Transactional
    def save(Tag tagInstance) {
        if (tagInstance == null) {
            notFound()
            return
        }

        if (tagInstance.hasErrors()) {
            respond tagInstance.errors, view:'create'
            return
        }

        tagInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tagInstance.label', default: 'Tag'), tagInstance.id])
                redirect tagInstance
            }
            '*' { respond tagInstance, [status: CREATED] }
        }
    }

    def edit(Tag tagInstance) {
        respond tagInstance
    }

    @Transactional
    def update(Tag tagInstance) {
        if (tagInstance == null) {
            notFound()
            return
        }

        if (tagInstance.hasErrors()) {
            respond tagInstance.errors, view:'edit'
            return
        }

        tagInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Tag.label', default: 'Tag'), tagInstance.id])
                redirect tagInstance
            }
            '*'{ respond tagInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Tag tagInstance) {

        if (tagInstance == null) {
            notFound()
            return
        }

        tagInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tag.label', default: 'Tag'), tagInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tagInstance.label', default: 'Tag'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
