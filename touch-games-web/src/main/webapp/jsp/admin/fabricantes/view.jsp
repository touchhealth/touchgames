<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<t:loadbundle var="i18n" basename="Names" />
	
	<t:panel id="fabricanteForm" cssClass="form">
		<t:title value="Consulta"/>
		
		<t:set component="label" value="width:150px" property="style" />
		
		<t:field>
			<t:label value="%{i18n.Fabricante.nome}"/>
			<t:textoutput value="%{fabricante.nome}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Fabricante.plataformas}"/>
			<t:table list="%{fabricante.plataformas}">
				<t:tablecolumn  property="nome" title="%{i18n.Plataforma.nome}"/>
			</t:table>
		</t:field>
		
		<t:toolbar>
			<t:jsbutton template="cancel" action="$('fabricanteForm').remove()"/>
		</t:toolbar>
	</t:panel>

</jsp:root>