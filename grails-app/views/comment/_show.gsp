<%@page import="outofbounds.User" %> 

<div class="comment">
	<div>
	</div>
	
	<div class="content">
	   	<label for="comment_text" class="text">${comment.text} -
			<g:link controller="user" action="show" params='[user_id: "${comment.user.id}"]'>
				${comment.user.username}
			</g:link>
			<span class="date"><g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${comment.date}" /></span>
		</label>
	    
	    <div class="modification">
	    <g:if test="${comment.user == currentLoggedInUser }">  
			<g:link controller="Comment" action="edit" params='[comment_id: "${comment.id}"]'>
		        Edit
		    </g:link>
		    <g:link controller="Comment" action="deleteComment" params='[comment_id: "${comment.id}"]'>
		        Delete
		    </g:link>
		</g:if>
		</div>
	</div>
</div>
