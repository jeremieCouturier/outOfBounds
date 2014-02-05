package outofbounds



import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
@Mock([Tag, Question])
class TagControllerSpec extends Specification {

      def user
      def springSecurityService
      def tags
      def question

    void setup() {
        springSecurityService = new Object()
        springSecurityService.metaClass.getCurrentUser = { user }


        def tag1 = new Tag(name: "grails", description: "")
        tag1.save(flush: true)
        def tag2 = new Tag(name: "groovy", description: "")
        tag2.save(flush: true)
        def tag3 = new Tag(name: "cpp", description: "")
        tag3.save(flush: true)

        tags = [tag1, tag2, tag3]
        
        question = new Question(title: "a", text: "a", tags: tags, user: user)
        question.save(flush: true)
    }

    void "Test the index action returns most popular tags"() {
        when: 'Trying to access index'
            controller.index()
        then: 'Content should contains popular tags'

    }
}
