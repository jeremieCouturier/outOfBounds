
<%@page import="java.lang.Math"%>
<%@page import="outofbounds.Configuration"%>
<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
	</head>
	<body>		
		<ul class="menuInPage"> 
		   	<li class="title"><g:message code="default.list.label" args="[entityName]" /></li>
		   <li <g:if test="${choice.equals('new')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="newestUsers">newest</g:link></li>
		</ul> 
		
		<div class="groupUser">
			<g:each var="i" in="${ (0..<(users.size()-1)/4+1) }">
				<g:render template="templateUser" collection="${users.subList(i*4, Math.min((i+1)*4, users.size()))}" var="user" />
			</g:each>
		</div>
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE*4}" total="${total}"/>
		</div>
		
	</body>
</html>
