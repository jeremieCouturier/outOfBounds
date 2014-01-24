<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'display.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'addPost.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'header.css')}" rel="stylesheet">
	</head>
	<body>
		<g:render template="/headerSite" />
        <g:render template="/header" />
		
		<!-- Question -->
		<div class="question_show">					
			<g:render template="templateQuestionDetailled" bean="${questionInstance}" var="question"></g:render>
			
	        <!-- Answers of the question -->
			<div>
	            <div class="number_answers">
	                <label><g:message code="question.answer_count" args="[questionInstance.answers.size()]"/></label>
	            </div>
	            <g:render template="templateAnswer" collection="${questionInstance.answers}" var="answer" />
	        </div>
	
	        <sec:ifLoggedIn>
	        	<g:render template="templateAddAnswer" bean="${questionInstance}" var="question"></g:render>
	        </sec:ifLoggedIn>
	    </div>
        
	</body>
</html>
