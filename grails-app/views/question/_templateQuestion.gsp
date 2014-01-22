<%@page import="outofbounds.User" %> 

<div>
	<label>Votes </label>${question.mark}
	<label>Answers</label>${question.answers.size()}
	<g:link controller="Question" action="show" params='[question_id: "${question.id}"]'>
	    ${question.title}
	</g:link>
	
	Tags
	<g:each in="${question.tags}" var="tag">
		<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
	    	${tag.name}
		</g:link>
	</g:each>
	
	<g:link controller="user" action="profile">
		${question.user.username}
	</g:link>
	<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}" />
	
</div>
