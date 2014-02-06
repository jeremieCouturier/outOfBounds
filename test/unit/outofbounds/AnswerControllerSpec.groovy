package outofbounds



import grails.test.mixin.*
import spock.lang.*

@TestFor(AnswerController)
@Mock([Answer, User, Post, Question, Role, UserRole])
class AnswerControllerSpec extends Specification {
    def user
    def question
    def answer

    void setup() {
        defineBeans {
            answerService(AnswerService) { bean ->
                bean.autowire = true
            }
        }

        User.metaClass.encodePassword = { -> }
        AnswerController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', password: 'a')
        user.save(flush: true, failOnError: true)

        question = new Question(title: "title very long", text: "b", tags: [], user: user)
        question.save(flush: true, failOnError: true)

        answer = new Answer(text: "random", question: question, user: user)
        answer.save(flush: true, failOnError: true)
    }

    void "Test the index action returns null"() {
        when: 'Trying to access index'
            def msg = shouldFail(MissingMethodException) {
                controller.index()
            }
        then: 'Should return null'
    }
    
    void "Test adding a answer to a question works"() {
        when: 'Considering a answer for a question'
            params.text = "mytext"
            params.id = question.id
            controller.create()
        then: 'Should go back to question with the brand-new answer'
            assertEquals response.redirectedUrl, '/question/show?question_id=' + question.id
            assertEquals 2, question.answers.size() //since there is already one
            def myanswer = Answer.findByText("mytext")
            assertEquals answer.question, question
            assertEquals answer, question.answers[0]
            assertEquals myanswer, question.answers[1]

    }

    void "Test adding a answer to a non existing question raise error"() {
        when: 'Considering a answer for a 404 question'
            params.text = "mytext"
            params.id = -1
            controller.create()
        then: 'Should go back to index with error message'
            assertEquals flash.message, 'answer.question_404'
            assertEquals response.redirectedUrl, '/'
    }

    void "Test adding a answer with empty text fails"() {
        when: 'Considering an empty useless answer'
            params.text = ""
            params.id = question.id
            controller.create()
        then: 'Should go back to question page with error message'
            assertEquals flash.message, 'answer.text.size.toosmall'
            assertEquals response.redirectedUrl, '/question/show?question_id=' + question.id
    }

    void "Test editing an answer works"() {
        when: 'Editing an answer'
            params.id = '' + answer.id
            params.answer_text = "New text"
            controller.updateAnswer()
        then: 'Should go back to question page with edited answer'
            assertEquals response.redirectedUrl, '/question/show?question_id=' + question.id
            assertEquals "New text", answer.text 
    }

    void "Test deleting my own answer works"() {
        when: 'Deleting my answer'
            assertEquals 1, question.answers.size()
            params.answer_id = '' + answer.id
            controller.deleteAnswer()
        then: 'Should go back to question page with removed answer'
            assertEquals '/question/show?question_id=' + question.id, response.redirectedUrl
            assertEquals 'post.delete_success', flash.message
            assertEquals 0, question.answers.size()
    }

    void "Test deleting other user's answer do not work"() {
        when: "Deleting another user's answer"

            def user2 = new User(username: 'username2', password: 'a').save(flush: true, failOnError: true)
            def answer2 = new Answer(text: "itsquestion", question: question, user: user2).save(flush: true, failOnError: true)

            assertEquals 2, question.answers.size()
            params.answer_id = '' + answer2.id
            controller.deleteAnswer()
        then: 'Should go back to question page with error message'
            assertEquals '/question/show?question_id=' + question.id, response.redirectedUrl
            assertEquals 'post.delete_not_authorized', flash.message
            assertEquals 2, question.answers.size()
    }
}
