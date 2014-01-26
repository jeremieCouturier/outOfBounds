package outofbounds

import grails.transaction.Transactional

@Transactional
class UserService {

    def serviceMethod() {

    }
	
	def newUsers(def offset, def max) {
		return User.list(max: max, offset: offset, /*sort: 'dateSignUp', order: 'desc'*/)
	}
}
