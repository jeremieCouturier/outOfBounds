package outofbounds



import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
@Mock([Tag, Question, User, Post])
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
        
        question1 = new Question(title: "a", text: "a", tags: tags, user: user)
        question1.save(flush: true)
        
        question2 = new Question(title: "b", text: "b", tags: [tag2], user: user)
        question2.save(flush: true)

        tag1.addToQuestions(question1).save()
        tag2.addToQuestions(question1).save()
        tag3.addToQuestions(question1).save()
        tag2.addToQuestions(question2).save()
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

    void "Test displaying a tag and its associated newest questions"() {
        when: 'Trying to access questions for a given tag'
            params.tag_id = tag2.id
            controller.newestQuestions()
        then: 'Should get the list of questions and a brief description of the tag'
            assert view == '/tag/show'
            assert model.tag == tag2
            assert model.questions == [question2, question1]
            assert model.total == 2
    }
}
