<%@page import="outofbounds.User" %> 

<div>
    <textarea readonly="true">${comment.text}</textarea>
    
    <!-- vote -->
    <label> ${comment.mark} </label>
    
    <g:link controller="Post" action="upVote" params='[post_id: "${comment.id}"]'>
        <button>+</button>
    </g:link>
    <g:link controller="Post" action="downVote" params='[post_id: "${comment.id}"]'>
        <button>-</button>
    </g:link>
    
    <g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${comment.date}"/>
    
    <g:if test="${comment.user == currentLoggedInUser }">  
		<g:link controller="Comment" action="edit" params='[comment_id: "${comment.id}"]'>
	        <button>Edit</button>
	    </g:link>
	    <g:link controller="Comment" action="deleteComment" params='[comment_id: "${comment.id}"]'>
	        <button>Delete</button>
	    </g:link>
	</g:if>

</div>