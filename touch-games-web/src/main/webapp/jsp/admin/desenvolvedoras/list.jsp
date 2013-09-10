<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<t:loadbundle var="i18n" basename="Names" />
<t:loadbundle var="msg" basename="TGames-Messages" />

<t:userinterface>
	<t:title value="%{msg.tittle.deselvolvedoras}"/>
	
	<t:panel id="list" cssClass="form">
		<t:title value="%{msg.lista}"/>
		
		<t:table list="%{desenvolvedoras}">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			<t:tablecolumn  property="nome" title="%{i18n.Desenvolvedora.nome}"/>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Desenvolvedoras!create.action" responseTarget="response" postAction="" template="create"/>
			<t:ajaxbutton action="Desenvolvedoras!update.action" responseTarget="response" postAction="" template="update"/>
			<t:ajaxbutton action="Desenvolvedoras!view.action" responseTarget="response" postAction="" template="view"/>
			<t:submitbutton action="Desenvolvedoras!remove.action" postAction="" template="remove" confirmMsg="%{msg.cfmMessage.deletar}"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>

</jsp:root>