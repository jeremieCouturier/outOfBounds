import outofbounds.Question
import outofbounds.Role
import outofbounds.Tag
import outofbounds.User
import outofbounds.UserRole

class BootStrap {
	def springSecurityService

	def init = { servletContext ->
		/* roles definition */
	    def userRole = Role.findByAuthority('ROLE_USER') ?: 
	    	new Role(authority: 'ROLE_USER').save(failOnError: true)
	    def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: 
	    	new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
	    def moderatorRole = Role.findByAuthority('ROLE_MODERATOR') ?: 
	    	new Role(authority: 'ROLE_MODERATOR').save(failOnError: true)

	    /* admin super user */
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



		/* first question */
		def tag = Tag.findByName("grails") ?: new Tag(
			name:"grails",
			description:"the best way to develop your web app").save(failOnError: true)
		
		def question = new Question(
			title:"How to deploy a grails application?",
			text:"I try for 2 days to deploy my app but it doesn't work. Am I stupid?",
			user:adminUser)
		
		question.addToTags(tag).save(failOnError: true)
		
		/* second question */
		def tagcss = Tag.findByName("css") ?: new Tag(
			name:"css",
			description:"the design of your web app").save(failOnError: true)
			
		def taghtml = Tag.findByName("html") ?: new Tag(
			name:"html",
			description:"the body of your web app").save(failOnError: true)
		
		def questionWeb = new Question(
			title:"How to merge the use html/css",
			text:"I understand nothing. Please, please, please, help me!!!",
			user:adminUser)
		
		questionWeb.addToTags(tagcss).addToTags(taghtml).save(failOnError: true)
		
		/* third question */
		def tagcpp = Tag.findByName("cpp qt") ?: new Tag(
			name:"cpp",
			description:"a performing object language").save(failOnError: true)
		
		def questionCpp = new Question(
			title:"How to insert a graphic in Qt",
			text:"The problem is in the title! Does anybody know how to do?",
			user:adminUser)
		
		questionCpp.addToTags(tagcpp).save(failOnError: true)
			

	}
    def destroy = {
    }
}

