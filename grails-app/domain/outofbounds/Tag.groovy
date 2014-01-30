package outofbounds

class Tag {
	
	String name
	String description
	Date  creationDate = new Date() //date of constructor call

	static hasMany = [questions:Question]
	static belongsTo = Question

    static constraints = {
    	name unique: true
    	description nullable: true
    }
}
