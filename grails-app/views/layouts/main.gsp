<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<g:javascript library="application"/>		
		<r:layoutResources />
	</head>
	<body>
<<<<<<< HEAD
		<div id="oobLogo" role="banner"><a href="${resource(url:'/')}/"><img src="${resource(dir: 'images', file: 'out_of_bounds.png')}" alt="Out of bounds"/></a></div>
=======
		<div id="oobLogo" role="banner"><a href="${resource(url:'/')}"><img src="${resource(dir: 'images', file: 'out_of_bounds.png')}" alt="Out of bounds"/></a></div>
		<!-- <div id="oobLogo" role="banner"><a href="http://localhost:8080/outOfBounds"><img src="${resource(dir: 'images', file: 'out_of_bounds.png')}" alt="Out of bounds"/></a></div> -->
		<div id="menu">
			<a href="http://localhost:8080/outOfBounds"><img src="${resource(dir: 'images', file: 'out_of_bounds.png')}" alt="Out of bounds"/></a>
		    <label> OutOfBounds </label>
		    
		    <g:link controller="Question" action="index">
		        <button><g:message code="menu.questions"/></button>
		    </g:link>
		    <g:link controller="Tag" action="index">
		        <button><g:message code="menu.tags"/></button>
		    </g:link>
		    <g:link controller="User" action="index">
		        <button><g:message code="menu.users"/></button>
		    </g:link>
		    <g:link controller="Badge" action="index">
		        <button><g:message code="menu.badges"/></button>
		    </g:link>
		    <g:link controller="Question" action="create">
		        <button><g:message code="menu.ask"/></button>
		    </g:link>
		</div>
>>>>>>> 2f62675... [Menu] added menu with i18
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<r:layoutResources />
	</body>
</html>
