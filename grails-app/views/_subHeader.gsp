<div class="subheader">
	<div class="image">
		<img src="${resource(dir: 'images', file: 'out_of_bounds.png')}" alt="Out of bounds"/>
	</div>
		<ul class="menuSubHeader">
		<li class="title">OutOfBounds</li>
	   <li <g:if test="${layout.equals('question')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="index"><g:message code='menu.questions' /></g:link></li>
	   <li <g:if test="${layout.equals('tag')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="index"><g:message code='menu.tags' /></g:link></li>
	   <li <g:if test="${layout.equals('user')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="index"><g:message code='menu.users' /></g:link></li>
	   <li <g:if test="${layout.equals('badge')}">class="item_selected_menu"</g:if> ><g:link controller="Badge" action="badges" params="[medal:"all"]"><g:message code='menu.badges' /></g:link></li>
	   <li <g:if test="${layout.equals('ask')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="create"><g:message code='menu.ask' /></g:link></li>
	</ul>
</div>
