package outofbounds



import grails.test.mixin.*
import spock.lang.*
import grails.plugin.springsecurity.SpringSecurityUtils

@TestFor(PostController)
@Mock([Question, User, Comment, UpVote, DownVote, Post])
class PostControllerSpec extends Specification {

    def user
     

    void setup() {
        defineBeans {
            postService(PostService) { bean ->
                bean.autowire = true
            }

            questionService(QuestionService) { bean ->
                bean.autowire = true
            }
        }
        User.metaClass.encodePassword = { -> }
        PostController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', realname: 'realname', email: 'aaa@aa.fr',
            password: 'a', location: 'fr', website: 'google.fr')
        user.save(flush: true)
        
    }
    
/*
    void "Test up vote when no user logged"() {

      when: "up vote a post"
        params.post_id = '0'
        controller.upVote()
      then: "redirect to login page"
        assert response.redirectedUrl == '/login/auth'
    }

    void "Test down vot when no user logged"() {

      when: "down vote a post"
        params.post_id = '0'
        controller.downVote()
      then: "redirect to login page"
        assert response.redirectedUrl == '/login/auth'
    }
*/
    void "Test add comment is OK"() {

        def question = new Question(title: "title very long", text: "text", user: user)
        question.save(flush: true)

        when: "adding new comment"
            params.id = '1'
            params.text = 'text'
            controller.addComment()
        then: "save it and redirect to its page"
            assert response.redirectedUrl == "/question/show?question_id=1"

            def q = Question.findById(1)
            assert q.comments != null
            assert q.comments[0] != null
            assert q.comments[0].text == "text"
    }

    void "Test down vot when no user logged"() {

        def question = new Question(title: "title very long", text: "text", user: user)
        question.save(flush: true)

        when: "down vote a post"
            params.post_id = '1'
            controller.upVote()
        then: "read mark"
            def q = Question.findById(1)
            assert q.mark == 1
    }

    void "Test down vot when no user logged"() {

        def question = new Question(title: "title very long", text: "text", user: user)
        question.save(flush: true)

        when: "down vote a post"
            params.post_id = '1'
            controller.downVote()
        then: "read mark"
            def q = Question.findById(1)
            assert q.mark == -1
    }
    
}
