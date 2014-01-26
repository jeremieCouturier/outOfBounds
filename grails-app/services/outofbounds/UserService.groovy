package outofbounds

import grails.transaction.Transactional

@Transactional
class UserService {

    def serviceMethod() {

    }
	
	def newUsers(def offset, def max) {
		return User.list(max: max, offset: offset, sort: 'dateSignUp', order: 'desc')
	}
	
	def userQuestions(def user) {
		return Question.findAllByUser(user)
	}
	
	def userAnswers(def user) {
		def c = Question.createCriteria()
		def results = c.list {
			answers {
				eq('user', user)
			}
		}
		
		return results
	}
}
