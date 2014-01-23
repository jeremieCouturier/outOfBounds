
<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="/header" />
		<div>
			<label>All question</label>
			<g:link controller="Question" action="newsQuestions">
		        <button>News</button>
		    </g:link>
		    <g:link controller="Question" action="voteQuestions">
		        <button>Vote</button>
		    </g:link>
		    <g:link controller="Question" action="unansweredQuestions">
		        <button>Unanswered</button>
		    </g:link>
		</div>
		
		<g:render template="templateQuestion" collection="${questions}" var="question" />
		
		<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
	</body>
</html>
