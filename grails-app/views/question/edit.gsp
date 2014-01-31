<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="/header" />

		<div id="edit-question" class="content scaffold-edit" question="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" question="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${questionInstance}">
			<ul class="errors" question="alert">
				<g:eachError bean="${questionInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:questionInstance, action:'update']" method="PUT" >
				<fieldset class="form">
					<!-- title -->
					<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'title', 'error')} required">
						<label for="title">
							<g:message code="question.title.label" />
							<span class="required-indicator">*</span>
						</label>
						<g:textField name="title" required="" value="${questionInstance?.title}"/>
					</div>


					<!-- tags -->
					<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'tags', 'error')} ">
						<label for="tags">
							<g:message code="question.tags.label" />
							
						</label>
						<g:select name="tags" from="${outofbounds.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${questionInstance?.tags*.id}" class="many-to-many"/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
