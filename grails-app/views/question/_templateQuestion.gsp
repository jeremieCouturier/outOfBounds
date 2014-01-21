<%@page import="outofbounds.User" %> 

<div>
    <textarea readonly="true">${question.text}</textarea>
    
    <g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}"/>
      
	<g:link controller="Question" action="edit" params='[question_id: "${question.id}"]'>
        <button>Edit</button>
    </g:link>
    <g:link controller="Question" action="deleteQuestion" params='[question_id: "${question.id}"]'>
        <button>Delete</button>
    </g:link>
    
</div>
