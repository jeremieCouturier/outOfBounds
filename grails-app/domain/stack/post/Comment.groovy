package stack.post

class Comment extends Post {
	
	static belongsTo = [post:Post]
	
    static constraints = {
    }
}
