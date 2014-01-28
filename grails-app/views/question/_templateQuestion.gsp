<%@page import="outofbounds.User" %> 
<link href="${resource(dir: 'css', file: 'listQuestion.css')}" rel="stylesheet">

<div class="list_question">

	<div class="parameters">
	<div class="group_mark">
		<span class="mark_value">${question.mark}</span><br/>
		<span class="mark_name">vote<g:if test="${question.mark != 0 }">s</g:if></span><br/>
		<span class="answers_value">${question.answers.size()}</span><br/>
		<span class="answers_name">answer<g:if test="${question.answers.size() != 0 }">s</g:if></span><br/>
	</div>
		<span class="views">${question.views} view<g:if test="${question.views != 0 }">s</g:if></span>
	</div>

	<div class="group_message">
	<g:link controller="Question" action="show" params='[question_id: "${question.id}"]'>
	    ${question.title}
	</g:link><br/>
	<label class="text">
		<g:if test="${question.text.size() > 150}"> ${question.text.substring(0, 150) + "..." } </g:if>
		<g:else> ${question.text} </g:else>
	</label>
	</div>
	
	<span class="tag">
		<g:each in="${question.tags}" var="tag">
			<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
		    	<label class="tag_text">${tag.name}</label>
			</g:link>
		</g:each>
	</span>
	
	<span class="user">
		<label><g:message code ="question.asked"/> </label>
		<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}" />
		<label> <g:message code ="question.by"/> </label>
		<g:link controller="user" action="show" params='[user_id: "${question.user.id}"]'>
			${question.user.username}
		</g:link>
	</span>
	
</div>
