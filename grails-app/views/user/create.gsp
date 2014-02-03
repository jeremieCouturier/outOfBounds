<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'formLogSign.css')}" rel="stylesheet">
		
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
	
		<g:form url="[resource:userInstance, action:'save']" class="register">
			<g:render template="formRO"/>	
		</g:form>
	</body>
</html>
