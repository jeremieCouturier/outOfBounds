package outofbounds

import grails.transaction.Transactional

@Transactional
class QuestionService {

	def answerService

	def setTags(String tagString, Question question)
	{
		String[] tagsName = tagString.split("[;,:( )]+")
		
		// remove tags from the question since we'll refill them right after
		// but first, check if these tags are used elsewhere. If not, delete them
		// definitely
		question.tags.each { t ->
			if (t.questions.size() == 1) {
				t.delete()
			}
		}

		if (question.tags != null) {
			question.tags.clear()
		}

		for (String tagName : tagsName) {
			Tag tag = Tag.findByName(tagName.toLowerCase())?:
				new Tag(name: tagName.toLowerCase()).save(failOnError: true)

			question.addToTags(tag)
		}	
		
		question.save(failOnError: true)
	}
	
	def addView(Question question)
	{
		question.views ++
		question.save(failOnError: true)
	}

	def saveQuestion(String title, String text, String tags, User user) {
		
		Question question = new Question(
				title: title,
				text: text,
				user: user
		)

		if (! question.validate()) {
			return question
		}

		setTags(tags, question)
		user.addToPosts(question)

		return question
	}

	def deleteQuestion(Question question) {
		// remove answers
		for (Answer a : question.answers) {
			answerService.deleteAnswer(a)
		}

		// and question's comments
		question.comments.each { comment ->
			answer.removeFromComments(comment)
			comment.delete()
		}

		// and tags if they are not used elsewhere
		question.tags.each { tag ->
			if (tag.questions.size() == 1) {
				tag.delete()
			}
		}

		question.delete flush:true
	}

	def newestQuestions(def offset, def max) {
		return Question.list(max: max, offset: offset, sort: 'date', order: 'desc')
	}
	
	def voteQuestions(def offset, def max)
	{
		return Question.list(max: max, offset: offset, sort: 'mark', order: 'desc')
	}

	def unansweredQuestions(def offset, def max)
	{
		def listUnansweredQuestions = Question.findByViews(0)
		if (listUnansweredQuestions !! null) {
			listUnansweredQuestions = listUnansweredQuestions.list(max: max, offset: offset, sort: 'date', order: 'desc')
		}
		return listUnansweredQuestions
	}

	def getAllQuestionsByUser(User user) {
		return Question.findAllByUser(user)
	}
}
