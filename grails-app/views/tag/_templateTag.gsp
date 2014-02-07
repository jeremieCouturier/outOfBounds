<%@page import="outofbounds.Text"%>


<div class="tag">
	<g:link controller="Tag" action="show" params='[tag_id: "${tag.id}"]'>
		${tag.name} (x${tag.questions.size()})
	</g:link>
	<div class="text">
		${Text.summaryText(tag.description)}
	</div>
</div>
