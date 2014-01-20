<%@page import="outofbounds.User" %> 

<div>
    <textarea readonly="true">${answer.text}</textarea>
      
	<g:link controller="Answer" action="edit" params='[answer_id: "${answer.id}"]'>
        <button>Edit</button>
    </g:link>
    <g:link controller="Answer" action="deleteAnswer" params='[answer_id: "${answer.id}"]'>
        <button>Delete</button>
    </g:link>
</div>
