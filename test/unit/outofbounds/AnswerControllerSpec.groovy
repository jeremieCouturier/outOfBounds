package outofbounds



import grails.test.mixin.*
import spock.lang.*

@TestFor(AnswerController)
@Mock(Answer)
class AnswerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns null"() {
        def msg = shouldFail(MissingMethodException) {
            controller.index()
        }
    }
}
