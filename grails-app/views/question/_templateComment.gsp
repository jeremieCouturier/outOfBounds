<%@page import="outofbounds.User" %> 

<div class="comment">
    <label for="comment_text" class="text">${comment.text}</label>
    
    <br/>
    
    <span class="user">
		<label>asked </label>
		<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${comment.date}" />
		<label> by </label>
		<g:link controller="user" action="profile">
			${comment.user.username}
		</g:link>
	</span>
    
    <g:if test="${comment.user == currentLoggedInUser }">  
		<g:link controller="Comment" action="edit" params='[comment_id: "${comment.id}"]'>
	        <button>Edit</button>
	    </g:link>
	    <g:link controller="Comment" action="deleteComment" params='[comment_id: "${comment.id}"]'>
	        <button>Delete</button>
	    </g:link>
	</g:if>

</div>
