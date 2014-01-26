<%@ page import="outofbounds.User" %>


<div id="form_wrapper" class="form_wrapper">
	<h3>Register</h3>
    <div class="column">
        <div>
            <label>First Name :</label>
            <input type="text" />
        </div>
        <div>
            <label>Last Name :</label>
            <input type="text" />
        </div>
        <div>
            <label>Website :</label>
            <input type="text" value="http://"/>
        </div>
    </div>
    <div class="column">
        <div>
            <label><g:message code="index.username"/></label>
            <g:textField type="text" name="username" value="${userInstance?.username}"/>
        </div>
        <div>
            <label>Email :</label>
            <input type="text" />
        </div>
        <div>
            <label><g:message code="index.password"/></label>
            <g:passwordField type="password" name="password" required="" value="${userInstance?.password}"/>
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


