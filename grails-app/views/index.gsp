<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Out of bounds</title>
	</head>
	<body>
		<sec:ifNotLoggedIn>
			<form method="POST" action="${resource(file: 'j_spring_security_check')}">
				<table>
				<tr><td>Username:</td><td><g:textField name="j_username"/></td></tr>
				<tr><td>Password:</td><td><input name="j_password" type="password"/></td></tr>
				<tr><td colspan="2"><g:submitButton name="login" value="Login"/></td></tr>
				</table>              
			</form>
			<g:form name="signup" url="[action:'create',controller:'user']">
				<g:submitButton name="signup" value="Sign Up" />
			</g:form>
		</sec:ifNotLoggedIn>
		<sec:ifAllGranted roles="ROLE_USER">
	  		<g:link controller="user" action="profile" resource="${userInstance}">
	  			<g:message code="user.my_profile" />
	  		</g:link>
	  		<g:link controller="user" action="disconnection">
	  			
	  		</g:link>
			<form method="POST" action="${resource(file: 'j_spring_security_check')}">
				<g:submitButton name="logout" value="Logout"/>
			</form>
		</sec:ifAllGranted>
		<link:aboutConfig><g:message code="index.about_config"/></link:aboutConfig>
	</body>
</html>
