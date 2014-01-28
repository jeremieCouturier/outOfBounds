
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
		   	<li <g:if test="${choice.equals('popular')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="popularTags"><g:message code='tag.popular' /></g:link></li>
			<li <g:if test="${choice.equals('name')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="nameTags"><g:message code='tag.name' /></g:link></li>
			<li <g:if test="${choice.equals('newest')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="newTags"><g:message code='tag.new' /></g:link></li>		
		</ul> 
		
		<div class="groupTag">
			<g:render template="templateTag" collection="${tags}" var="tag" />

			%{-- why? WHY this v ?! --}%
			%{-- <g:each var="i" in="${ (0..<(tags.size()-1)/4+1) }">
				<g:render template="templateTag" collection="${tags.subList(0*4, Math.min((0+1)*4, tags.size()))}" var="tag" />
			</g:each> --}%
		</div>
		
		<div class="pagination">
			<g:paginate action="${actionName }" max="${Configuration.NUMBER_ITEM_PER_PAGE*4}" total="${total}"/>
		</div>
		
	</body>
</html>
