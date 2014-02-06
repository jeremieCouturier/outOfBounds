<div class="title">
	<div class="username"><label>${user?.username}</label>
	</div>
	<div class="content">

		<div class="parameters">
		<label class="parameter"><g:message code="index.realname"/></label>
		<label class="parameter"><g:message code="index.location"/></label>
		<label class="parameter"><g:message code="index.email"/></label>
		<label class="parameter"><g:message code="index.website"/></label>
		</div>
		
		<div class="values">
		<label class="value">${user?.realname}</label>
		<label class="value">${user?.location}</label>
		<label class="value">${user?.email}</label>
		<label class="value">${user?.website}</label>
		</div>
		
		<div class="reputation">
		<label class="parameter_reputation"><g:message code="index.reputation"/></label><br/>
		<label class="value_reputation">${user?.reputation}</label>
		</div>
		
	</div>
</div>