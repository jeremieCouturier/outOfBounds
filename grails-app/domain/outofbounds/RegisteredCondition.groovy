
package outofbounds

import java.util.Date;

class RegisteredCondition {

	/**
	 * @conditionClass class implementing BadgeCondition used to bind some badges to an user
	 * @conditionParameters parameters used by this class to perform this particular badge
	 */
	String conditionClass
	String conditionParametersNames

	static hasMany = [badges: Badge]

    static constraints = {
		conditionClass blank: false
    }
}
