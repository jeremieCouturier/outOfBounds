<%@ page import="outofbounds.Tag" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="/header" />

		<div id="edit-tag" class="content scaffold-edit" tag="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${tagInstance}">
			<ul class="errors" tag="alert">
				<g:eachError bean="${tagInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:tagInstance, action:'update']" method="PUT" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'name', 'error')} required">
						<label for="name">
							<g:message code="tag.name" />
							<span class="required-indicator">*</span>
						</label>
						<g:textField name="name" required="" value="${tagInstance?.name}"/>
					</div>

					<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'description', 'error')}">
						<label for="description">
							<g:message code="tag.description" />
						</label>
						<g:textField name="description" value="${tagInstance?.description}"/>
					</div>

				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label'}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
