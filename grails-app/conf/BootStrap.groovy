import outOfBounds.BadgeCondition;
import outOfBounds.ConnectedCondition;
import outOfBounds.QuestionNumberCondition
import outOfBounds.ConnectedCondition.CalendarFormat;
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
        	enabled: true).save(failOnError: true)
		
	    if (!adminUser.authorities.contains(adminRole)) {
        	UserRole.create adminUser, adminRole
    	}
    	if (!adminUser.authorities.contains(userRole)) {
        	UserRole.create adminUser, userRole
    	}

    	//random user
    	def user = User.findByUsername('a') ?: new User(
        	username: 'a',
        	password: 'a',
        	enabled: true).save(failOnError: true)
    	if (!user.authorities.contains(userRole)) {
        	UserRole.create user, userRole
    	}


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
			text:'<p><strong>ed in, etc. HOWEVER, I do want to DISPLAY them in the same order. It seems to me that this logic should be able to exist ENTIRELY in the view layer, but the only solutions I&#39;ve been able to find tell me to declare items as a SortedSet in the model layer. This also affects my controller layer, as simple List op<em>erations such as .collect{} now require extra synta</em></strong><em>ctic jumping around to keep the type conversions correct and preserve my sorting. To me, this is nuts, so I must be missing something simple! Is there any way, for example, to do something like </em><code><em>&lt;g:each in=&quot;${cart.items</em>.sort{it.name}}&quot;&gt;</code> or so</p>',
			user:user).addToTags(tagcpp).save(failOnError: true)
		
		//badges
		BadgeCondition bc = new ConnectedCondition(1, CalendarFormat.HOUR)
		def badge = Badge.findByName("hourConnection") ?: new Badge(
			name:"hourConnection",
			description:"you've been here for an hour",
			medal:BadgeMedal.Bronze,
			conditionClass: ConnectedCondition.class.getName(),
			conditionParameters: bc.getParameters()
			).save(failOnError: true)
			
		BadgeCondition bcy = new ConnectedCondition(1, CalendarFormat.YEAR)
		def badgey = Badge.findByName("yearConnection") ?: new Badge(
			name:"yearConnection",
			description:"you've been here for a year",
			medal:BadgeMedal.Silver,
			conditionClass: ConnectedCondition.class.getName(),
			conditionParameters: bcy.getParameters()
			).save(failOnError: true)
				
		BadgeCondition bcs = new ConnectedCondition(0, CalendarFormat.HOUR)
		def badgesec = Badge.findByName("First connection") ?: new Badge(
			name:"First connection",
			description:"Hello world",
			medal:BadgeMedal.Bronze,
			conditionClass: ConnectedCondition.class.getName(),
			conditionParameters: bcs.getParameters()
			).save(failOnError: true)
				
		BadgeCondition bcq = new QuestionNumberCondition(1);
		def badgeq = Badge.findByName("First question") ?: new Badge(
			name:"First question",
			description:"You're first question !",
			medal:BadgeMedal.Bronze,
			conditionClass: QuestionNumberCondition.class.getName(),
			conditionParameters: bcq.getParameters()
			).save(failOnError: true)
	}
    def destroy = {
    }
}

