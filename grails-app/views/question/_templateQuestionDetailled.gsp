<%@page import="outofbounds.User" %> 

<div class="question">
    
    <div class="text_detailled">
		<label class="title">${question.title}</label>
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
	
	<div class="block">
		<div class="text_detailled">
			<label class="text">${question.text}</label>
		</div>
		
		<span class="tag">
			<g:each in="${question.tags}" var="tag">
				<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
			    	<label>${tag.name}</label>
				</g:link>
			</g:each>
		</span>
		
		<span class="user">
			<label>asked </label>
			<g:formatDate format="dd-MM-yyyy HH:mm:ss" date="${question.date}" />
			<label> by </label>
			<g:link controller="user" action="profile"> ${question.user.username} </g:link>
		
	    	<g:if test="${question.user == currentLoggedInUser }">
				<g:link controller="Question" action="edit" params='[question_id: "${question.id}"]'>
			        <button>Edit</button>
			    </g:link>
			    <g:link controller="Question" action="deleteQuestion" params='[question_id: "${question.id}"]'>
			        <button>Delete</button>
			    </g:link>
			</g:if>
		</span>
	</div>
</div>
