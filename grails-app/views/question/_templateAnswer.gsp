<%@page import="outofbounds.User" %> 

<div class="answer">
    <!-- vote -->
    <div class="vote">
	    <g:link controller="Post" action="upVote" params='[post_id: "${answer.id}"]'>
	        <span class="triangle-up"></span><br />
	    </g:link><br/>
	    <label class="mark"> ${answer.mark} </label><br/>
	    <g:link controller="Post" action="downVote" params='[post_id: "${answer.id}"]'>
	        <span class="triangle-down"></span>
	    </g:link>
	</div>
	
	<div class="block">
		<div class="text_detailled">
			<label class="text">${answer.text}</label>
		</div>
		
		
		<span class="user">
			<label>answered </label>
			<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${answer.date}" />
			<label> by </label>
			<g:link controller="user" action="profile"> ${answer.user.username} </g:link>
		
	    	<g:if test="${answer.user == currentLoggedInUser }">
				<g:link controller="Answer" action="edit" params='[answer_id: "${answer.id}"]'>
			        <button>Edit</button>
			    </g:link>
			    <g:link controller="Answer" action="deleteAnswer" params='[answer_id: "${answer.id}"]'>
			        <button>Delete</button>
			    </g:link>
			</g:if>
		</span>
	</div>
    
    <g:render template="templateComment" collection="${answer.comments}" var="comment" />
    
    <g:form controller="Comment" action="createCommentForAnswer" id="${answer.id}">
        <textarea name="comment_text" placeholder="Add a comment ..." required></textarea><br />
        <button type="submit">Add the comment</button>
    </g:form>
    
</div>
