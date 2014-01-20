<%@ page import="outofbounds.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="comment.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${commentInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['comment.id': commentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'mark', 'error')} required">
	<label for="mark">
		<g:message code="comment.mark.label" default="Mark" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="mark" type="number" value="${commentInstance.mark}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'post', 'error')} required">
	<label for="post">
		<g:message code="comment.post.label" default="Post" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="post" name="post.id" from="${outofbounds.Post.list()}" optionKey="id" required="" value="${commentInstance?.post?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="comment.text.label" default="Text" />
		
	</label>
	<g:textField name="text" value="${commentInstance?.text}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="comment.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${outofbounds.User.list()}" optionKey="id" required="" value="${commentInstance?.user?.id}" class="many-to-one"/>
</div>