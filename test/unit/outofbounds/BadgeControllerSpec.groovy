package outofbounds

import grails.test.mixin.*
import spock.lang.*

@TestFor(BadgeController)
@Mock(Badge)
class BadgeControllerSpec extends Specification {

    void setup() {
    }

    void "Test the index action returns list of badges"() {
        when: 'Trying to access index'
            controller.index()
        then: 'Should return list of badges'
            assertEquals response.redirectedUrl, '/badge/badges'
    }
}