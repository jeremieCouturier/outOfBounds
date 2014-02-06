<div class="user">
	<g:img file="users/${user?.avatarpath}"/>
	<div class="reputation">
	${user?.reputation}
	</div>
	<g:link controller="User" action="show" params='[user_id: "${user.id}"]'>
		${user?.username} 
	</g:link>
</div>