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

	def newsQuestions() {
		return Question.list(max:10, sort: 'date', order: 'desc')
	}
	
	def voteQuestions()
	{
		return Question.list(max:10, sort: 'mark', order: 'desc')
	}

	def unansweredQuestions()
	{
		def listUnansweredQuestions = Question.findByViews(0)
		return listUnansweredQuestions.list(max:10, sort: 'date', order: 'desc')
	}
}
