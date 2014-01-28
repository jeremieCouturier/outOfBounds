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
			ORDER BY COUNT(q) ASC
		'''
		def ids = Tag.executeQuery(hql)

		ids.subList(offset ?: 0, max ?: ids.size())

		return Tag.getAll(ids)
	}

	def newTags(def offset, def max) {
		return Tag.list(max: max, offset: offset, sort: 'name', order: 'asc')
	}

	def nameTags(def offset, def max) {
		return Tag.list(max: max, offset: offset, sort: 'name', order: 'asc')
	}
	
	def unansweredTags(def tag) {
		def c = Question.createCriteria()
		def results = c.list {
			answers {
				count = 0
			}
			tags {
				eq('name', tag.name)
			}
			
			order("mark", "desc")
		}
		
		return results
	}
}
