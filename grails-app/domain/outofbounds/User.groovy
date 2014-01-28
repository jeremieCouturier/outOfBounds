package outofbounds

class User {
	static hasMany = [questions:Question, oAuthIDs: OAuthID, badges: Badge]

	transient springSecurityService
	
	Date dateSignUp = new Date()

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	transient bEncoded = false

	static constraints = {
		username blank: false, unique: true
		password blank: false, password: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	boolean isAdmin() {
	    def adminRole = Role.findByAuthority('ROLE_ADMIN')
		
	    return (authorities.contains(adminRole))
	}

	protected void encodePassword() {
		if (!bEncoded) {
			password = springSecurityService.encodePassword(password, username)
			bEncoded = true
		}
	}
}
