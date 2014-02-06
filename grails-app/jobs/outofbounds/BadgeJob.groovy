package outofbounds

import outofbounds.BadgeCondition;
import outofbounds.Badge;
import outofbounds.Badge.BadgeMedal;
import outofbounds.BadgeService;
import outofbounds.User;

class BadgeJob {
	
	def BadgeService badgeService
	def UserService userService
	boolean initialized = false

    static triggers = {
      simple repeatInterval: 10000l // execute job once in 10 seconds
    }

    /**
     * For each user, add badges that verify the badge's conditions
     */
    def execute() {

		badgeService.addBadgeToUser(badgeService.findByName("yearConnection"), userService.findByUsername("admin"))
		for (Badge badge : badgeService.getAllBadges()) {
			for (User user : userService.getAllUsers()) {
				if (badgeService.callConditionOnBadge(badge, user)) {
					badgeService.addBadgeToUser(badge, user)
				}
			}
		}
	}
}
