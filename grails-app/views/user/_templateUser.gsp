<div class="user">
	<g:link controller="User" action="show" params='[user_id: "${user.id}"]'>
		${user?.username} 
	</g:link>

	<div class="text">
		<span class="bold"> <g:message code='index.reputation'/> : </span> ${user.reputation } <br/>
		<span class="bold"> <g:message code='index.realname'/> : </span>${user.realname }
	</div>
</div>
