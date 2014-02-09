
<%@page import="java.lang.Math"%>
<%@page import="outofbounds.Configuration"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="tag.list" /></title>
		<link href="${resource(dir: 'css', file: 'listUserTag.css')}" rel="stylesheet">
	</head>
	<body>		
		<ul class="menuInPage"> 
		   	<li class="title"><g:message code="tag.list" /></li>
		   	<li <g:if test="${choice.equals('popular')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="popularTags"><g:message code='tag.popular' /></g:link></li>
			<li <g:if test="${choice.equals('name')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="nameTags"><g:message code='tag.alphabetical' /></g:link></li>
			<li <g:if test="${choice.equals('new')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="newTags"><g:message code='tag.new' /></g:link></li>		
		</ul> 

		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<div class="groupTag">
		<table>
			<!-- display 4 tags per line -->
		   <g:each var="i" in="${ (0..<(tags.size()-1)/4+1) }">
		   <g:set var="liste" value="${tags.subList(i*4, Math.min((i+1)*4, tags.size()))}" />
			<tr>
				<g:each var="j" in="${(0..<liste.size()) }">
					<td>
					<g:render template="templateTag" bean="${liste.get(j)}" var="tag" />
					</td>
				</g:each>
			</tr>
			
			</g:each>
		</table>
		</div>

			
			%{--display pagination only if there is more than one page--}%
			<g:if test="${total > Configuration.NUMBER_TAGS_PER_PAGE}">
				<div class="pagination">
					<g:paginate action="${actionName }" max="${Configuration.NUMBER_TAGS_PER_PAGE}" total="${total}"/>
				</div>
			</g:if>
		
	</body>
</html>
