<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
		  xmlns:t="http://www.touchtec.com.br/twfc-tags">
	
	<t:loadbundle var="i18n" basename="Names" />
	
	<t:panel id="fabricanteForm" cssClass="form">
		<t:title value="%{formTitle}"/>
		
		<input type="hidden" name="fabricante.id" value="${fabricante.id}"/>
		
		<t:set component="label" value="width:150px" property="style" />
		
		<t:field>
			<t:label value="%{i18n.Fabricante.nome}"/>
			<t:textinput name="fabricante.nome" value="%{fabricante.nome}"/>
		</t:field>
		
		<t:toolbar>
			<t:jsbutton template="cancel" action="$('fabricanteForm').remove(); $t('list').open()"/>
			<t:submitbutton action="Fabricantes!save.action" template="save" primary="true"/>
		</t:toolbar>
	</t:panel>
	
</jsp:root>