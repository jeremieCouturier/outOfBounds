package outofbounds

import outofbounds.badges.conditions.BadgeCondition;
import outofbounds.Badge;
import outofbounds.BadgeService;
import outofbounds.User;
import outofbounds.Configuration;

class BadgeJob {
	
	def BadgeService badgeService
	def UserService userService

    static triggers = {
      simple startDelay:10000, repeatInterval: 10000l // execute job once in 10 seconds
    }

    /**
     * For each user, add badges that verify the badge's conditions
     */
    def execute() {
    	log.info "job executed"
		for (Badge badge : badgeService.getAllBadges()) {
			for (User user : userService.getAllUsers()) {
				if (badgeService.callConditionOnBadge(badge, user)) {
					badgeService.addBadgeToUser(badge, user)
				}
      			userService.updateReputation(user)
			}
		}
	}
}
