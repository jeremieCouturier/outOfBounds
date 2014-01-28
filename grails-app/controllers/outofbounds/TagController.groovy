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

    def newTags() {
        def tags = TagService.newTags(params? params.offset : 0, 
            params? params.max : Configuration.NUMBER_ITEM_PER_PAGE*4)
        getTags("new", tags)
    }

    def popularTags() {
        def tags = TagService.popularTags(params? params.offset : 0, 
            params? params.max : Configuration.NUMBER_ITEM_PER_PAGE*4)
        getTags("popular", tags)
    }

    def nameTags() {
        def tags = TagService.nameTags(params? params.offset : 0, 
            params? params.max : Configuration.NUMBER_ITEM_PER_PAGE*4)
        getTags("name", tags)
    }

    def getTags(name, tags) {
        render(   
            view: '/tag/index',
            model: [ 
                tags: tags,
                total: Tag.count, choice: name, layout: "tag"
            ]
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

    def show() {
        render(
            view: '/tag/show',
            model: [ 
                tag: Tag.findById(params.id)
            ]
        )
    }
}
