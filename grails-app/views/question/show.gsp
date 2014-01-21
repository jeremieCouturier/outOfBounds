
<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<!-- Question -->
		<label>${questionInstance.title}</label>
				
		<g:render template="templateQuestion" bean="${questionInstance}" var="question"></g:render>	
		
		<!-- Comments of the question -->
		<div>
            <div>
                <h2>${questionInstance.comments.size()} Comment<g:if test="${questionInstance.comments.size() > 1}">s</g:if></h2>
            </div>
            <g:render template="templateComment" collection="${questionInstance.comments}" var="comment" />
        </div>
        
        <g:form controller="Comment" action="createCommentForQuestion" id="${questionInstance.id}">
            <textarea name="comment_text" placeholder="Add a comment ..." required></textarea><br />
            <button type="submit">Add the comment</button>
        </g:form>
        
        
        <!-- Answers of the question -->

		<div>
            <div>
                <h2>${questionInstance.answers.size()} Answer<g:if test="${questionInstance.answers.size() > 1}">s</g:if></h2>
            </div>
            <g:render template="templateAnswer" collection="${questionInstance.answers}" var="answer" />
        </div>
        
        <g:form controller="Answer" action="createPostForAnswer" id="${questionInstance.id}">
            <textarea name="answer_text" placeholder="Add an answer ..." required></textarea><br />
            <button type="submit">Post your answer</button>
        </g:form>
        
	</body>
</html>
