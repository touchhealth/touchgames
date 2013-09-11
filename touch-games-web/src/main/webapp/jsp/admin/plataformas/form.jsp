<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
	
	<t:loadbundle var="i18n" basename="Names" />
	
	<t:panel id="plataformaForm" cssClass="form">
		<t:title value="%{formTitle}"/>
		
		<input type="hidden" name="plataforma.id" value="${plataforma.id}"/>
		
		<t:set component="label" value="width:150px" property="style" />
		
		<t:field>
			<t:label value="%{i18n.Plataforma.nome}"/>
			<t:textinput name="plataforma.nome" value="%{plataforma.nome}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Plataforma.fabricante}"/>
			<t:singleselect name="plataforma.fabricante" value="%{plataforma.fabricante}" 
				list="%{fabricantes}" optionLabel="nome"  optionValue="id"/>
		</t:field>
		
		<t:toolbar>
			<t:jsbutton template="cancel" action="$('plataformaForm').remove(); $t('list').open()"/>
			<t:submitbutton action="Plataformas!save.action" template="save" primary="true"/>
		</t:toolbar>
	</t:panel>
	
</jsp:root>