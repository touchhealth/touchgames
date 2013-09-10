<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<t:loadbundle var="i18n" basename="Names" />
<t:loadbundle var="msg" basename="TGames-Messages" />

<t:userinterface>
	<t:title value="%{msg.tittle.pedidos}"/>
	
	<t:panel id="list" cssClass="form">
		<t:title value="%{msg.lista}"/>
		
		<t:table list="%{pedidos}">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			<t:tablecolumn  property="data" title="%{i18n.Pedido.data}"/>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Pedidos!view.action" responseTarget="response" postAction="" template="view"/>
			<t:submitbutton action="Pedidos!remove.action" postAction="" template="remove" confirmMsg="%{msg.cfmMessage.deletar}"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>

</jsp:root>	