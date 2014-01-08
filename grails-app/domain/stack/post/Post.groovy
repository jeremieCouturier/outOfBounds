package stack.post

import stack.user.User;

class Post {
	
	int mark
	String text
	
	static belongsTo = [user:User]
	static hasMany = [comments:Comment]

    static constraints = {
		tablePerHierarchy false
    }
}
