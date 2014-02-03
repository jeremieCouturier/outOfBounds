
<div class="badge">
	<g:render template="../badge/medal" model="${badge}" var="badge" />
	<g:link controller="Badge" action="show" params='[badge_id: "${badge?.id}"]'>
	<span class="name">${badge?.name}</span>
	</g:link>
	<span class="description">${badge?.description}</span>
<span class="date">
<g:if test="${params?.earnedDate != null}" >
	obtenu le <g:formatDate format="dd-MM-yyyy" date="${params?.earnedDate}" />
</g:if>
<g:else>
	disponible depuis le <g:formatDate format="dd-MM-yyyy" date="${badge?.dateCreated}" />
</g:else>
</span>
</div>