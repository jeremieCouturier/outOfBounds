package outofbounds

class Comment extends Post {
	
	static belongsTo = [post:Post]
	
	static constraints = {
	}
}
