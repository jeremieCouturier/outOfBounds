package outofbounds

class Tag {
	
	String name
	String description = new String("")
	
	static hasMany = [questions:Question]
	static belongsTo = Question

    static constraints = {
    }
}
