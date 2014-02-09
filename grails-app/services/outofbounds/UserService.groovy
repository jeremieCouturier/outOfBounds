package outofbounds

import grails.transaction.Transactional

@Transactional
class UserService {

	def questionService
	def answerService
	
	def newUser(def offset, def max) {
		
		def c = User.createCriteria()
		def results = c.list(max: max, offset: offset) {
			and{
			   order('dateSignUp','desc')
			   order('username','asc')
			}
		}
		return results
	}
	
	def reputationUser(def offset, def max) {
		def c = User.createCriteria()
		def results = c.list(max: max, offset: offset) {
			and{
			   order('reputation','desc')
			   order('username','asc')
			}
		}
		return results
	}
	
	def usernameUser(def offset, def max) {
		return User.list(max: max, offset: offset, sort: 'username', order: 'asc')
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
		//if (user == null) return user

		user.reputation = 1
		for (Question question : questionService.getAllQuestionsByUser(user)) {
			if (question.mark > 0) {
				user.reputation += 5 * question.mark
			}
			else {
				user.reputation += 2 * question.mark
			}
		}
		for (Answer answer : answerService.getAllAnswersByUser(user)) {
			if (answer.question.correctAnswer != null
				&&  answer.question.correctAnswer == answer) {
				user.reputation += 20
			}
			if (answer.mark > 0) {
				user.reputation += 10 * answer.mark
			}
			else {
				user.reputation += 2 * answer.mark
			}
		}
		if (user.reputation < 1) user.reputation = 1

		def moderatorRole = Role.findByAuthority('ROLE_MODERATOR')
		if (user.reputation >= Configuration.MODERATOR_REPUTATION) {
			if (!user.authorities.contains(moderatorRole)) {
				UserRole.create user, moderatorRole
			}
		}
		
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
