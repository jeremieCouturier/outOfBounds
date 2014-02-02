<%@ page import="outofbounds.Badge" %>

<div class="fieldcontain ${hasErrors(bean: badgeInstance, field: 'user', 'error')} required">
	<label for="medal">
		<g:message code="badge.medal.label" default="Medal" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="medal" name="medal.id" from="${outofbounds.Badge.BadgeMedal}" required="" value="${badgeInstance?.medal}" />
</div>
