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
			<div class="intro">Out of Bounds is an engineering school project (<a href="http://isima.fr">ISIMA</a>, France) written by 
			<span class="italic">Jérémie COUTURIER, David FOUREL, Jérémi JUTAND and Gautier PELLOUX-PRAYER</span>.  <br/>
			It aims to learn and practice with <span class="bold">Grails</span> framework and <span class="bold">Groovy</span> language.
			</div>

			<div class="source">
			Application demo is <a href="http://outofbounds.herokuapp.com/">available on heroku.</a><br/>
			Application sources are <a href="https://github.com/jeremieCouturier/outOfBounds">available on GitHub.</a>
			</div>
		
			<div class="description">
			<h2>Features:</h2>
			<ul>
			    <li>Localization support (English & French versions).</li>
			    <li>Register and login to your account, with Google, Twitter or a custom username.</li>
			    <li>Submit questions, post answers, add comments...</li>
			    <li>Gain reputation with these actions and unlock badges!</li>
			    <li>Become a moderator and edit other's posts.</li>
			    <li>Continuous integration with travis available at travis-ci.org.</li>
			    <li>Demo application running on heroku.</li>
			    <li>Wiki for easy setup and more.</li>
			    <li>... and more!</li>
			</ul>
			</div>
			
			<div class="license">
			<h2>License</h2>
				<div class="content">This software is available under <span class="italic">Apache 2.0 License</span>.</div>
			</div>
		</div>
	</body>
</html>
