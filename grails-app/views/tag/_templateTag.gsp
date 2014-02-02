<div class="tag">
	<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
		${tag.name} (x${tag.questions.size()})
	</g:link>
	<div class="text">
		<g:if test="${tag.description?.size() > 150}"> ${tag.description.substring(0, 150) + "..." } </g:if>
		<g:else> ${tag.description} </g:else>
	</div>
</div>
