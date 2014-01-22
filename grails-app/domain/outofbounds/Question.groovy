package outofbounds

class Question extends Post {
	
	String title
	Integer views = 0
	
	static hasMany = [answers:Answer, tags:Tag]

    static constraints = {
		title blank: false, size:10..50
		text blank: false, maxSize: 1000
    }
}
