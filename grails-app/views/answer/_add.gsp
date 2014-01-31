
<div class="new_answer">
	<g:form controller="Answer" action="create" id="${question.id}">
	<div class="text">
	    <!-- <textarea name="text" placeholder="Add an answer ..."></textarea><br /> -->
	    <ckeditor:config var="toolbar_Mytoolbar">
			[
			    ['Bold', 'Italic', '-', 'Link', 'Unlink', 'Blockquote','CreateDiv', 'Image', '-', 'NumberedList', 'BulletedList', 'HorizontalRule', '-', 'Undo','Redo']
			]
		</ckeditor:config>
		<ckeditor:editor name="text" toolbar="Mytoolbar">
			${initialValue}
		</ckeditor:editor>	
	 </div>
	    <button type="submit"><g:message code="question.post_answer"/></button>
	</g:form>
</div>