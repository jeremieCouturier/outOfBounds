<div class="subheader">
	<div class="image">
		<img src="${resource(dir: 'images', file: 'out_of_bounds.png')}" alt="Out of bounds"/>
	</div>
	
	<ul class="menu"> 
			
		   <li class="title">OutOfBounds</li> 

			<g:if test="${text != null && text.equals('question') }">
		   <li class="item_selected_menu">Questions</li> 
			</g:if>
			<g:else>
			<li><g:link controller="Question" action="index">Questions</g:link></li> 
			</g:else>
		   
		   
		   <li><g:link controller="Tag" action="index">Tags</g:link></li> 
		   
		   <g:if test="${text != null && text.equals('users') }">
		   <li class="item_selected_menu">Users</li> 
			</g:if>
			<g:else>
			<li><g:link controller="User" action="index">Users</g:link></li> 
			</g:else>
		   
		   
		   <g:if test="${text != null && text.equals('ask') }">
		   <li class="item_selected_menu">Ask Question</li> 
			</g:if>
			<g:else>
			<li><g:link controller="Question" action="create">Ask Question</g:link></li> 
			</g:else>
	</ul>
</div>
