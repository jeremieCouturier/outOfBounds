
<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<label>${questionInstance.title}</label>
				
		<textarea readonly="true">${questionInstance.text}</textarea>	
				
		<div>
            <div>
                <h2>${questionInstance.answers.size()} Answer<g:if test="${questionInstance.answers.size() > 1}">s</g:if></h2>
            </div>
            <g:render template="templateAnswer" collection="${questionInstance.answers}" var="answer" />
        </div>
        
        <g:form controller="Answer" action="create" id="${questionInstance.id}">
            <textarea name="answer_text" placeholder="Add an answer ..." required></textarea><br />
            <button type="submit">Post your answer</button>
        </g:form>
        
	</body>
</html>
