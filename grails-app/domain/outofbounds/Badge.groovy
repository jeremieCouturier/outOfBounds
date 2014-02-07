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

	String conditionParameters
	
	static hasMany = [users: User]
	static belongsTo = [condition: RegisteredCondition]

    static constraints = {
		name  blank: false, unique: true
		medal blank: false
    }
}
