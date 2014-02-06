package outofbounds

import grails.test.mixin.*
import spock.lang.*

@TestFor(CommentController)
@Mock([Comment, User, Post, Question, Role, UserRole])
class CommentControllerSpec extends Specification {
    def user
    def question
    def comment

    void setup() {
        defineBeans {
            commentService(CommentService) { bean ->
                bean.autowire = true
            }
            postService(PostService) { bean ->
                bean.autowire = true
            }
        }

        User.metaClass.encodePassword = { -> }
        CommentController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', password: 'a')
        user.save(flush: true, failOnError: true)

        question = new Question(title: "title very long", text: "b", tags: [], user: user)
        question.save(flush: true, failOnError: true)

        comment = new Comment(text: "text", post: question, user: user)
        comment.save(flush: true, failOnError: true)
    }

    void "Test the index action returns null"() {
        when: 'Trying to access index'
            def msg = shouldFail(MissingMethodException) {
                controller.index()
            }
        then: 'Should return null'
    }
    
    void "Test editing a comment works"() {
        when: 'Editing a comment'
            assertEquals "text", comment.text
            comment.text = "New text"
            controller.update(comment)
        then: 'Should go back to question page with edited comment'
            assertEquals response.redirectedUrl, '/question/show?question_id=' + question.id
            assertEquals "New text", comment.text
    }

    void "Test deleting my own comment works"() {
        when: 'Deleting my comment'
            assertEquals 1, question.comments.size()
            params.comment_id = '' + comment.id
            controller.deleteComment()
        then: 'Should go back to question page with removed comment'
            assertEquals '/question/show?question_id=' + question.id, response.redirectedUrl
            assertEquals 'post.delete_success', flash.message
            assertEquals 0, question.comments.size()
    }

    void "Test deleting other user's comment do not work"() {
        when: "Deleting another user's comment"

            def user2 = new User(username: 'username2', password: 'a').save(flush: true, failOnError: true)
            def comment2 = new Comment(text: "itsquestion", post: question, user: user2).save(flush: true, failOnError: true)

            assertEquals 2, question.comments.size()
            params.comment_id = '' + comment2.id
            controller.deleteComment()
        then: 'Should go back to question page with error message'
            assertEquals '/question/show?question_id=' + question.id, response.redirectedUrl
            assertEquals 'post.delete_not_authorized', flash.message
            assertEquals 2, question.comments.size()
    }
}
