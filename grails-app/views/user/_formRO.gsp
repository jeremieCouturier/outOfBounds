<%@ page import="outofbounds.User" %>


<div id="form_wrapper" class="form_wrapper">
	<h3>Register</h3>
    <div class="column">
        <div>
            <label><g:message code="index.realname"/></label>
            <g:textField type="text" name="realname" value="${userInstance?.realname}"/>
        </div>
        <div>
            <label><g:message code="index.username"/></label>
            <g:textField type="text" name="username" required="" value="${userInstance?.username}"/>
        </div>
        <div>
            <label><g:message code="index.mail"/></label>
            <g:textField type="text" name="email" value="${userInstance?.mail}"/>
        </div>  
    </div>
    <div class="column">
        <div>
            <label><g:message code="index.location"/></label>
            <g:textField type="text" name="location" value="${userInstance?.location}"/>
        </div>
        <div>
            <label><g:message code="index.password"/></label>
            <g:passwordField type="password" name="password" required="" value="${userInstance?.password}"/>
        </div>  
        <div>
            <label><g:message code="index.website"/></label>
            <g:textField type="text" name="website" value="${userInstance?.website}"/>
        </div>    
    </div>
    
    <div class="recaptcha">
        <recaptcha:ifEnabled>
            <recaptcha:recaptcha theme="white"/>
        </recaptcha:ifEnabled>
	</div>
    
    <div class="bottom">
        <input type="submit" value=<g:message code="user.create.validate"/>></input>
        <g:link controller="login">You have an account already? Log in here</g:link>
        <div class="clear"></div>
    </div>
</div>


