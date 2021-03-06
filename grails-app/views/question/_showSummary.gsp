<%@page import="outofbounds.User" %>
<%@page import="outofbounds.Text"%>
<%@ defaultCodec="none" %>

<link href="${resource(dir: 'css', file: 'listQuestion.css')}" rel="stylesheet">

<div class="list_question">

	<div class="parameters">
		<div class="group_mark">
			<span class="mark_value">${question.mark}</span><br/>
			<span class="mark_name"><g:message code='question.vote_pl' args="[question.mark]"/></span><br/>
			<span class="answers_value">${question.answers.size()}</span><br/>
			<span class="answers_name"><g:message code='question.answer_pl' args="[question.answers.size()]"/></span><br/>
		</div>
		<span class="views"><g:message code="question.view_count" args="[question.views]"/></span>
	</div>

	<div class="group_message">
		<g:link controller="Question" action="show" params='[question_id: "${question.id}"]'>
		    ${question.title}
		</g:link><br/>
		<label class="text">
			${Text.summaryText(question.text)}
		<g:link controller="Question" action="show" params='[question_id: "${question.id}"]'>
		    ...
		</g:link><br/>
		</label>
	</div>
	
	<span class="tag">
		<g:each in="${question.tags.sort{a,b-> a.creationDate.compareTo(b.creationDate)} }" var="tag">
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
