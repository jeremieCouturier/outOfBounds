package outofbounds

import org.junit.internal.runners.statements.FailOnTimeout;

import grails.transaction.Transactional

@Transactional
class AnswerService {

    def serviceMethod() {

    }
	
	def editAnswer(int answer_id, String text)
	{
		def answer = Answer.findById(answer_id)
	
		answer.text = text
		answer.save(failOnError: true)
	
		return answer
	}
	
	def create(int question_id, String text, User user)
	{
		def question = Question.findById(question_id)
		
		Answer answer = new Answer(
			question:question,
			text:text,
			user:user
		).save(failOnError: true)

		return answer
	}
	
	def delete(int answer_id)
	{
		def answer = Answer.findById(answer_id)
		def question = answer.question

		question.removeFromAnswers(answer)
		answer.delete()
		question.save(FailOnError: true)

		return question
	}
}
