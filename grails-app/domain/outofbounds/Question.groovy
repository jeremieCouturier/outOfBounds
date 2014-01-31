package outofbounds

class Question extends Post {
	
	String title
	Integer views = 0

	Answer correctAnswer

	// Unfortulately we cannot sort tags by name/creationDate because it's a many-to-
	// many relations (see http://stackoverflow.com/questions/2028301/grails-sorting-list-output-without-having-to-have-a-sortedset-or-comparable-mo)
	static hasMany = [answers:Answer, tags:Tag]

    static constraints = {
		title blank: false, size:10..50
		text blank: false, maxSize: 1000
		correctAnswer nullable: true
    }
}
