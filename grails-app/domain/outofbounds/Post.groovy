package outofbounds

class Post {
	
	int mark = 0
	String text
	
	static belongsTo = [user:User]
	static hasMany = [comments:Comment]

    /*static constraints = {
		tablePerHierarchy false
	
    }*/
	
}
