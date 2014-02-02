
<%@ page import="outofbounds.Badge" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'badge.label', default: 'Badge')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-badge" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-badge" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="medal" title="${message(code: 'badge.medal.label', default: 'Medal')}" />
						<g:sortableColumn property="name" title="${message(code: 'badge.name.label', default: 'Name')}" />
						<g:sortableColumn property="description" title="${message(code: 'badge.description.label', default: 'Description')}" />
						<g:sortableColumn property="dateCreated" title="${message(code: 'badge.date.label', default: 'Date')}" />
						<g:sortableColumn property="conditionClass" title="${message(code: 'badge.class.label', default: 'Class')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${badgeInstanceList}" status="i" var="badgeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: badgeInstance, field: "medal")}</td>
						<td><g:link action="show" id="${badgeInstance.id}">${fieldValue(bean: badgeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: badgeInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: badgeInstance, field: "dateCreated")}</td>
					
						<td>${fieldValue(bean: badgeInstance, field: "conditionClass")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${badgeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
