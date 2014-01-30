<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" >
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
		<link href="${resource(dir: 'css', file: 'createQuestion.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'header.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'subheader.css')}" rel="stylesheet">

		<ckeditor:resources/>

	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${question}">
			<ul class="errors" role="alert">
				<g:eachError bean="${question}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		
		<div class="new_question">
		<g:form action="saveQuestion" >

			<div class="title">
				<label for="title">Title</label>
				<input class="text" name="question_title" type="text" placeholder="What's your programming question? Be specific">
			</div>
			
			<div class="text">
				<!--  <textarea name="question_text" placeholder="Enter your question here ..."></textarea> -->
				<ckeditor:config var="toolbar_Mytoolbar">
					[
					    ['Bold', 'Italic', '-', 'Link', 'Unlink', 'Blockquote','CreateDiv', 'Image', '-', 'NumberedList', 'BulletedList', 'HorizontalRule', '-', 'Undo','Redo']
					]
				</ckeditor:config>
				<ckeditor:editor name="question_text" toolbar="Mytoolbar">
					${initialValue}
				</ckeditor:editor>
			</div>
			
			<div class="tags">
				<label for="tags" >Tags</label>
				<input class="text" name="question_tags" placeholder="at least one tag such as (java, grails, c++), max 5 tags">
			</div>
			
			<div class="bouton">
				<button type="submit">Post your question</button>
            </div>
                   
		</g:form>
		</div>
		
	</body>
</html>
