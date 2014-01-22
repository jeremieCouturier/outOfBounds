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
				<tr><td><g:message code="index.username"/></td><td><g:textField name="j_username"/></td></tr>
				<tr><td><g:message code="index.password"/></td><td><input name="j_password" type="password"/></td></tr>
				<tr><td colspan="2"><g:submitButton name="login" value="${message(code: 'index.log_in')}"/></td></tr>
				</table>              
			</form>
			<g:form name="signup" url="[action:'create',controller:'user']">
				<g:submitButton name="signup" value="${message(code: 'index.sign_up')}"/>
			</g:form>
		</sec:ifNotLoggedIn>

		<sec:ifAnyGranted roles="ROLE_USER, ROLE_ADMIN">
	  		<g:link controller="user" action="profile">
	  			<g:message code="user.my_profile" />
	  		</g:link>

    		<g:remoteLink class="logout" controller="logout">${message(code: 'index.log_out')}</g:remoteLink><br>

	  		<g:link controller="question" action="create">
	  			<g:message code="question.create_question" />
	  		</g:link><br>
		</sec:ifAnyGranted>

		<link:aboutConfig><g:message code="index.about_config"/></link:aboutConfig>
	</body>
</html>
