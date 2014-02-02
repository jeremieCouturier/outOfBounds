
<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.Badge" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'badge.label', default: 'Badge')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'badge.css')}" rel="stylesheet">
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
			
		<ul class="menuInPage"> 
		   	<li class="title"><g:message code="default.list.label" args="[entityName]" /></li>
		   	<li <g:if test="${choice.equals('all')}">class="item_selected_menu"</g:if> ><g:link controller="Badge" action="badges" params="[medal:"all"]"><g:message code='badge.all' /></g:link></li>
			<li <g:if test="${choice.equals('bronze')}">class="item_selected_menu"</g:if> ><g:link controller="Badge" action="badges" params="[medal:"bronze"]"><g:message code='badge.bronze' /></g:link></li>
			<li <g:if test="${choice.equals('silver')}">class="item_selected_menu"</g:if> ><g:link controller="Badge" action="badges" params="[medal:"silver"]"><g:message code='badge.silver' /></g:link></li>
			<li <g:if test="${choice.equals('gold')}">class="item_selected_menu"</g:if> ><g:link controller="Badge" action="badges" params="[medal:"gold"]"><g:message code='badge.gold' /></g:link></li>		
		</ul> 
		<g:render template="templateBadge" collection="${badges}" var="badge" /> 
			<div class="pagination">
				<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
			</div>
		</div>
	</body>
</html>
