<div class="badge">
	<label class="text">${badge?.medal}</label>
	<g:link controller="Badge" action="show" params='[badge_id: "${badge.id}"]'>
	${badge?.name}
	</g:link>
</div>