package outofbounds



import static org.springframework.http.HttpStatus.*
import outOfBounds.Configuration;
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

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
	
	/*def unansweredTags() {
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
	}*/

    def show() {
        def tag = Tag.findById(params.tag_id)
        
        if (tag == null) {
            redirect action: "index"
            return
        }

        def questions = TagService.taggedQuestions(tag, params? params.offset : 0, 
            params? params.max : Configuration.NUMBER_ITEM_PER_PAGE)

        render(
            view: '/tag/show',
            model: [ 
                tag: tag,
                questions: questions
            ]
        )
    }

    @Secured(['IS_AUTHENTICATED_FULLY', 'ROLE_MODERATOR', 'ROLE_ADMIN'])
    def edit() {
        def tag = Tag.findById(params.tag_id)
        if (tag == null) {
            redirect action:'show', tag_id:params.tag_id
            return
        }
        
//        if (tag.canUserEditPost(getAuthenticatedUser())) {
            respond tag
  //      } else {
    //        flash.message = message(code: 'post.edit_not_authorized', args: ['tag'])
      //      redirect action:show tag_id:params.tag_id
       // }
    }

}
