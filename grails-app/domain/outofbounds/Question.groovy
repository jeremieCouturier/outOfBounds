package outofbounds

class Question extends Post {
	
	String title
	
	
	static hasMany = [answers:Answer, tags:Tag]
	static belongsTo = [user:User]

    static constraints = {
		title blank: false, size:10..50
		text blank: false, maxSize: 1000
    }
}
