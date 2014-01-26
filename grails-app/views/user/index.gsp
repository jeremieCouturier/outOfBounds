
<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>		
		<ul class="menuInPage"> 
		   <li class="title">Users</li> 
		   <li <g:if test="${choice.equals('new')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="newestUsers">newest</g:link></li>
		</ul> 
		
		<g:render template="templateUser" collection="${users}" var="user" />
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
		</div>
		
	</body>
</html>
