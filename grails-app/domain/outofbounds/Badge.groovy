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
	
	/**
	 * @conditionClass class implementing BadgeCondition used to bind some badges to an user
	 * @conditionParameters parameters used by this class to perform this particular badge
	 */
	String conditionClass
	List<String> conditionParameters
	
	static hasMany = [users: User]

    static constraints = {
		name  blank: false, unique: true
		medal blank: false
		conditionClass blank: false
    }
}
