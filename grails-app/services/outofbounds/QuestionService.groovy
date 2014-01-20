package outofbounds

import grails.transaction.Transactional

@Transactional
class QuestionService {

    def serviceMethod() {

    }
	
	def saveQuestion(String title, String text, String tags, User user) {
		Question question = new Question(
				title: title,
				text: text,
				user: user
		).save(failOnError: true)

		return question
	}
}
