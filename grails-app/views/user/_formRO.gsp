<%@ page import="outofbounds.User" %>


<div id="form_wrapper" class="form_wrapper">
	<h3><g:message code="user.register"/></h3>
	<fieldset class="form">
    <div class="column">
        <div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'realname', 'error')} required">
            <label><g:message code="index.realname"/></label>
            <g:textField type="text" name="realname" value="${userInstance?.realname}"/>
        </div>
        <div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'username', 'error')} required">
            <label><g:message code="index.username"/></label>
            <g:textField type="text" name="username" required="" value="${userInstance?.username}"/>
        </div>
        <div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'email', 'error')} required">
            <label><g:message code="index.email"/></label>
            <g:textField type="text" name="email" value="${userInstance?.email}"/>
        </div>  
    </div>
    <div class="column">
        <div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'location', 'error')} required">
            <label><g:message code="index.location"/></label>
            <g:textField type="text" name="location" value="${userInstance?.location}"/>
        </div>
        <div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'password', 'error')} required">
            <label><g:message code="index.password"/></label>
            <g:passwordField type="password" name="password" required="" value="${userInstance?.password}"/>
        </div>  
        <div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'website', 'error')} required">
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
        <g:link controller="login"><g:message code="user.login.create"/></g:link>
        <div class="clear"></div>
    </div>
    </fieldset>
</div>


