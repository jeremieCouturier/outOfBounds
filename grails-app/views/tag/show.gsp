
<%@ page import="outofbounds.Tag" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	
		<label>Tagged Questions : </label>${tag.name}
		<button>Newest</button>
		<button>Unanswered</button>
		
		
		<g:render template="/question/templateQuestion" collection="${tag.questions}" var="question" />
		
	</body>
</html>
