
<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'badge.css')}" rel="stylesheet">
	</head>
	<body>
		<div class="userDetailled">
			<div class="title">
				<label>${userInstance?.username}</label>
			</div>
			
			<ul class="menuInPage"> 
			   <li <g:if test="${choice.equals('questions')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="userQuestions" params='[user_id: "${userInstance?.id}"]'>questions</g:link></li>
			   <li <g:if test="${choice.equals('answers')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="userAnswers" params='[user_id: "${userInstance?.id}"]'>answers</g:link></li>
			   <li <g:if test="${choice.equals('tags')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="actionName">tags</g:link></li>
			   <li <g:if test="${choice.equals('badges')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="userBadges" params='[user_id: "${userInstance?.id}"]'>badges</g:link></li>
			</ul> 
			
			<g:if test="${choice.equals('questions')}">
				<g:render template="/question/showSummary" collection="${questions}" var="question" />
			</g:if>
			
			<g:if test="${choice.equals('answers')}">
				<g:render template="/question/showSummary" collection="${questions}" var="question" />
			</g:if>
			
			<g:if test="${choice.equals('badges')}">
				<g:render template="/badge/templateBadge" collection="${badges}" var="badge" />
			</g:if>
			
		</div>
	</body>
</html>
