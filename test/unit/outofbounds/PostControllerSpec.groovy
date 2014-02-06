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
    }

    void "Test add comment is OK"() {

        User.metaClass.encodePassword = { -> }
        PostController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', realname: 'realname', email: 'aaa@aa.fr',
            password: 'a', location: 'fr', website: 'google.fr')
        user.save(flush: true, failOnError: true)

        def question = new Question(title: "title very long", text: "text", user: user)
        question.save(flush: true, failOnError: true)

        when: "adding new comment"
            params.id = '1'
            params.text = 'text'
            controller.addComment()
        then: "save it and redirect to its page"
            assertEquals response.redirectedUrl, "/question/show?question_id=1"

            def q = Question.findById(1)
            assertNotNull q.comments
            assertNotNull q.comments[0]
            assertEquals q.comments[0].text, "text"
    }


    void "Test up vote when user is logged"() {

        User.metaClass.encodePassword = { -> }
        PostController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', realname: 'realname', email: 'aaa@aa.fr',
            password: 'a', location: 'fr', website: 'google.fr')
        user.save(flush: true, failOnError: true)

        def question = new Question(title: "title very long", text: "text", user: user)
        question.save(flush: true, failOnError: true)

        when: "down vote a post"
            params.post_id = '1'
            controller.upVote()
        then: "read mark"
            def q = Question.findById(1)
            assertEquals q.mark, 1
    }


    void "Test down vote when user is logged"() {

        User.metaClass.encodePassword = { -> }
        PostController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', realname: 'realname', email: 'aaa@aa.fr',
            password: 'a', location: 'fr', website: 'google.fr')
        user.save(flush: true, failOnError: true)

        def question = new Question(title: "title very long", text: "text", user: user)
        question.save(flush: true, failOnError: true)

        when: "down vote a post"
            params.post_id = '1'
            controller.downVote()
        then: "read mark"
            def q = Question.findById(1)
            assertEquals q.mark, -1
    }   
}

