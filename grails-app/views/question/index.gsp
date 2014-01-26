<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.Question" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'listQuestion.css')}" rel="stylesheet">
	</head>
	<body>
		
		<ul class="menuInPage"> 
		   <li class="title">All Questions</li>
		   <li <g:if test="${choice.equals('newest')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="newestQuestions">newest</g:link></li>
		   <li <g:if test="${choice.equals('votes')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="voteQuestions">votes</g:link></li>
		   <li <g:if test="${choice.equals('unanswered')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="unansweredQuestions">unanswered</g:link></li>
		</ul> 
		
		<g:render template="templateQuestion" collection="${questions}" var="question" />
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
		</div>
	</body>
</html>
