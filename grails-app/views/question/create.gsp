<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.Question')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<h1><g:message code="default.create.label" args="[entityName]" /></h1>

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
				<g:render template="form" />

		    	<fieldset class="buttons">
					<g:actionSubmit class="save" action="saveQuestion" value="${message(code: 'question.post_question')}" />
				</fieldset>
			</g:form>
		</div>		
	</body>
</html>
