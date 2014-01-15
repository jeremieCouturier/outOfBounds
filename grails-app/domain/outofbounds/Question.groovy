package outofbounds

class Question extends Post {
	
	String title
	
	
	static hasMany = [answers:Answer, tags:Tag]

    static constraints = {
		title blank:false, size:10..50
    }
}
