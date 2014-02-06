package outofbounds



import grails.test.mixin.*
import spock.lang.*
import grails.plugin.springsecurity.SpringSecurityUtils

@TestFor(PostController)
@Mock([Question, User, Comment, UpVote, DownVote, Post])
class PostControllerSpec extends Specification {

    def user
    def user2

    void setup() {
        defineBeans {
            postService(PostService) { bean ->
                bean.autowire = true
            }
        } 

        User.metaClass.encodePassword = { -> }

        user = new User(username: 'username', realname: 'realname', email: 'aaa@aa.fr',
            password: 'a', location: 'fr', website: 'google.fr')
        user.save(flush: true, failOnError: true)

        PostController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }         

        user2 = new User(username: 'otherusername', realname: 'otherrealname', email: 'bbb@aa.fr',
            password: 'b', location: 'fr', website: 'google.fr')
        user2.save(flush: true, failOnError: true)
    }


    void "Test add comment is OK"() {       

        def question = new Question(title: "title very long", text: "text", user: user)
        question.save(flush: true, failOnError: true)

        when: "adding new comment"
            params.id = question.id.toString()
            params.text = 'text'
            controller.addComment()
        then: "save it and redirect to its page"
            assert response.redirectedUrl == "/question/show?question_id=1"

            def q = Question.findById(question.id)
            assert q.comments != null
            assert q.comments[0] != null
            assert q.comments[0].text == "text"
    }


    void "Test up vote"() {

        def question = new Question(title: "title very long", text: "text", user: user2)
        question.save(flush: true, failOnError: true)

        when: "up vote a post"
            params.post_id = question.id.toString()
            controller.upVote()
        then: "read mark"
            def q = Question.findById(question.id)
            assert q.mark == 1
    }


    void "Test down vote"() {

        def question = new Question(title: "title very long", text: "text", user: user2)
        question.save(flush: true, failOnError: true)

        when: "down vote a post"
            params.post_id = question.id.toString()
            controller.downVote()
        then: "read mark"
            def q = Question.findById(question.id)
            assert q.mark == -1
    }
}

