<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.Question')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
		
		<g:if test="${flash.message}">
			<div class="message" question="status">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${questionInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${questionInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>

		<div class="edit_question">
			<g:form url="[resource:questionInstance, action:'update']" method="PUT" >
				<g:render template="form" />
			
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
