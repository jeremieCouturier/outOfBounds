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
	<!-- temporary -->
	<twitterAuth:button />
</sec:ifNotLoggedIn>

<sec:ifAnyGranted roles="ROLE_USER">
		<label>
		<g:message code="index.welcome" args="${sec.loggedInUserInfo(field:'username')}" />
		</label>
		<g:link controller="user" action="profile">
			<g:message code="user.my_profile" />
		</g:link><br>

	<g:remoteLink class="logout" controller="logout">${message(code: 'index.log_out')}</g:remoteLink><br>

		<g:link controller="question" action="create">
			<g:message code="question.create_question" />
		</g:link><br>
</sec:ifAnyGranted>
<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
</g:if>


<sec:ifAnyGranted roles="ROLE_ADMIN">
	<link:aboutConfig>
		<g:message code="index.about_config" /> 
	</link:aboutConfig><br>
</sec:ifAnyGranted>
