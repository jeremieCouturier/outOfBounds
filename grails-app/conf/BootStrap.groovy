import outofbounds.badges.conditions.*;
import outofbounds.Badge.BadgeMedal;
import outofbounds.Badge
import outofbounds.Question
import outofbounds.Answer
import outofbounds.Role
import outofbounds.Tag
import outofbounds.User
import outofbounds.UserRole

class BootStrap {
	def springSecurityService
	def badgeService

	def init = { servletContext ->
		//roles definition
		def userRole = Role.findByAuthority('ROLE_USER') ?:
			new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
			new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		def moderatorRole = Role.findByAuthority('ROLE_MODERATOR') ?:
			new Role(authority: 'ROLE_MODERATOR').save(failOnError: true)

		//admin super user
		def adminUser = User.findByUsername('admin') ?: new User(
			username: 'admin',
			password: "admin",
			realname: "paul ochon",
			location: "in the space",
			website: "http://admin.fr",
			email: "paul@ochon@yahoo.fr",
			enabled: true).save(failOnError: true)
		
		if (!adminUser.authorities.contains(adminRole)) {
			UserRole.create adminUser, adminRole
		}
		if (!adminUser.authorities.contains(userRole)) {
			UserRole.create adminUser, userRole
		}

		//random user
		for (String a in ["a","b","c","d","e","f","g","h","i","j"]) {
			createSimplifiedStandardUser(a, userRole)
		}

		def user = createSimplifiedStandardUser("user", userRole)


		//first question
		def tag = Tag.findByName("grails") ?: new Tag(
			name:"grails",
			description:"the best way to develop your web app").save(failOnError: true)
		
		def question = Question.findByTitle("How to deploy a grails application?") ?: new Question(
			title:"How to deploy a grails application?",
			text:"I try for 2 days to deploy my app but it doesn't work. Am I stupid?",
			user:adminUser).addToTags(tag).save(failOnError: true)

		//with its first answer (hourray!)
		if (! question.answers) {
			def answer = new Answer(
				text:"I tested g++ and it worked",
				question: question.id,
				user:adminUser).save(failOnError: true)

			question.correctAnswer = answer
		}
		
		//second question
		def tagcss = Tag.findByName("css") ?: new Tag(
			name:"css",
			description:"the design of your web app").save(failOnError: true)
			
		def taghtml = Tag.findByName("html") ?: new Tag(
			name:"html",
			description:"the body of your web app").save(failOnError: true)
		
		def questionWeb = Question.findByTitle("How to merge the use html/css") ?:new Question(
			title:"How to merge the use html/css",
			text:"I understand nothing. Please, please, please, help me!!!",
			user:adminUser).addToTags(tagcss).addToTags(taghtml).save(failOnError: true)

		//third question
		def tagcpp = Tag.findByName("cpp") ?: new Tag(
			name:"cpp",
			description:"a performing object language").save(failOnError: true)
		
		def questionCpp = Question.findByTitle("How to insert a graphic in Qt") ?: new Question(
			title:"How to insert a graphic in Qt",
			text:'<img alt="" height="347" src="http://www.menucool.com/slider/prod/image-slider-4.jpg"><p><strong>ed in, etc. HOWEVER, I do want to DISPLAY them in the same order. It seems to me that this logic should be able to exist ENTIRELY in the view layer, but the only solutions I&#39;ve been able to find tell me to declare items as a SortedSet in the model layer. This also affects my controller layer, as simple List op<em>erations such as .collect{} now require extra synta</em></strong><em>ctic jumping around to keep the type conversions correct and preserve my sorting. To me, this is nuts, so I must be missing something simple! Is there any way, for example, to do something like </em><code><em>&lt;g:each in=&quot;${cart.items</em>.sort{it.name}}&quot;&gt;</code> or so</p>',
			user:user).addToTags(tagcpp).save(failOnError: true)

		initBadges()
	}
	def destroy = {
	}

	def createSimplifiedStandardUser(String username, def userRole) {
		def user = User.findByUsername(username) ?: new User(
			username: username,
			password: username,			
			realname: username,
			location: username,
			website: username,
			email: username + "@hotmail.fr",
			enabled: true).save(failOnError: true)
		if (!user.authorities.contains(userRole)) {
			UserRole.create user, userRole
		}
		return user
	}

	def initBadges() {
		badgeService.saveBadge("Hello you",
			"You've been here for more than a second",
			BadgeMedal.Bronze,
			new ConnectedCondition(0, "HOUR")
			)
		badgeService.saveBadge("Happy hour",
			"You've been here for more than an hour",
			BadgeMedal.Silver,
			new ConnectedCondition(1, "HOUR")
			)
		badgeService.saveBadge("Happy birthday",
			"You've been here for more than a year",
			BadgeMedal.Gold,
			new ConnectedCondition(1, "YEAR")
			)
		badgeService.saveBadge("One question",
			"And the others will follow",
			BadgeMedal.Bronze,
			new QuestionNumberCondition(1)
			)
		badgeService.saveBadge("Thirteen questions",
			"Good luck",
			BadgeMedal.Silver,
			new QuestionNumberCondition(13)
			)
		badgeService.saveBadge("Forty-two questions",
			"We hope you've got your answer",
			BadgeMedal.Gold,
			new QuestionNumberCondition(42)
			)
		badgeService.saveBadge("Second reputation",
			"You're reputation is growing",
			BadgeMedal.Bronze,
			new ReputationCondition(2)
			)
		badgeService.saveBadge("Third reputation",
			"Three",
			BadgeMedal.Silver,
			new ReputationCondition(333)
			)
		badgeService.saveBadge("Reputation hunter",
			"There's a bounty on your head",
			BadgeMedal.Gold,
			new ReputationCondition(1000)
			)
	}

}

