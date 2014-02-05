package outofbounds



import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
@Mock([Tag, Question, User, Post])
class TagControllerSpec extends Specification {

    def user
    def tag1, tag2, tag3

    void setup() {
        defineBeans {
            tagService(TagService) { bean ->
                bean.autowire = true
            }
        }
        User.metaClass.encodePassword = { -> }
        QuestionController.metaClass.springSecurityService.getAuthenticatedUser = { -> user }

        user = new User(username: 'username', realname: 'realname', email: 'aaa@aa.fr',
            password: 'a', location: 'fr', website: 'google.fr')
        user.save(flush: true)


        tag1 = new Tag(name: "grails", description: "")
        tag1.save(flush: true)
        tag2 = new Tag(name: "groovy", description: "")
        tag2.save(flush: true)
        tag3 = new Tag(name: "cpp", description: "")
        tag3.save(flush: true)

        def tags = [tag1, tag2, tag3]
        
        def question = new Question(title: "a", text: "a", tags: tags, user: user)
        question.save(flush: true)
    }

    void "Test the index action returns most popular tags"() {
        when: 'Trying to access index'
            controller.index()
        then: 'Should be redirected to popular tags'
            assert response.redirectedUrl == '/tag/popularTags'
    }

    void "Test the newest tags action returns most newest tags"() {
        when: 'Trying to access newest tags'
            controller.newTags()
        then: 'Should get the ordered list of newest tags'
            assert view == '/tag/index'
            assert model.tags == [tag3, tag2, tag1]
    }

    void "Test the name tags action returns tags by name"() {
        when: 'Trying to access tags by name'
            controller.nameTags()
        then: 'Should get the ordered list of tags by name'
            assert view == '/tag/index'
            assert model.tags == [tag3, tag1, tag2]
    }
}
