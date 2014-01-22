<%@page import="outofbounds.User" %> 

<div>
    <textarea readonly="true">${answer.text}</textarea>
    
    <!-- vote -->
    <label> ${answer.mark} </label>
    
    <g:link controller="Post" action="upVote" params='[post_id: "${answer.id}", question_id: "${answer.question.id }"]'>
        <button>+</button>
    </g:link>
    <g:link controller="Post" action="downVote" params='[post_id: "${answer.id}", question_id: "${answer.question.id }"]'>
        <button>-</button>
    </g:link>
    
    <g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${answer.date}"/>
      
	<g:link controller="Answer" action="edit" params='[answer_id: "${answer.id}"]'>
        <button>Edit</button>
    </g:link>
    <g:link controller="Answer" action="deleteAnswer" params='[answer_id: "${answer.id}"]'>
        <button>Delete</button>
    </g:link>
    
    <div>
        <div>
            <h2>${answer.comments.size()} Comment<g:if test="${answer.comments.size() > 1}">s</g:if></h2>
        </div>
        <g:render template="templateComment" collection="${answer.comments}" var="comment" />
    </div>
    
    <g:form controller="Comment" action="createCommentForAnswer" id="${answer.id}">
        <textarea name="comment_text" placeholder="Add a comment ..." required></textarea><br />
        <button type="submit">Add the comment</button>
    </g:form>
    
</div>
