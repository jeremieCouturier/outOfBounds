<%@ page import="outofbounds.Badge" %>

<div class="fieldcontain ${hasErrors(bean: badgeInstance, field: 'user', 'error')} required">
	<label for="medal">
		<g:message code="badge.medal.label" default="Medal" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="medal" name="medal.id" from="${outofbounds.Badge.BadgeMedal}" optionKey="id" required="" value="${badgeInstance?.medal}" />
	<label for="user">
		<g:message code="badge.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${outofbounds.User.list()}" optionKey="id" required="" value="${badgeInstance?.user?.id}" class="many-to-one"/>
</div>

