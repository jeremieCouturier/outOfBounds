<%@page import="outofbounds.Configuration"%>
<%@ page import="outofbounds.Question" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="question.list" /></title>
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<ul class="menuInPage"> 
		   	<li class="title"><g:message code="question.list" /></li>
		   <li <g:if test="${choice.equals('newest')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="newestQuestions"><g:message code="question.newest"/></g:link></li>
		   <li <g:if test="${choice.equals('votes')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="voteQuestions"><g:message code="question.votes"/></g:link></li>
		   <li <g:if test="${choice.equals('unanswered')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="unansweredQuestions"><g:message code="question.unanswered"/></g:link></li>
		</ul> 
		
		<g:render template="showSummary" collection="${questions}" var="question" />
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
		</div>
	</body>
</html>
