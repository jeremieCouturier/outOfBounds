package outofbounds

class Tag {
	
	String name
	String description = new String("")
	int reputation = 1 /*a tag is created by adding a new question*/
	
	static hasMany = [questions:Question]
	static belongsTo = Question

    static constraints = {
    }
}
