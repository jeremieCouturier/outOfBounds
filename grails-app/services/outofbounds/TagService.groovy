package outofbounds

import grails.transaction.Transactional

@Transactional
class TagService {

    def serviceMethod() {

    }
	
	def popularTags(def offset, def max) {

		String hql = '''
			SELECT t.id
			FROM Tag t LEFT OUTER JOIN t.questions q
			GROUP BY t.id
			ORDER BY COUNT(q) DESC
		'''
		def ids = Tag.executeQuery(hql, [], [offset: offset, max: max])

		return Tag.getAll(ids)
	}

	def newTags(def offset, def max) {
		return Tag.list(max: max, offset: offset, sort: 'creationDate', order: 'desc')
	}

	def nameTags(def offset, def max) {
		return Tag.list(max: max, offset: offset, sort: 'name', order: 'asc')
	}
	
	def unansweredTaggedQuestions(def tag) {
		if (tag == null) {
			return null
		}
		
		// find questions which
		def criteria = Question.createCriteria()
		def unanswered = criteria.list {
			// have no correct answer yet
			isNull('correctAnswer')
			and {
				 // and are tagged with the given tag
				tags {
					eq('id', tag.id)
				}
			}
			// sorted by date
			order('date', 'desc')
		}

		return unanswered
	}
	def newestTaggedQuestions(def tag, def offset, def max) {
		if (tag == null) {
			return null
		}

		// inverted order, see http://stackoverflow.com/questions/8672811/how-to-reverse-the-sort-of-a-groovy-collection
		def questions = tag.questions.asList().sort{ a, b ->
			b.date <=> a.date ?: b.title <=> a.title }
		
		offset = offset ? offset.toInteger() : 0
		max = max ? Math.min(max.toInteger() + offset, questions.size()) : questions.size()

		return questions.subList(offset, max)

	}
	def popularTaggedQuestions(def tag, def offset, def max) {
		if (tag == null) {
			return null
		}

		// non-trivial query: we need to sort QUESTIONS by ANSWERS count for 
		// a given tag...
		def popular = Question.executeQuery("""
			SELECT q
			FROM Question q
			WHERE q.id in (:list)
			ORDER BY size(q.answers) DESC""",
			[list: tag.questions*.id], [max: max, offset: offset])
		
		return popular
	}
}
