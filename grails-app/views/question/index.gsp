
<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
	</head>
	<body>
		<!-- <g:render template="/header" /> -->
		
		<ul class="menu_question"> 
		   <li class="title">All Questions</li>
		   <li><g:link controller="Question" action="newsQuestions">newest</g:link></li> 
		   <li><g:link controller="Question" action="voteQuestions">votes</g:link></li> 
		   <li><g:link controller="Question" action="unansweredQuestions">unanswered</g:link></li> 
		</ul> 
		
		<g:render template="templateQuestion" collection="${questions}" var="question" />
		
		<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
	</body>
</html>
