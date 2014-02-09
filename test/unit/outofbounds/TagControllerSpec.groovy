package outofbounds



import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
@Mock([Tag, Question, User, Post, Role, UserRole])
class TagControllerSpec extends Specification {

    def user
    def tag1, tag2, tag3
    def question1, question2

    void setup() {
        defineBeans {
            tagService(TagService) { bean ->
                bean.autowire = true
            }
        }
        User.metaClass.encodePassword = { -> }
        QuestionController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', password: 'a').save(flush: true, failOnError: true)

        tag1 = new Tag(name: "grails", description: "", creationDate: new Date() - 1).save(flush: true, failOnError: true)
        tag2 = new Tag(name: "groovy", description: "", creationDate: new Date()).save(flush: true, failOnError: true)
        tag3 = new Tag(name: "cpp", description: "", creationDate: new Date() + 1).save(flush: true, failOnError: true)

        def tags = [tag1, tag2, tag3]
        
        question1 = new Question(title: "title very long", text: "a", tags: tags, user: user).save(flush: true, failOnError: true)
        question2 = new Question(title: "title very long2", text: "b", tags: [tag2], user: user).save(flush: true, failOnError: true)

        tag1.addToQuestions(question1).save(flush: true, failOnError: true)
        tag2.addToQuestions(question1).save(flush: true, failOnError: true)
        tag3.addToQuestions(question1).save(flush: true, failOnError: true)
        tag2.addToQuestions(question2).save(flush: true, failOnError: true)
    }

    void "Test the index action returns most popular tags"() {
        when: 'Trying to access index'
            controller.index()
        then: 'Should be redirected to popular tags'
            assertEquals response.redirectedUrl, '/tag/popularTags'
    }

    void "Test the newest tags action returns newest tags"() {
        when: 'Trying to access newest tags'
            controller.newTags()
        then: 'Should get the ordered list of newest tags'
            assertEquals view, '/tag/index'

            def sublist = [tag3, tag2, tag1].asList().subList(0, Math.min(3, Configuration.NUMBER_TAGS_PER_PAGE))

            assertEquals model.tags, sublist
    }

    void "Test the name tags action returns tags by name"() {
        when: 'Trying to access tags by name'
            controller.nameTags()
        then: 'Should get the ordered list of tags by name'
            assertEquals view, '/tag/index'

            def sublist = [tag3, tag1, tag2].asList().subList(0, Math.min(3, Configuration.NUMBER_TAGS_PER_PAGE))

            assertEquals model.tags, sublist
    }

    void "Test displaying a tag and its associated newest questions"() {
        when: 'Trying to access questions for a given tag'
            params.tag_id = '' + tag2.id
            controller.newestQuestions()
        then: 'Should get the list of questions and a brief description of the tag'
            assertNull flash.message
            assertEquals '/tag/show', view
            assertEquals tag2, model.tag 
            assertEquals Math.min(Configuration.NUMBER_POSTS_PER_PAGE, 2), model.questions.size()
            assertEquals 2, model.total
            if (Configuration.NUMBER_POSTS_PER_PAGE == 1) {
                assertEquals([question2], model.questions)
            } else {
                assertEquals([question1, question2], model.questions)
            }
    }
}
