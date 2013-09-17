<#if dto.id??>
	<div>
		<#include "view_imagem1.ftl" />
	</div>
	<div style="padding-top: 5px;">
		<@t.fileupload id="fileuploadId" name="${renderer.name}" />
	</div>
<#else>
	<@t.fileupload id="fileuploadId" name="${renderer.name}" />
</#if>
