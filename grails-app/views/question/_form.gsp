
<link href="${resource(dir: 'css', file: 'createQuestion.css')}" rel="stylesheet">
<link href="${resource(dir: 'css', file: 'widgets.css')}" rel="stylesheet">
<link href="${resource(dir: 'css', file: 'header.css')}" rel="stylesheet">
<link href="${resource(dir: 'css', file: 'subheader.css')}" rel="stylesheet">

<ckeditor:resources/>


<fieldset class="form">
	<!-- title -->
	<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'title', 'error')} required">
	 	<div class="title">
			<label for="title">
				<g:message code="question.title" />
				<span>*</span>
			</label>
			<input class="text" id="title" name="title" required="" placeholder="${message (code: 'question.title.placeholder')}" value="${questionInstance?.title}"/>
		</div>
	</div>
	
	<!-- body -->
	<ckeditor:config var="toolbar_Mytoolbar">
	[
	    ['Bold', 'Italic', '-', 'Link', 'Unlink', 'Blockquote','CreateDiv', 'Image', '-', 'NumberedList', 'BulletedList', 'HorizontalRule', '-', 'Undo','Redo']
	]
	</ckeditor:config>
	<ckeditor:editor name="text" toolbar="Mytoolbar">
		<div class="content">${questionInstance?.text}</div>
	</ckeditor:editor>	
	
	<!-- tags -->
	<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'question_tags', 'error')} required">
		<div class="tags">
			<label for="question_tags">
				<g:message code="question.tags" />		
				<span>*</span>
			</label>
			<input class="text" name="question_tags" value="${tags}" required="" placeholder="${message (code: 'question.tags.placeholder')}">
		</div>	
	</div>
</fieldset>
