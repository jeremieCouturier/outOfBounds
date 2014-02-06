package outofbounds



import grails.test.mixin.*
import spock.lang.*
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.validation.ValidationException

@TestFor(QuestionController)
@Mock([Question, User, Tag, Post, Role])
class QuestionControllerSpec extends Specification {

    def user

    void setup() {
        defineBeans {
            questionService(QuestionService) { bean ->
                bean.autowire = true
            }
        }

        User.metaClass.encodePassword = { -> }
        QuestionController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', realname: 'realname', email: 'aaa@aa.fr',
            password: 'a', location: 'fr', website: 'google.fr')
        user.save(flush: true, failOnError: true)
    }

    void "Test create new question is OK"() {
        when: "creating new question"
            params.title = 'title very long'
            params.text = 'text'
            params.question_tags = 'cpp grails'
            controller.saveQuestion()
        then: "save it and redirect to its page"
            assertEquals response.redirectedUrl, "/question/show?question_id=1"

            def q = Question.findById(1)
            assertNotNull q
            assertEquals q.title, "title very long"
            assertEquals q.tags.size(), 2
            assertEquals q.user, user
    }

    void "Test create new question missing text is failling"() {
        when: "creating new question with missing text"
            params.title = 'title very long'
            params.text = null
            params.question_tags = 'cpp grails'

            controller.saveQuestion()
        then: "stay on create page"
            assertNull response.redirectedUrl
            assertEquals view, 'create'
            assertNotNull model.questionInstance
            assertTrue model.questionInstance.errors.toString().contains(
                "org.grails.datastore.mapping.validation.ValidationErrors: 1 errors")
            assertTrue model.questionInstance.errors.toString().contains(
                "Field error in object 'outofbounds.Question' on field 'text': rejected value [null];")
    }

    void "Return to index if request is invalid"() {
        when: "accessing an invalid URL"
            params.id = 'YOLLO!' //giving a string instead of an integer
            controller.show()
        then: "go back to index"
            assertEquals response.redirectedUrl, '/question/newestQuestions'
            assertEquals flash.message, 'default.invalid_URL'
    }

    void "Return to index if trying to create a question from URL hardcode"() {
        when: "pasting question creation validation URL in a new tab"
            controller.saveQuestion()

        then: "go back to index with custom error"
            assertEquals response.redirectedUrl, '/question/newestQuestions'
            assertEquals flash.message, 'question.force_create_URL_issue'
    }

}
