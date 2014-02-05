package outofbounds

import outofbounds.BadgeCondition
import outofbounds.Badge.BadgeMedal
import grails.transaction.Transactional

@Transactional
class BadgeService {

    def serviceMethod() {

    }
	
	def updateBadge(String name, String description, BadgeMedal medal, BadgeCondition badgeCondition)
	{
		Badge badge = Badge.findByName(name);
		if (badge != null) {
			if (badge.description != description) {
				badge.description = description;
			}
			if (badge.medal != medal) {
				badge.medal = medal;
			}
		} else {
			badge = new Badge(
				name: name,
				description: description,
				medal: medal,
				conditionClass: badgeCondition.getClass(),
				conditionParameters: badgeCondition.getParameters()
			)
		}
		badge.save(failOnError: true)
		return badge
	}
	
	def callConditionOnBadge(Badge badge, User user) {
		try {
			Class<?> conditionClass = Class.forName(badge.conditionClass);
			BadgeCondition condition = conditionClass.newInstance()
			condition.setParameters(badge.conditionParameters)
			return condition.check(user)
		}
		catch(Exception e) {
			return false
		}
		return false;
	}
	
	def addBadgeToUser(Badge badge, User user) {
		if (user == null) return user

		for (EarnedBadge awardBadge : user.badges) {
			if (awardBadge?.badge?.name == badge?.name) {
				return user
			}
		}

		EarnedBadge awardBadge = new EarnedBadge(
			badge: badge,
			user: user
			).save(failOnError: true)

		badge.addToUsers(user)
		user.addToBadges(awardBadge)

		badge.save(failOnError: true)
		user.save(failOnError: true)
		
		return user
	}
	
	def getAllBadges() {
		return Badge.list()
	}
}
