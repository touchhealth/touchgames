<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<t:loadbundle var="i18n" basename="Names" />

<t:userinterface>
	<t:title value="Jogos"/>
	
	<t:panel id="list" cssClass="form">
		<t:title value="Lista"/>
		
		<t:table list="%{jogos}">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			<t:tablecolumn  property="nome" title="%{i18n.Jogo.nome}"/>
			<t:tablecolumn  property="genero" title="%{i18n.Jogo.genero}"/>
			<t:tablecolumn  property="desenvolvedora" title="%{i18n.Jogo.desenvolvedora}"/>
			<t:tablecolumn  property="preco" title="%{i18n.Jogo.preco}"/>
			<t:tablecolumn  property="dataLancamento" title="%{i18n.Jogo.dataLancamento}"/>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Jogos!create.action" responseTarget="response" template="create"/>
			<t:ajaxbutton action="Jogos!update.action" responseTarget="response" template="update"/>
			<t:ajaxbutton action="Jogos!view.action" responseTarget="response" template="view"/>
			<t:submitbutton action="Jogos!remove.action" template="remove" confirmMsg="%{i18n.msg.remover}"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>

</jsp:root>