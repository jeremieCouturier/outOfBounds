package outofbounds



import grails.test.mixin.*
import spock.lang.*
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.validation.ValidationException

@TestFor(QuestionController)
@Mock([Question, User, Tag, Post])
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
        user.save(flush: true)
    }

    void "Test create new question is OK"() {
        when: "creating new question"
            params.title = 'title very long'
            params.text = 'text'
            params.question_tags = 'cpp grails'
            controller.saveQuestion()
        then: "save it and redirect to its page"
            assert response.redirectedUrl == "/question/show?question_id=1"

            def q = Question.findById(1)
            assert q != null
            assert q.title == "title very long"
            assert q.tags.size() == 2
            assert q.user == user
    }

    void "Test create new question missing text is failling"() {
        when: "creating new question with missing text"
            params.title = 'title very long'
            params.text = null
            params.question_tags = 'cpp grails'

            shouldFail(ValidationException) {
                controller.saveQuestion()
            }
        then: "stays on create page"
            assert response.redirectedUrl == null
    }


}
