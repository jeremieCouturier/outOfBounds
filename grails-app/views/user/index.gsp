
<%@page import="java.lang.Math"%>
<%@page import="outofbounds.Configuration"%>
<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="user.list" /></title>
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
	</head>
	<body>		
		<ul class="menuInPage"> 
		   	<li class="title"><g:message code="user.list" /></li>
		</ul> 
		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		

		<div class="groupUser">
			<g:render template="templateUser" collection="${users}" var="user" />
		</div>
		
		<div class="pagination">
			<g:paginate action="${actionName}" max="${Configuration.NUMBER_ITEM_PER_PAGE*4}" total="${total}"/>
		</div>
		
	</body>
</html>
