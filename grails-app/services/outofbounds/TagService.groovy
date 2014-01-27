package outofbounds

import grails.transaction.Transactional

@Transactional
class TagService {

    def serviceMethod() {

    }
	
	def popularTags(def offset, def max) {
		return Tag.list(max: max, offset: offset, sort: 'reputation', order: 'desc')
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
