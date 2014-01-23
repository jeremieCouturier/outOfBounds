package outofbounds

import grails.transaction.Transactional

@Transactional
class QuestionService {

    def serviceMethod() {

    }
	
	def ajouterTags(String tagString, Question question)
	{
		String[] tagsName = tagString.split("[;,:( )]+")
		
		for (String tagName : tagsName)
		{
			Tag tag = Tag.findByName(tagName)?:new Tag(name: tagName).save(failOnError: true)
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
		
		ajouterTags(tags, question)
		user.addToQuestions(question)

		return question
	}

	def newsQuestions(def offset, def max) {
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
