package outofbounds

import java.util.Date;

class Badge {
	
	enum BadgeMedal {
		Bronze,
		Silver,
		Gold
	}
	
	String name
	String description
	BadgeMedal medal
	Date dateCreated = new Date()
	
	String conditionClass
	List<String> conditionParameters
	
	static hasMany = [user: User]
	static belongsTo = User

    static constraints = {
		name  blank: false, unique: true
		medal blank: false
		conditionClass blank: false
    }
}
