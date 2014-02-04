<%@page import="outofbounds.User" %> 
<%@ defaultCodec="none" %>

<div class="question">
    <!-- title -->
    <div class="title">
    	<label>${question.title}</label>
    </div>
        
    <!-- vote -->
    <div class="vote">    	
    	<g:if test="${currentLoggedInUser != null && currentLoggedInUser.upVoted.find({obj -> obj.post == question})}">
			<g:set var="divName" value="check" />
		</g:if>
		<g:else>
     		<g:set var="divName" value="no-check" />
		</g:else>
    	<div class="${divName}">
		    <g:link controller="Post" action="upVote" params='[post_id: "${question.id}"]'>
		        <span class="triangle-up"></span><br />
		    </g:link><br/>
		</div>
		<label class="mark"> ${question.mark} </label><br/>
		<g:if test="${currentLoggedInUser != null && currentLoggedInUser.downVoted.find({obj -> obj.post == question})}">
			<g:set var="divName" value="check" />
		</g:if>
		<g:else>
     		<g:set var="divName" value="no-check" />
		</g:else>
		 <div class="${divName}">
		    <g:link controller="Post" action="downVote" params='[post_id: "${question.id}"]'>
		        <span class="triangle-down"></span>
		    </g:link>
		</div>
	</div>

	
	<div class="group_question">
		<!-- body -->
		<label class="text">${question.text}</label>
		
		<div class="foot_question">
		<div class="begin">
			<!-- tags -->
			<span class="tag">
				<g:each in="${question.tags.sort{a,b-> a.creationDate.compareTo(b.creationDate)}}" var="tag">
					<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
				    	<label>${tag.name}</label>
					</g:link>
				</g:each>
			</span>
			<!-- asker / date -->
			<span class="user">
				<label><g:message code ="question.asked"/> </label>
				<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}" />
				<label> <g:message code ="question.by"/> </label>
				<g:link controller="user" action="show" params='[user_id: "${question.user.id}"]'> ${question.user.username} </g:link>
			</span>
		</div>
		
		<!-- edit / delete -->
		<div class="modification">
			<!-- check that user IS login and owner or is admin -->
			<sec:ifLoggedIn>
	    	<g:if test="${question.user == currentLoggedInUser || currentLoggedInUser.isAdmin() }">
				<g:link controller="Question" action="edit" params='[question_id: "${question.id}"]'>
			        Edit
			    </g:link>
			    <g:link controller="Question" action="deleteQuestion" params='[question_id: "${question.id}"]'>
			        Delete
			    </g:link>
			</g:if>
			</sec:ifLoggedIn>
		</div>
		</div>
		
		<!-- comments -->
		<g:render template="/comment/show" collection="${questionInstance.comments}" var="comment" />
		
		<sec:ifLoggedIn>
	    	<g:render template="/comment/add" bean="${questionInstance}" var="post" />
	    </sec:ifLoggedIn>
		
	</div>
</div>
