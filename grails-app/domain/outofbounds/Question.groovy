package outofbounds

class Question extends Post {
	
	String title
	
	
	static hasMany = [answers:Answer, tags:Tag]

    static constraints = {
    }
}
