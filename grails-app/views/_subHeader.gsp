<div class="subheader">
	<div class="image">
		<img src="${resource(dir: 'images', file: 'out_of_bounds.png')}" alt="Out of bounds"/>
	</div>
		<ul class="menuSubHeader">
		<li class="title">OutOfBounds</li>
	   <li <g:if test="${layout.equals('question')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="index">Questions</g:link></li>
	   <li <g:if test="${layout.equals('tag')}">class="item_selected_menu"</g:if> ><g:link controller="Tag" action="index">Tags</g:link></li>
	   <li <g:if test="${layout.equals('user')}">class="item_selected_menu"</g:if> ><g:link controller="User" action="index">Users</g:link></li>
	   <li <g:if test="${layout.equals('badge')}">class="item_selected_menu"</g:if> ><g:link controller="Badge" action="index">Badges</g:link></li>
	   <li <g:if test="${layout.equals('ask')}">class="item_selected_menu"</g:if> ><g:link controller="Question" action="create">Ask question</g:link></li>
	</ul>
</div>
