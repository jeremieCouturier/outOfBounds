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
	   		<li class="title">Tagged Questions</li>
		   		
			<li <g:if test="${choice.equals('newest')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="newestTags">newest</g:link></li>
			<li <g:if test="${choice.equals('votes')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="votesTags">votes</g:link></li>
			<li <g:if test="${choice.equals('unanswered')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="unansweredTags">unanswered</g:link></li>
		</ul> 
		
		<!-- to be changed -->
		Tag: ${tag.name}<br >
		Description: ${tag.description?: message(code: "tag.no_description")}
		<sec:ifAnyGranted roles='ROLE_MODERATOR, ROLE_ADMIN'>
			<g:link controller="Tag" action="edit" params='[tag_id: "${tag.id}"]'>
	    	 	<g:message code="tag.edit_it" />
	     	</g:link>
	     </sec:ifAnyGranted>

		<g:render template="/question/templateQuestion" collection="${questions}" var="question" />
		
		<div class="pagination">
			%{-- <g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/> --}%
		</div>
		
	</body>
</html>
