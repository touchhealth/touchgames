<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags" xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<t:loadbundle var="i18n" basename="Names" />

<t:userinterface>
	<t:title value="Pedidos"/>
	
	<t:panel id="list" cssClass="form" collapsible="true">
		<t:title value="Lista"/>
		
		<t:table list="%{pedidos}" var="pedido">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			<t:tablecolumn  property="id" title="%{i18n.Pedido.id}"/>
			<t:tablecolumn  property="data" title="%{i18n.Pedido.data}"/>
			<t:tablecolumn  title="%{i18n.Pedido.valorTotal}">
			</t:tablecolumn>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Pedidos!view.action" responseTarget="response" postAction="$t('list').close()" template="view"/>
			<t:submitbutton action="Pedidos!remove.action" template="remove" confirmMsg="%{i18n.msg.remover}"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>

</jsp:root>	