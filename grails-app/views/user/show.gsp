
<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'badge.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'user.css')}" rel="stylesheet">
	</head>
	<body>
		<div class="userDetailled">
			
			<g:render template="templateUserDetailled" bean="${userInstance}" var="user" />
			
			<ul class="menuInPage"> 
			   <li <g:if test="${choice.equals('questions')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="userQuestions" params='[user_id: "${userInstance?.id}"]'><g:message code='question.questions'/></g:link></li>
			   <li <g:if test="${choice.equals('answers')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="userAnswers" params='[user_id: "${userInstance?.id}"]'><g:message code='answer.answers'/></g:link></li>
			   <li <g:if test="${choice.equals('tags')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="actionName"><g:message code='tag.tags'/></g:link></li>
			   <li <g:if test="${choice.equals('badges')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="userBadges" params='[user_id: "${userInstance?.id}"]'><g:message code='badge.badges'/></g:link></li>
			</ul> 
			
			<g:if test="${choice.equals('questions')}">
				<g:render template="/question/showSummary" collection="${questions}" var="question" />
			</g:if>
			
			<g:if test="${choice.equals('answers')}">
				<g:render template="/question/showSummary" collection="${questions}" var="question" />
			</g:if>
			
			<g:if test="${choice.equals('badges')}">
					<g:render template="/badge/templateEarnedBadge" var="badge" 
					 collection="${badges}" params='[earnedDate: "${earnedDate}"]'
					/>
			</g:if>
			
		</div>
	</body>
</html>
