<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
		  xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<t:userinterface>
	<t:title value="Deselvolvedoras"/>
	
	<t:panel id="list" cssClass="form" collapsible="true">
		<t:title value="Lista"/>
		
		<t:table list="%{desenvolvedoras}">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			<t:tablecolumn  property="nome" title="Nome"/>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Desenvolvedoras!create.action" responseTarget="response" successAction="$t('list').close()" template="create"/>
			<t:ajaxbutton action="Desenvolvedoras!update.action" responseTarget="response" successAction="$t('list').close()" template="update"/>
			<t:submitbutton action="Desenvolvedoras!remove.action" template="remove" confirmMsg="Deseja realmente exluir?"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!-- Atualizado por AJAX --></div>
</t:userinterface>

</jsp:root>