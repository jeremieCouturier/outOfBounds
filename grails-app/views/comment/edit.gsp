<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comment.Comment')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${tagInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${tagInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>

		<div class="edit_question">
			<g:form url="[resource:commentInstance, action:'update']" method="PUT" >
				<fieldset class="form">
					<!-- text -->
					<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'text', 'error')} required">
						<label for="text">
							<g:message code="comment.text" />
							<span class="required-indicator">*</span>
						</label>
						<g:textField name="text" required="" value="${commentInstance?.text}"/>
					</div>
				</fieldset>

				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
