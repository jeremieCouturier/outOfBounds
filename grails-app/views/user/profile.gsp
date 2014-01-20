
<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.username')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username"/></span>
					
					<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
				</li>
			
				<li class="fieldcontain">
					<g:set var="count" value="${userInstance.posts?.size()}" />
					<span id="postcount-label" class="property-label"><g:message code="user.post_count"/></span>
					
					<span class="property-value" aria-labelledby="postcount-label">${count}</span>
				</li>

				<g:if test="${userInstance?.posts}">
				<li class="fieldcontain">
					<span id="posts-label" class="property-label"><g:message code="user.posts.label" /></span>
					
						<g:each in="${userInstance.posts}" var="p">
						<span class="property-value" aria-labelledby="posts-label"><g:link controller="post" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
