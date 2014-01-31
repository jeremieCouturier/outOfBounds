
<div class="new_comment">
	<g:form controller="Post" action="addComment" id="${post.id}">
	    <textarea name="text" placeholder="Add a comment ..."></textarea><br />
	    <button type="submit"><g:message code="question.add_comment"/></button>
	</g:form>
</div>