<%@page import="outofbounds.User" %> 

<div>
	<g:link controller="Question" action="show" params='[question_id: "${question.id}"]'>
	    ${question.title}
	</g:link>
	<g:link controller="user" action="profile">
			${question.user.username}
	</g:link>
	<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}" />
</div>
