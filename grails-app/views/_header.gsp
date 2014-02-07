<div id='cssmenu'>
<ul>
	<li class='active'><a href="${resource(uri: '/')}/"><g:message code="index.home" /></a></li>

	<sec:ifLoggedIn>
		<li><g:link controller="user" action="show">
			<g:message code="user.my_profile" />
		</g:link></li>

		<li><g:link controller="logout" action="index">
			<g:message code="index.log_out" />
		</g:link></li>	
	</sec:ifLoggedIn>

	<sec:ifNotLoggedIn>
		<li><g:link controller="user" action="create">
			<g:message code="index.sign_up" />
		</g:link></li>


		<li><g:link controller="login" action="index">
			<g:message code="index.log_in" />
		</g:link></li>

	</sec:ifNotLoggedIn>
   

   <li><g:link mapping="about"><span><g:message code="header.about" /></span></g:link></li>

</ul>
</div>
