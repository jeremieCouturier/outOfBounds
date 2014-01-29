<%@page import="outofbounds.User" %> 
<%@ defaultCodec="none" %>

<div class="question">
    
    <div class="title">
    	<label>${question.title}</label>
    </div>
        
    <!-- vote -->
    <div class="vote">
	    <g:link controller="Post" action="upVote" params='[post_id: "${question.id}"]'>
	        <span class="triangle-up"></span><br />
	    </g:link><br/>
	    <label class="mark"> ${question.mark} </label><br/>
	    <g:link controller="Post" action="downVote" params='[post_id: "${question.id}"]'>
	        <span class="triangle-down"></span>
	    </g:link>
	</div>
	
	<div class="group_question">
	
		<label class="text">${question.text}</label>
		
		<div class="foot_question">
		<div class="begin">
			<span class="tag">
				<g:each in="${question.tags}" var="tag">
					<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
				    	<label>${tag.name}</label>
					</g:link>
				</g:each>
			</span>
				
			<span class="user">
				<label><g:message code ="question.asked"/> </label>
				<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}" />
				<label> <g:message code ="question.by"/> </label>
				<g:link controller="user" action="show" params='[user_id: "${question.user.id}"]'> ${question.user.username} </g:link>
			</span>
		</div>
		
		<div class="modification">
			<!-- check that user IS login and owner or is admin -->
			<sec:ifLoggedIn>
	    	<g:if test="${question.user == currentLoggedInUser || currentLoggedInUser.isAdmin() }">
				<g:link controller="Question" action="edit" params='[question_id: "${question.id}"]'>
			        Edit
			    </g:link>
			    <g:link controller="Question" action="delete" params='[question_id: "${question.id}"]'>
			        Delete
			    </g:link>
			</g:if>
			</sec:ifLoggedIn>
		</div>
		</div>
		
		<g:render template="templateComment" collection="${questionInstance.comments}" var="comment" />
		
		<sec:ifLoggedIn>
	    	<g:render template="templateAddComment" bean="${questionInstance}" var="post" />
	    </sec:ifLoggedIn>
		
	</div>
</div>
