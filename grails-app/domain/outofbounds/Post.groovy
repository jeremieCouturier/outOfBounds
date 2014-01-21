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
	
}
