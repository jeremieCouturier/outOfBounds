package outofbounds

import grails.transaction.Transactional

@Transactional
class QuestionService {

    def serviceMethod() {

    }

	def setTags(String tagString, Question question)
	{
		String[] tagsName = tagString.split("[;,:( )]+")
		
		// remove tags from the question since we'll refill them right after
		// but first, check if these tags are used elsewhere. If not, delete them
		// definitely
		for (Tag t : question.tags) {
			if (t.questions.size() == 1) {
				t.delete()
			}
		}
		if (question.tags != null) {
			question.tags.clear()
		}

		for (String tagName : tagsName)
		{
			Tag tag = Tag.findByName(tagName)?:
				new Tag(name: tagName).save(failOnError: true)

			question.addToTags(tag)
		}	
		
		question.save(faiLonError: true)
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
		
		if (question.hasErrors()) {
			return question
		}

		setTags(tags, question)
		user.addToPosts(question)

		return question
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
		return listUnansweredQuestions.list(max: max, offset: offset, sort: 'date', order: 'desc')
	}
}
