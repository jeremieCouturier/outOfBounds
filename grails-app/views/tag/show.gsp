<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.Tag" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<ul class="menuInPage"> 
	   		<li class="title">Tagged Questions</li>
		   		
			<li <g:if test="${choice.equals('newest')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="newestQuestions" params='[tag_id: "${tag.id}"]'>newest</g:link></li>
			<li <g:if test="${choice.equals('popular')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="popularQuestions" params='[tag_id: "${tag.id}"]'>popular</g:link></li>
			<li <g:if test="${choice.equals('unanswered')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="unansweredQuestions" params='[tag_id: "${tag.id}"]'>unanswered</g:link></li>
		</ul> 
		
		<div class="description">
			<label class="name">${tag.name}</label>
			<label class="description">${tag.description?: message(code: "tag.no_description")}</label>
				
			<sec:ifAnyGranted roles='ROLE_MODERATOR, ROLE_ADMIN'>
				<g:link controller="Tag" action="edit" params='[tag_id: "${tag.id}"]'>
		    	 	<g:message code="tag.edit_it" />
		     	</g:link>
		    </sec:ifAnyGranted>
		</div>

	    <g:if test="${total == 0}">
	    	<g:message code="tag.no_question_found" args="[choice, tag.name]" />
	    </g:if>
	    <g:else>
			<g:render template="/question/showSummary" collection="${questions}" var="question" />
		
			<div class="pagination">
				<g:paginate action="${actionName }" params='[tag_id: "${tag.id}"]' max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
			</div>
		</g:else>
	</body>
</html>
