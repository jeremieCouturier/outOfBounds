<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comment.Comment')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'display.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">
	</head>
	<body>
	<div class="comment">
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

		<div class="title">
	    	<label>Edit your Comment</label>
		</div>
		<g:form url="[resource:commentInstance, action:'update']" method="PUT" >
			<textarea name="text">${commentInstance.text}</textarea>			
			
			<div class="bottom">
		        <div class="button">
		        	<button type="submit">${message(code: 'default.button.update.label')}</button>
		        </div>
		        <div class="button">
			        <g:link controller="Question" action="show" params='[question_id: "${questionInstance.id}"]'>
				        <button>Cancel</button>
				    </g:link>
				</div>
			</div>
		</g:form>
	</div>
	</body>
</html>
