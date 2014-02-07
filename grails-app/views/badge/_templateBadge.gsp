<parameter name="earnedDate" value="null" />
<div class="badge">
	<div class="medal">
		<g:render template="../badge/medal" model="${badge}" var="badge" />
	</div>
	
	<div class="content">
		<g:link controller="Badge" action="show" params='[badge_id: "${badge?.id}"]'>
		<span class="name"><g:message code="${badge?.name}" /></span>
		</g:link>

		<div class="description"><g:message code="${badge?.description}" /></div>
		<div class="date">
			<g:message code ="badge.created"/>
			<g:formatDate formatName="default.date.format" date="${badge?.dateCreated}" />
		</div>
	</div>
</div>