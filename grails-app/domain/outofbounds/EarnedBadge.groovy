package outofbounds

import java.util.Date;

class EarnedBadge {
	Badge badge
	Date earnedDate = new Date()
	static belongsTo = [user:User]

    static constraints = {
		badge  blank: false
    }
}
