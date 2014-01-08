package stack.user

import stack.post.Post;

class User {
	static hasMany = [posts:Post]

    static constraints = {
    }
}
