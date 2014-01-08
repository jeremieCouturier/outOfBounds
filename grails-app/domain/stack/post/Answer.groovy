package stack.post

class Answer extends Post {
	
	static belongsTo = [question:Question]

    static constraints = {
    }
}
