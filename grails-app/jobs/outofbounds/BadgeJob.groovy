package outofbounds

import outOfBounds.BadgeCondition;
import outofbounds.Badge;
import outofbounds.BadgeService;
import outofbounds.User;

class BadgeJob {
	
	def BadgeService badgeService
    static triggers = {
      simple repeatInterval: 10000l // execute job once in 10 seconds
    }

    /**
     * For each user, add badges that verify the badge's conditions
     */
    def execute() {
		badgeService.addBadgeToUser(Badge.findByName("yearConnection"), User.findByUsername("admin"))
		for (Badge badge : badgeService.getAllBadges()) {
			for (User user : User.list()) {
				if (badgeService.callConditionOnBadge(badge, user)) {
					badgeService.addBadgeToUser(badge, user)
				}
			}
		}
	}
	
}
