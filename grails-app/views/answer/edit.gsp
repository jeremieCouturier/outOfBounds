<%@ page import="outofbounds.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'answer.Answer')}" />
		<link href="${resource(dir: 'css', file: 'display.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">
		<title><g:message code="default.edit.label" args="[entityName]" /></title>

		<ckeditor:resources/>

	</head>
	<body>

	<div class="title">
    	<label>${questionInstance.title}</label>
	</div>
	
    <g:form action="updateAnswer" id="${answerInstance.id}">
    	<div class="block">
			<ckeditor:config var="toolbar_Mytoolbar">
				[
				    ['Bold', 'Italic', '-', 'Link', 'Unlink', 'Blockquote','CreateDiv', 'Image', '-', 'NumberedList', 'BulletedList', 'HorizontalRule', '-', 'Undo','Redo']
				]
			</ckeditor:config>
			<ckeditor:editor name="answer_text" toolbar="Mytoolbar">
				${answerInstance?.text}
			</ckeditor:editor>
		</div>
        <br />
        
        <div class="bottom">
	        <div class="button">
	        	<button type="submit">OK</button>
	        </div>
	        <div class="button">
		        <g:link controller="Question" action="show" params='[question_id: "${questionInstance.id}"]'>
			        <button>Cancel</button>
			    </g:link>
			</div>
		</div>
        
    </g:form>
		
	</body>
</html>
