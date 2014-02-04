<%@page import="outofbounds.User" %> 

<div class="comment">
	<div class="vote">  
    	<g:if test="${currentLoggedInUser != null && currentLoggedInUser.upVoted.find({obj -> obj.post.id == comment.id})}">
			<g:set var="divName" value="check" />
		</g:if>
		<g:else>
     		<g:set var="divName" value="no-check" />
		</g:else>
    	<div class="${divName}">
		    <g:link controller="Post" action="upVote" params='[post_id: "${comment.id}"]'>
		        <span class="triangle-up"></span><br />
		    </g:link><br/>
		</div>
		<label class="mark"> ${comment.mark} </label><br/>
		<g:if test="${currentLoggedInUser != null && currentLoggedInUser.downVoted.find({obj -> obj.post.id == comment.id})}">
			<g:set var="divName" value="check" />
		</g:if>
		<g:else>
     		<g:set var="divName" value="no-check" />
		</g:else>
		 <div class="${divName}">
		    <g:link controller="Post" action="downVote" params='[post_id: "${comment.id}"]'>
		        <span class="triangle-down"></span>
		    </g:link>
		</div>
  	</div>

	<div class="content">
	
	   	<label for="comment_text" class="text">${comment.text}>
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
