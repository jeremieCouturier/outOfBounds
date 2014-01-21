<%@page import="outofbounds.User" %> 

<div>
    <textarea readonly="true">${comment.text}</textarea>
    
    <g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${comment.date}"/>
      
	<g:link controller="Comment" action="edit" params='[comment_id: "${comment.id}"]'>
        <button>Edit</button>
    </g:link>
    <g:link controller="Comment" action="deleteComment" params='[comment_id: "${comment.id}"]'>
        <button>Delete</button>
    </g:link>

</div>
