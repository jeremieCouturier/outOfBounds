<%@page import="outofbounds.User" %> 
<%@ defaultCodec="none" %>

<div class="answer">
    <!-- vote -->
    <div class="vote">
    	<g:set var="typePost_id" value="${"[class outofbounds.Answer, " + answer.id + ']'}"/> 
		<g:if test="${flash.error && flash.args == typePost_id}">
		  	<div class="alert alert-error" style="display: block">${flash.error}</div>
		</g:if> 
		<div class="check"> 
		    <g:link controller="Post" action="upVote" params='[post_id: "${answer.id}"]'>
		        <span class="triangle-up"></span><br />
		    </g:link><br/>
		    <label class="mark"> ${answer.mark} </label><br/>
		    <g:link controller="Post" action="downVote" params='[post_id: "${answer.id}"]'>
		        <span class="triangle-down"></span>
		    </g:link>
		</div>
	    
	    <!-- correct answer -->
	    <!-- if it's user question, display both accept/unaccept -->
	    <g:if test="${answer.user == currentLoggedInUser}" >
		    <g:if test="${ answer.question.correctAnswer != null && answer.question.correctAnswer.id == answer.id }">
		    	<g:link class="accept" controller="Question" action="unaccept" params='[answer_id: "${answer.id}"]' />
		    </g:if>
		    <g:else>
		    	<g:link class="unaccept" controller="Question" action="accept" params='[answer_id: "${answer.id}"]' />
		    </g:else>
		</g:if>
		<g:else>
			<!-- else only display accepted icon, if any -->
			<g:if test="${ answer.question.correctAnswer != null && answer.question.correctAnswer.id == answer.id }">
				<!-- it shouldn't be a label.. but what ever -->
		    	<label class="accept" />
		    </g:if>
		</g:else>
	    
	</div>
	
	<div class="group_answer">
		<!-- text -->
		<label class="text">${answer.text}</label>		
		
		<!-- date / autor -->
		<div class="foot_answer">
		<div class="begin">
			<span class="user">
				<label>answered </label>
				<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${answer.date}" />
				<label> by </label>
				<g:link controller="user" action="show" params='[user_id: "${answer.user.id}"]'> ${answer.user.username} </g:link>
			</span>
		</div>
		
		<!-- modification -->
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
		
		<!-- comments -->
		<g:render template="/comment/show" collection="${answer.comments}" var="comment" /> 

		<!-- add response button -->
		<sec:ifLoggedIn>  
	    	<g:render template="/comment/add" bean="${answer}" var="post" />
	    </sec:ifLoggedIn>
		
	</div>    
</div>
