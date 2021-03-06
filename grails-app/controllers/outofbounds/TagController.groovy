package outofbounds



import static org.springframework.http.HttpStatus.*
import outofbounds.Configuration;
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class TagController {
	
	def tagService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        flash.message = flash.message
        redirect action: "popularTags"
    }

    /* Return list of tags ordered by date */    
    def newTags() {
        def tags = tagService.newTags(params.offset?: 0, 
            params.max?: Configuration.NUMBER_TAGS_PER_PAGE)
        getTags("new", tags)
    }

    /* Return list of tags ordered by popularity */    
    def popularTags() {
        def tags = tagService.popularTags(params.offset?: 0, 
            params.max?: Configuration.NUMBER_TAGS_PER_PAGE)
        getTags("popular", tags)
    }

    /* Return list of tags ordered by alphanumeric order */    
    def nameTags() {
        def tags = tagService.nameTags(params.offset?: 0, 
            params.max?: Configuration.NUMBER_TAGS_PER_PAGE)
        getTags("name", tags)
    }

    protected def getTags(name, tags) {
        render(   
            view: '/tag/index',
            model: [ 
                tags: tags,
                total: Tag.count, choice: name, layout: "tag"
            ]
        )   
    }
	

    /* Display a tag with its tagged questions */
    def show() {
        redirect action: 'unansweredQuestions', params: params
    }

    def newestQuestions() {
        def tag = Tag.findById(params.int('tag_id'))
        if (tag == null) {
            notFound()    
            return
        }

        def questions = tagService.newestTaggedQuestions(tag, params.offset?: 0, 
            params.max?: Configuration.NUMBER_POSTS_PER_PAGE)

        displayQuestions(tag, "newest", questions, tag.questions.size())
    }
    def popularQuestions() {
        def tag = Tag.findById(params.int('tag_id'))
        if (tag == null) {
            notFound()    
            return
        }

        def questions = tagService.popularTaggedQuestions(tag, params.offset?: 0, 
            params.max?: Configuration.NUMBER_POSTS_PER_PAGE)

        displayQuestions(tag, "popular", questions, tag.questions.size())
    }
    def unansweredQuestions() {
        def tag = Tag.findById(params.int('tag_id'))
        if (tag == null) {
            notFound()    
            return
        }

        // special case: since we need the total count, we do not use offset/max
        // params when querying the DB
        def questions = tagService.unansweredTaggedQuestions(tag)
        def size = questions.size()

        def offset = params.int('offset') ?: 0
        def max = params.int('max') ?: Configuration.NUMBER_POSTS_PER_PAGE


        // if we asked an invalid page, return to first page
        if (size > 0 && offset >= size) {
            offset = 0
        }

        questions = questions.subList(offset, Math.min(offset + max, size))
        displayQuestions(tag, "unanswered", questions, size)
    }
    protected def displayQuestions(tag, choice, questions, total) {
        render(
            view: '/tag/show',
            model: [ 
                tag: tag,
                questions: questions,
                choice: choice,
                total: total
            ]
        )
    }

    @Secured(['IS_AUTHENTICATED_FULLY', 'ROLE_MODERATOR', 'ROLE_ADMIN'])
    def edit() {
        def tag = Tag.findById(params.int('tag_id'))
        if (tag == null) {
            notFound()    
            return
        }
        
        respond tag
    }

    @Secured(['IS_AUTHENTICATED_FULLY', 'ROLE_MODERATOR', 'ROLE_ADMIN'])
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

        flash.message = message(code: 'post.update_success', args: ['tag'])
        redirect action: "show", params: ['tag_id': tagInstance.id]
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'post.not_found', args: ['tag'])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
