package outofbounds

class Post {
	
	int mark = 0
	String text
	Date date = new Date()
	
	static belongsTo = [user:User]
	static hasMany = [comments:Comment]

    /*static constraints = {
		tablePerHierarchy false
	
    }*/

	public boolean canUserDeletePost(User inUser) {
        def adminRole = Role.findByAuthority('ROLE_ADMIN')
  		
        //check that user has privileges to delete the post
        return (inUser.authorities.contains(adminRole) || inUser.id == user.id)
	}	
}
