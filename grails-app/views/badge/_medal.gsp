<%@ page import="outofbounds.Badge.BadgeMedal" %>
<span class="medal">
	<g:if test="${badge?.medal == BadgeMedal.Bronze}" >
	<g:link controller="Badge" action="badges" params='[medal: "bronze"]'>
		<g:img file="medals/bronze.jpg"/>
	</g:link>
	</g:if>
	<g:elseif test="${badge?.medal == BadgeMedal.Silver}" >
	<g:link controller="Badge" action="badges" params='[medal: "silver"]'>
		<g:img file="medals/silver.jpg"/>
	</g:link>
	</g:elseif>
	<g:elseif test="${badge?.medal == BadgeMedal.Gold}" >
	<g:link controller="Badge" action="badges" params='[medal: "gold"]'>
		<g:img file="medals/gold.jpg"/>
	</g:link>
	</g:elseif>
</span>