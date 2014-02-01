
<%@ page import="outofbounds.Question" %>
<%@ page import="outofbounds.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'display.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'addPost.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">		

		<ckeditor:resources/>
		
	</head>
	<body>		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>

		<!-- Question -->
		<div class="question_show">					
			
			<g:render template="showDetails" bean="${questionInstance}" var="question"></g:render>
			
	        <!-- Answers of the question -->
			<div>
	            <div class="number_answers">
	                <label><g:message code="question.answer_count" args="[questionInstance.answers.size()]"/></label>
	            </div>
	            <!-- render the correct answer first if it exists -->
	            <g:if test="${questionInstance.correctAnswer != null}">
	            	<g:render template="/answer/show" 
	            	model="['answer': questionInstance.correctAnswer]" />
	            </g:if>

	            <!-- render the others -->
	            <g:render template="/answer/show" collection="${questionInstance.answers.findAll {
	            it.id != questionInstance.correctAnswer?.id}.sort{a,b->
	            (b.vote.size() <=> a.vote.size()) ?: a.date <=> b.date}}" var="answer" />	
	        </div>
	
	        <sec:ifLoggedIn>
	        	<g:render template="/answer/add" bean="${questionInstance}" var="question"></g:render>
	        </sec:ifLoggedIn>
	    </div>
        
	</body>
</html>

