
<div class="new_answer">
	<g:form controller="Answer" action="create" id="${question.id}">
		<!-- text -->
		<div class="text">
		    <ckeditor:config var="toolbar_Mytoolbar">
				[
				    ['Bold', 'Italic', '-', 'Link', 'Unlink', 'Blockquote','CreateDiv', 'Image', '-', 'NumberedList', 'BulletedList', 'HorizontalRule', '-', 'Undo','Redo']
				]
			</ckeditor:config>
			<ckeditor:editor name="text" toolbar="Mytoolbar" >
			</ckeditor:editor>	
		 </div>
		 <!-- save button -->
	    <button type="submit"><g:message code="question.post_answer"/></button>
	</g:form>
</div>
