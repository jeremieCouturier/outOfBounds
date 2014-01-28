package outofbounds

class Badge {
	String title;
	String description;
	
	static belongsTo = [user: User]

    static constraints = {
		title unique: true
    }
}
