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
	
	<div class="group_answer">
		<label class="text">${answer.text}</label>		
		
		<div class="foot_answer">
		<div class="begin">
			<span class="user">
				<label>answered </label>
				<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${answer.date}" />
				<label> by </label>
				<g:link controller="user" action="show" params='[user_id: "${answer.user.id}"]'> ${answer.user.username} </g:link>
			</span>
		</div>
		
		<div class="modification">
	    	<g:if test="${answer.user == currentLoggedInUser }">
				<g:link controller="Answer" action="edit" params='[answer_id: "${answer.id}"]'>
			        Edit
			    </g:link>
			    <g:link controller="Answer" action="deleteAnswer" params='[answer_id: "${answer.id}"]'>
			        Delete
			    </g:link>
			</g:if>
		</div>
		</div>
		
		<g:render template="templateComment" collection="${answer.comments}" var="comment" /> 
		<sec:ifLoggedIn>  
	    	<g:render template="templateAddComment" bean="${answer}" var="post" />
	    </sec:ifLoggedIn>
		
	</div>    
</div>
