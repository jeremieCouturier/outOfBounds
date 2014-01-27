
<%@page import="java.lang.Math"%>
<%@page import="outOfBounds.Configuration"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
	</head>
	<body>		
		<ul class="menuInPage"> 
		   	<li class="title">Tags</li> 
		   	<li <g:if test="${choice.equals('popular')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="popularTags">newest</g:link></li>
			<li <g:if test="${choice.equals('name')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="nameTags">newest</g:link></li>
			<li <g:if test="${choice.equals('new')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="newTags">newest</g:link></li>		
		</ul> 
		
		<div class="groupTag">
			<g:each var="i" in="${ (0..<(tags.size()-1)/4+1) }">
				<g:render template="templateTag" collection="${tags.subList(i*4, Math.min((i+1)*4, tags.size()))}" var="tag" />
			</g:each>
		</div>
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE*4}" total="${total}"/>
		</div>
		
	</body>
</html>
