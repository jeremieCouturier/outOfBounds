package outofbounds

class User {
	static hasMany = [posts:Post, oAuthIDs: OAuthID, badges:EarnedBadge, upVoted:UpVote, downVoted:DownVote]

	transient springSecurityService
	
	Date dateSignUp = new Date()

	String username
	String realname
	String email
	String website
	String location
	String password
	
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Integer reputation = 1
	
	static transients = ['springSecurityService']

	transient bEncoded = false

	static constraints = {
		username blank: false, unique: true
		password blank: false, password: true
		realname nullable: true
		email nullable: true
		website nullable: true
		location nullable: true
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

	boolean isModerator() {
	    def modRole = Role.findByAuthority('ROLE_MODERATOR')
		
	    return (isAdmin() || authorities.contains(modRole))
	}

	protected void encodePassword() {
		if (!bEncoded) {
			password = springSecurityService.encodePassword(password, username)
			bEncoded = true
		}
	}
}
