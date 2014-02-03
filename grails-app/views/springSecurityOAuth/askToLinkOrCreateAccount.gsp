<head>
    <meta name='layout' content='main'/>
    <title>Create or Link Account</title>
	<link href="${resource(dir: 'css', file: 'formLogSign.css')}" rel="stylesheet">
</head>

<body>

<div class='body'>
    <g:if test='${flash.error}'>
        <div class="errors">${flash.error}</div>
    </g:if>

    <label class="message"><g:message code="springSecurity.oauth.registration.link.not.exists" args="[session.springSecurityOAuthToken.providerName]"/></label>
    <br/>
    
    <g:form url="[resource:userInstance, action:'save']" class="register">
		<g:render template="/user/formRO"/>	
	</g:form>

    
    <!-- <div id="form_wrapper_google" class="form_wrapper_google" >
        <g:hasErrors bean="${createAccountCommand}">
        <div class="errors">
            <g:renderErrors bean="${createAccountCommand}" as="list"/>
        </div>
        </g:hasErrors>
		<g:form class="login active" action="createAccount" method="post" autocomplete="off">
		    <h3><g:message code="springSecurity.oauth.registration.create.legend" /></h3>
		    <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'username', 'error')} ">
                <label for='username'><g:message code="OAuthCreateAccountCommand.username.label" />:</label>
                <g:textField name='username' value='${createAccountCommand?.username}'/>
		    </div>
		    <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'password1', 'error')} ">
                <label for='password1'><g:message code="OAuthCreateAccountCommand.password1.label"/>:</label>
		        <g:passwordField name='password1' value='${createAccountCommand?.password1}'/>
		    </div>
		    <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'password2', 'error')} ">
                <label for='password2'><g:message code="OAuthCreateAccountCommand.password2.label"/>:</label>
		        <g:passwordField name='password2' value='${createAccountCommand?.password2}'/>
		    </div>
		    <div class="bottom">
		    	<g:submitButton name="${message(code: 'springSecurity.oauth.registration.create.button')}"/>
		        <g:link controller="login" action="auth"><g:message code="springSecurity.oauth.registration.back"/></g:link>
		        <div class="clear"></div>
		    </div>
		</g:form>
	</div>-->

    <div id="form_wrapper_google" class="form_wrapper_google">
        <g:hasErrors bean="${linkAccountCommand}">
        <div class="errors">
            <g:renderErrors bean="${linkAccountCommand}" as="list"/>
        </div>
        </g:hasErrors>
    
        <g:form class="login active" action="linkAccount" method="post" autocomplete="off">
		    <h3><g:message code="springSecurity.oauth.registration.login.legend"/></h3>
		    <div class="fieldcontain ${hasErrors(bean: linkAccountCommand, field: 'username', 'error')} ">
		        <label for='username'><g:message code="OAuthLinkAccountCommand.username.label" />:</label>
		        <g:textField name='username' value='${linkAccountCommand?.username}'/>
		    </div>
		    <div class="fieldcontain ${hasErrors(bean: linkAccountCommand, field: 'password', 'error')} ">
                <label for='password'><g:message code="OAuthLinkAccountCommand.password.label"/>:
		        </label>
		        <g:passwordField name='password' value='${linkAccountCommand?.password}'/>
		    </div>
		    <div class="bottom">
		        <g:submitButton name="${message(code: 'springSecurity.oauth.registration.login.button')}"/>
		        <g:link controller="login" action="auth"><g:message code="springSecurity.oauth.registration.back"/></g:link>
		        <div class="clear"></div>
		    </div>
		</g:form>
	</div>
</div>
</body>
