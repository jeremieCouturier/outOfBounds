
<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'subheader.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'header.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'menu.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'pagination.css')}" rel="stylesheet">
	</head>
	<body>
		<g:render template="/headerSite"/>
		<g:render template="/subHeader"  model="[text: 'users']"/>
		
		<ul class="menu_question"> 
		   <li class="title">Users</li> 
		   <!-- first choice -->
		   <g:if test="${choice.equals('new')}">
			  	<li class="item_selected_menu">new users</li> 
			</g:if>
			<g:else>
		 		<li><g:link controller="User" action="newUsers">newest</g:link></li>
		 	</g:else>
		</ul> 
		
		<g:render template="templateUser" collection="${users}" var="user" />
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
		</div>
		
	</body>
</html>
