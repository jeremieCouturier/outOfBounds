<div id='cssmenu'>
<ul>
	<li class='active'><a href='${resource(uri: "/")}/'><span>Home</span></a></li>

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
   

   <li><a href='#'><span>About</span></a></li>
   <li class='last'><a href='#'><span>Contact</span></a></li>
</ul>
</div>
