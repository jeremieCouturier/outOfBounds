<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
		<link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
	</head>
	<body>
		<g:render template="/header" />

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
		<g:form action="saveQuestion" >

			<div class="block">
				<label class="label_title" for="title">Title</label>
				<input class="input_title" name="question_title" type="text" placeholder="What's your programming question? Be specific" required>
			</div>
			
			<br/>
			
			<div class="block">
				<textarea name="question_text" required placeholder="Enter your question here ..."></textarea>
			</div>
			
			<br/>
			
			<div class="block">
				<label for="tags" >Tags</label>
				<input name="question_tags" placeholder="at least one tag such as (java, grails, c++), max 5 tags">
			</div>
			<button type="submit">Post your question</button>
                    
		</g:form>
	</body>
</html>
