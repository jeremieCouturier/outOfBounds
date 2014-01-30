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
		def ids = Tag.executeQuery(hql)

		offset = offset ? offset.toInteger() : 0
		max = max ? Math.min(max.toInteger() + offset, ids.size()) : ids.size()

		ids = ids.subList(offset, max)

		return Tag.getAll(ids)
	}

	def newTags(def offset, def max) {
		return Tag.list(max: max, offset: offset, sort: 'creationDate', order: 'desc')
	}

	def nameTags(def offset, def max) {
		return Tag.list(max: max, offset: offset, sort: 'name', order: 'asc')
	}
	
	def unansweredTags(def tag) {
		//todo
	}

	def taggedQuestions(def tag, def max, def offset) {
		return tag.questions;//(max: max, offset: offset, sort: 'views', order: 'desc')
	}
}
