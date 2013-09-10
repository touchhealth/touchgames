<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
<t:userinterface>
	<t:title>Jogos</t:title>
	
	<t:panel id="list" cssClass="form">
		<t:title value="Lista"/>
		
		<t:table list="%{jogos}">
			<t:tablerowselector name="selectedId" multiple="false"/>
			<t:tablecolumn  property="nome" title="Nome"/>
			<t:tablecolumn  property="genero" title="Gênero"/>
			<t:tablecolumn  property="desenvolvedora" title="Desenvolvedora"/>
			<t:tablecolumn  property="preco" title="Preço"/>
			<t:tablecolumn  property="dataLancamento" title="Lançamento"/>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Jogos!update.action" responseTarget="response" postAction="" template="update"/>
			<t:ajaxbutton action="Jogos!create.action" responseTarget="response" postAction="" template="create"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>

</jsp:root>