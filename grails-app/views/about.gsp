<%@ page import="outofbounds.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Out of bounds</title>
		<link href="${resource(dir: 'css', file: 'about.css')}" rel="stylesheet">
	</head>
	<body>
		<div class="about">
			<div class="intro">
			<g:message code='about.intro.part1'/>
			(<a href="http://isima.fr">ISIMA</a>, France) 
			<g:message code='about.intro.part2'/>
			<span class="italic">Jérémie COUTURIER, David FOUREL, Jérémi JUTAND 
			<g:message code='about.intro.part3'/>
			Gautier PELLOUX-PRAYER</span>.  <br/>
			<g:message code='about.intro.part4'/>
			<span class="bold">
			<g:message code='about.intro.part5'/>
			</span> 
			</div>

			<div class="source">
			<g:message code='about.intro.source1'/>
			<a href="http://outofbounds.herokuapp.com/">
			<g:message code='about.intro.source2'/>
			</a><br/>
			<g:message code='about.intro.source3'/>
			<a href="https://github.com/jeremieCouturier/outOfBounds">
			<g:message code='about.intro.source4'/>
			</a>
			</div>
		
			<div class="description">
			<h2><g:message code='about.intro.description1'/></h2>
			<ul>
			    <li><g:message code='about.intro.description2'/></li>
			    <li><g:message code='about.intro.description3'/></li>
			    <li><g:message code='about.intro.description4'/></li>
			    <li><g:message code='about.intro.description5'/></li>
			    <li><g:message code='about.intro.description6'/></li>
			    <li><g:message code='about.intro.description7'/></li>
			    <li><g:message code='about.intro.description8'/></li>
			    <li><g:message code='about.intro.description9'/></li>
			    <li><g:message code='about.intro.description10'/></li>
			</ul>
			</div>
			
			<div class="license">
			<h2><g:message code='about.intro.license1'/></h2>
				<div class="content"><g:message code='about.intro.license2'/><span class="italic">Apache 2.0 License</span>.</div>
			</div>
		</div>
	</body>
</html>
