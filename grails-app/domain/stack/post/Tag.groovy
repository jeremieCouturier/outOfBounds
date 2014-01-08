package stack.post

class Tag {
	
	String name
	String description
	int reputation
	
	static hasMany = [questions:Question]
	static belongsTo = Question

    static constraints = {
    }
}
