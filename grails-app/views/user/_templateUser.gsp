<div class="user">
	<g:link controller="User" action="show" params='[user_id: "${user.id}"]'>
		${user?.username} 
	</g:link>
	<div class="reputation">
		<g:message code='user.reputation' args="[user?.reputation]" />
	</div>
</div>
