<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
		  xmlns:t="http://www.touchtec.com.br/twfc-tags">
	
<t:loadbundle var="i18n" basename="Names" />

<t:userinterface>
	<t:title value="Fabricantes"/>
	
	<t:panel id="list" cssClass="form" collapsible="true">
		<t:title value="Lista"/>
		
		<t:table list="%{fabricantes}">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			<t:tablecolumn  property="nome" title="%{i18n.Fabricante.nome}"/>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Fabricantes!create.action" responseTarget="response" successAction="$t('list').close()" template="create"/>
			<t:ajaxbutton action="Fabricantes!update.action" responseTarget="response" successAction="$t('list').close()" template="update"/>
			<t:ajaxbutton action="Fabricantes!view.action" responseTarget="response" successAction="$t('list').close()" template="view"/>
			<t:submitbutton action="Fabricantes!remove.action" template="remove" confirmMsg="%{i18n.msg.remover}"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>
	
</jsp:root>