<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" 
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<t:loadbundle var="i18n" basename="Names" />
<fmt:setLocale value="pt_BR"/>

<t:userinterface>
	<t:title value="Jogos"/>
	
	<t:panel id="list" cssClass="form" collapsible="true">
		<t:title value="Lista"/>
		
		<t:table list="%{jogos}" var="jogo">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			
			<t:tablecolumn title="Imagem">
				<c:if test="${!empty jogo.imagens}">
					<img src="${app}/imagens?id=${jogo.imagens[0].id}" width="50px"/>
				</c:if>
			</t:tablecolumn>
			
			<t:tablecolumn  property="nome" title="%{i18n.Jogo.nome}"/>
			<t:tablecolumn  property="genero" title="%{i18n.Jogo.genero}"/>
			<t:tablecolumn  property="desenvolvedora" title="%{i18n.Jogo.desenvolvedora}"/>
			<t:tablecolumn  title="%{i18n.Jogo.preco}">
				<fmt:formatNumber type="currency" value="${jogo.preco}" />
			</t:tablecolumn>
			<t:tablecolumn  title="%{i18n.Jogo.precoComDesconto}">
				<fmt:formatNumber type="currency" value="${jogo.precoComDesconto}" />
			</t:tablecolumn>
			<t:tablecolumn  title="%{i18n.Jogo.dataLancamento}">
					<fmt:formatDate value="${jogo.dataLancamento}" />
			</t:tablecolumn>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Jogos!create.action" responseTarget="response" postAction="$t('list').close()" template="create"/>
			<t:ajaxbutton action="Jogos!update.action" responseTarget="response" postAction="$t('list').close()" template="update"/>
			<t:ajaxbutton action="Jogos!view.action" responseTarget="response" postAction="$t('list').close()" template="view"/>
			<t:submitbutton action="Jogos!remove.action" template="remove" confirmMsg="%{i18n.msg.remover}"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>

</jsp:root>