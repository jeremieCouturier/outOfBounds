<%@ page import="outofbounds.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>

    <label>Question : ${questionInstance.title}</label>

    <g:form action="editAnswer" id="${answerInstance.id}">
    	<div class="block">
			<textarea name="answer_text" required placeholder="Add an answer ...">${answerInstance.text}</textarea>
		</div>
        <br />
        <button type="submit">OK</button>
        
    </g:form>
    
    <g:link controller="Question" action="show" params='[question_id: "${questionInstance.id}"]'>
        <button>Cancel</button>
    </g:link>
		
	</body>
</html>
