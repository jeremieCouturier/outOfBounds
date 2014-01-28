<div class="tag">
	<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
		${tag.name} (x${tag.questions.size()})
	</g:link>
</div>
