<%@ page import="outofbounds.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required" align="center">
	<label for="username">
		<g:message code="index.username"/>
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required" align="center">
	<label for="password">
		<g:message code="index.password"/>
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField  name="password" required="" value="${userInstance?.password}"/>
</div>

<div align="center">
    <br>
        <recaptcha:ifEnabled>
            <recaptcha:recaptcha theme="white"/>
        </recaptcha:ifEnabled>
    </br>
</div>

