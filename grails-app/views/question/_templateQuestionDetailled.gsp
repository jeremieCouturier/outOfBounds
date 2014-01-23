<%@page import="outofbounds.User" %> 

<div>
    <textarea readonly="true">${question.text}</textarea>
    
    <!-- vote -->    
    <g:link controller="Post" action="upVote" params='[post_id: "${question.id}"]'>
        <span class="triangle-up"></span><br />
    </g:link>
    <label> ${question.mark} </label>
    <g:link controller="Post" action="downVote" params='[post_id: "${question.id}"]'>
        <span class="triangle-down"></span><br />
    </g:link>
    
    Tags
    <g:each var="tag" in="${question.tags}">
    	${tag.name}
    </g:each>
    
    <g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}"/>
      
    <g:if test="${question.user == currentLoggedInUser }">
		<g:link controller="Question" action="edit" params='[question_id: "${question.id}"]'>
	        <button>Edit</button>
	    </g:link>
	    <g:link controller="Question" action="deleteQuestion" params='[question_id: "${question.id}"]'>
	        <button>Delete</button>
	    </g:link>
	</g:if>
</div>
