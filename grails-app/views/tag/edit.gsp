<%@ page import="outofbounds.Tag" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'createTag.css')}" rel="stylesheet">
	</head>
	<body>
		<div>
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
			
			<div class="edit_tag">			
			<g:form url="[resource:tagInstance, action:'update']" method="PUT" >
				<ckeditor:resources/>
				<!-- title -->
				<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'title', 'error')} required">
				 	<div class="title">
						<label for="title">
							<g:message code="tag.name" />
							<span>*</span>
						</label>
						<input class="text" id="title" name="title" required="" value="${tagInstance?.name}"/>
					</div>
				</div>
				
				<!-- body -->
				<ckeditor:config var="toolbar_Mytoolbar">
				[
				    ['Bold', 'Italic', '-', 'Link', 'Unlink', 'Blockquote','CreateDiv', 'Image', '-', 'NumberedList', 'BulletedList', 'HorizontalRule', '-', 'Undo','Redo']
				]
				</ckeditor:config>
				<ckeditor:editor name="text" toolbar="Mytoolbar">
					<div class="content">${tagInstance?.description}</div>
				</ckeditor:editor>
			
				<div class="bouton">
					<button type="submit">${message(code: 'default.button.update.label')}</button>
				</div>
			</g:form>
			</div>
		</div>
	</body>
</html>
