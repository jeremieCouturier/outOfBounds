<%@page import="outofbounds.User" %> 

<div>
    <textarea readonly="true">${question.text}</textarea>
    
    <!-- vote -->
    <label> ${question.mark} </label>
    
    <g:link controller="Post" action="upVote" params='[post_id: "${question.id}"]'>
        <button>+</button>
    </g:link>
    <g:link controller="Post" action="downVote" params='[post_id: "${question.id}"]'>
        <button>-</button>
    </g:link>
    
    <g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}"/>
      
	<g:link controller="Question" action="edit" params='[question_id: "${question.id}"]'>
        <button>Edit</button>
    </g:link>
    <g:link controller="Question" action="deleteQuestion" params='[question_id: "${question.id}"]'>
        <button>Delete</button>
    </g:link>
    
</div>
