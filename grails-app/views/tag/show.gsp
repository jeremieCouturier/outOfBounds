
<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.Tag" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	
		<ul class="menuInPage"> 
		   <g:if test="${choice.equals('info')}">
		   		<li class="title">Tagged Questions</li>
		   	</g:if>
		   	<g:else>
		   		<li class="title">Tag Info</li>
		   	</g:else>
		   		
		   <li <g:if test="${choice.equals('info')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="infoTags">info</g:link></li>
		   <li <g:if test="${choice.equals('unanswered')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="unansweredTags">unanswered</g:link></li>
		</ul> 
		
		<g:if test="${choice.equals('info')}">
			
		</g:if>
		<g:if test="${choice.equals('unanswered')}">
			<g:render template="/Question/templateQuestion" collection="${questions}" var="question" />
		</g:if>
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
		</div>
		
	</body>
</html>
