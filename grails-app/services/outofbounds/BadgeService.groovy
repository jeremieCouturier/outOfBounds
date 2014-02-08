package outofbounds

import outofbounds.badges.conditions.BadgeCondition
import outofbounds.RegisteredCondition
import grails.transaction.Transactional

@Transactional
class BadgeService {
	
	def saveBadge(String name, String description,
		Badge.BadgeMedal medal, BadgeCondition badgeCondition)
	{
		Badge badge = Badge.findByName(name);
		if (badge == null) {
			String conditionClass = badgeCondition.getClass().getName()
			RegisteredCondition condition = RegisteredCondition.findByConditionClass(conditionClass)
			if (condition == null) {
				condition = new RegisteredCondition(
					conditionClass: conditionClass,
					conditionParametersNames: badgeCondition.getParametersNames().join(";")
				).save(failOnError: true, flush: true)
			}
			badge = new Badge(
				name: name,
				description: description,
				medal: medal,
				condition: condition,
				conditionParameters : badgeCondition.getParameters().join(";")
			).save(failOnError: true, flush: true)
		}
		return badge
	}
	
	def callConditionOnBadge(Badge badge, User user) {
		if (badge == null || user == null)  return false
		try {
			Class<?> conditionClass = Class.forName(badge.condition.conditionClass);
			if (conditionClass != null) {
				BadgeCondition condition = conditionClass.newInstance()
				condition.setParameters(badge.conditionParameters.split(";").toList())
				return condition.check(user)
			}
		}
		catch(Exception e) {
			throw e
			return false
		}
		return false;
	}
	
	def addBadgeToUser(Badge badge, User user) {
		if (badge == null || user == null)  return user

		// does user already has the badge ?
		for (EarnedBadge awardBadge : user.badges) {
			if (awardBadge.badge.name == badge.name) {
				return user
			}
		}

		// create user's badge
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

	def findByName(String name) {
		return Badge.findByName(name)
	}

	def getAllBadgesByUser(User user) {
		return Badge.findAllByUser(user)
	}
}
