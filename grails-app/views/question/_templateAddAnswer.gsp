
<div class="new_answer">
	<g:form controller="Answer" action="create" id="${question.id}">
	<div class="text">
	    <textarea name="text" placeholder="Add an answer ..."></textarea><br />
	 </div>
	    <button type="submit"><g:message code="question.post_answer"/></button>
	</g:form>
</div>