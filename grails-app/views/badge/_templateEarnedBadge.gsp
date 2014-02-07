<parameter name="earnedDate" value="null" />
<div class="badge">
	<div class="medal">
		<g:render template="../badge/medal" collection="${badge?.badge}" var="badge" />
	</div>
	
	<div class="content">
		<g:link controller="Badge" action="show" params='[badge_id: "${badge?.badge?.id}"]'>
		<span class="name"><g:message code="${badge?.badge?.name}" /></span>
		</g:link>

		<div class="description"><g:message code="${badge?.badge?.description}" /></div>
		<div class="date">
			<g:message code ="badge.earned"/>
			<g:formatDate formatName="default.date.format" date="${badge?.earnedDate}" />
		</div>
	</div>
</div>