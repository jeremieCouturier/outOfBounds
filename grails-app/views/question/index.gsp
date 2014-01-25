
<%@page import="outOfBounds.Configuration"%>
<%@ page import="outofbounds.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'listQuestion.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'subheader.css')}" rel="stylesheet">
		<link href="${resource(dir: 'css', file: 'header.css')}" rel="stylesheet">
	</head>
	<body>
		<g:render template="/headerSite"/>
		<g:render template="/subheader"  model="[text: 'question']"/>
		
		<ul class="menu_question"> 
		   <li class="title">All Questions</li> 
		   <!-- first choice -->
		   <g:if test="${choice.equals('newest')}">
			  	<li class="item_selected_menu">newest</li> 
			</g:if>
			<g:else>
		 		<li><g:link controller="Question" action="newestQuestions">newest</g:link></li>
		 	</g:else>
		 	
		 	<!-- second choice -->
		 	<g:if test="${choice.equals('votes')}">
		   		<li class="item_selected_menu">votes</li> 
		   	</g:if>
			<g:else>
		 		<li><g:link controller="Question" action="voteQuestions">votes</g:link></li> 
		 	</g:else>
		 	
		 	<!-- third choice -->
		 	<g:if test="${choice.equals('unanswered')}">
		   		<li class="item_selected_menu">unanswered</li> 
		   	</g:if>
			<g:else>
		 		<li><g:link controller="Question" action="unansweredQuestions">unanswered</g:link></li> 
		 	</g:else>
		</ul> 
		
		<g:render template="templateQuestion" collection="${questions}" var="question" />
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE}" total="${total}"/>
		</div>
	</body>
</html>
