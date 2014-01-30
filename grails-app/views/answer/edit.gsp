<%@ page import="outofbounds.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>

		<ckeditor:resources/>

	</head>
	<body>

    <label>Question : ${questionInstance.title}</label>

    <g:form action="editAnswer" id="${answerInstance.id}">
    	<div class="block">
			<!-- <textarea name="answer_text" required placeholder="Add an answer ...">${answerInstance.text}</textarea> -->
			<ckeditor:config var="toolbar_Mytoolbar">
				[
				    ['Bold', 'Italic', '-', 'Link', 'Unlink', 'Blockquote','CreateDiv', 'Image', '-', 'NumberedList', 'BulletedList', 'HorizontalRule', '-', 'Undo','Redo']
				]
			</ckeditor:config>
			<ckeditor:editor name="answer_text" toolbar="Mytoolbar">
				${answerInstance.text}
			</ckeditor:editor>
		</div>
        <br />
        <button type="submit">OK</button>
        
    </g:form>
    
    <g:link controller="Question" action="show" params='[question_id: "${questionInstance.id}"]'>
        <button>Cancel</button>
    </g:link>
		
	</body>
</html>
