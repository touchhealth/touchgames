<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<t:loadbundle var="i18n" basename="Names" />
	<t:loadbundle var="msg" basename="TGames-Messages" />
	
	<t:panel id="desenvolvedoraForm" cssClass="form">
		<t:title value="%{msg.consulta}"/>
		
		<t:set component="label" value="width:150px" property="style" />
		
		<t:field>
			<t:label value="%{i18n.Desenvolvedora.nome}"/>
			<t:textoutput value="%{desenvolvedora.nome}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Desenvolvedora.jogos}"/>
			<t:table list="%{desenvolvedora.jogos}">
				<t:tablecolumn  property="nome" title="%{i18n.Jogo.nome}"/>
			</t:table>
		</t:field>
		
		<t:toolbar>
			<t:jsbutton template="cancel" action="$('desenvolvedoraForm').remove()"/>
		</t:toolbar>
	</t:panel>

</jsp:root>