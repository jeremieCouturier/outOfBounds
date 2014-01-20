<%@ page import="outofbounds.Answer" %>



<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="answer.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${answerInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['answer.id': answerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'mark', 'error')} required">
	<label for="mark">
		<g:message code="answer.mark.label" default="Mark" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="mark" type="number" value="${answerInstance.mark}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="answer.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="question" name="question.id" from="${outofbounds.Question.list()}" optionKey="id" required="" value="${answerInstance?.question?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="answer.text.label" default="Text" />
		
	</label>
	<g:textField name="text" value="${answerInstance?.text}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="answer.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${outofbounds.User.list()}" optionKey="id" required="" value="${answerInstance?.user?.id}" class="many-to-one"/>
</div>

