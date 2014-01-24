<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
	</head>
	<body>
        <g:render template="/header" />
		
		<!-- Question -->
		<div class="question_detailled">					
			<g:render template="templateQuestionDetailled" bean="${questionInstance}" var="question"></g:render>	
	
			<!-- Comments of the question -->
			<g:render template="templateComment" collection="${questionInstance.comments}" var="comment" />
	        
	        <g:form controller="Comment" action="createCommentForQuestion" id="${questionInstance.id}">
	            <textarea name="comment_text" placeholder="Add a comment ..." required></textarea><br />
	            <button type="submit">Add the comment</button>
	        </g:form>
	        
	        
	        <!-- Answers of the question -->
	
			<div>
	            <div class="number_answers">
	                <label>${questionInstance.answers.size()} Answer<g:if test="${questionInstance.answers.size() > 1}">s</g:if></label>
	            </div>
	            <g:render template="templateAnswer" collection="${questionInstance.answers}" var="answer" />
	        </div>
	
	        <g:form controller="Answer" action="createAnswerForQuestion" id="${questionInstance.id}">
	            <textarea name="answer_text" placeholder="Add an answer ..." required></textarea><br />
	            <button type="submit">Post your answer</button>
	        </g:form>
	    </div>
        
	</body>
</html>
