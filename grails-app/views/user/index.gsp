
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
		   	<li class="title"><g:message code="tag.list" /></li>
		   	<li <g:if test="${choice.equals('reputation')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="reputationUser"><g:message code='user.sort.reputation' /></g:link></li>
			<li <g:if test="${choice.equals('username')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="usernameUser"><g:message code='user.sort.username' /></g:link></li>
			<li <g:if test="${choice.equals('new')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="newUser"><g:message code='user.sort.new' /></g:link></li>		
		</ul> 
		
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		

		<div class="groupUser">
		<table>
		   <g:each var="i" in="${ (0..<(users.size()-1)/4+1) }">
		   <g:set var="liste" value="${users.subList(i*4, Math.min((i+1)*4, users.size()))}" />
			<tr>
				<g:each var="j" in="${(0..<liste.size()) }">
					<td>
					<g:render template="templateUser" bean="${liste.get(j)}" var="user" />
					</td>
				</g:each>
			</tr>
			
			</g:each>
		</table>
		</div>
		
		<div class="pagination">
			<g:paginate action="${actionName}" max="${Configuration.NUMBER_ITEM_PER_PAGE*4}" total="${total}"/>
		</div>
		
	</body>
</html>
