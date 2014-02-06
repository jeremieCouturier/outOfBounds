package outofbounds

import grails.transaction.Transactional

@Transactional
class UserService {
	
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

	/**
	 * simplified reputation rules from
	 * http://meta.stackoverflow.com/questions/7237/how-does-reputation-work
	 */
	def updateReputation(User user) {
		if (user == null) return user

		user.reputation = 1
		for (Question question : Question.findAllByUser(user)) {
			if (question.mark > 0) {
				user.reputation += 5 * question.mark
			}
			else {
				user.reputation -= 2 * question.mark
			}
		}
		for (Answer answer : Answer.findAllByUser(user)) {
			if (answer.question.correctAnswer == answer) {
				user.reputation ++
			}
			if (answer.mark > 0) {
				user.reputation += 10 * answer.mark
			}
			else {
				user.reputation -= 2 * answer.mark
			}
		}
		if (user.reputation < 1) user.reputation = 1
		
		user.save(failOnError : true)

		return user
	}

	def getAllUsers() {
		return User.list()
	}
	def findByUsername(String name) {
		return User.findByUsername(name)
	}
}
