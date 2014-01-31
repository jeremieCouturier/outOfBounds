package outofbounds

class Post {
	
	int mark = 0
	String text
	Date date = new Date()
	
	static belongsTo = [user:User]
	static hasMany = [comments:Comment, vote:Vote]

	static constraints = {
		text type:'text'
	}

	public boolean canUserDeletePost(User inUser) {
        def adminRole = Role.findByAuthority('ROLE_ADMIN')
        //check that user has privileges to delete the post
        return (inUser.authorities.contains(adminRole) 
        	|| inUser.id == user.id)
	}	

	public boolean canUserEditPost(User inUser) {
        def moderatorRole = Role.findByAuthority('ROLE_MODERATOR')
        def adminRole = Role.findByAuthority('ROLE_ADMIN')
  		
        //check that user has privileges to edit the post
        return (inUser.authorities.contains(moderatorRole) 
        	|| inUser.authorities.contains(adminRole) 
        	|| inUser.id == user.id)
	}	
}
