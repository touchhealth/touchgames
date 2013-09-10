<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
	
<t:loadbundle var="i18n" basename="Names" />
<t:loadbundle var="msg" basename="TGames-Messages" />

<t:userinterface>
	<t:title value="%{msg.tittle.fabricantes}"/>
	
	<t:panel id="list" cssClass="form">
		<t:title value="%{msg.lista}"/>
		
		<t:table list="%{fabricantes}">
			<t:tablerowselector name="selectedId" multiple="false" property="id"/>
			<t:tablecolumn  property="nome" title="%{i18n.Fabricante.nome}"/>
		</t:table>
	
		<t:toolbar>
			<t:ajaxbutton action="Fabricantes!create.action" responseTarget="response" postAction="" template="create"/>
			<t:ajaxbutton action="Fabricantes!update.action" responseTarget="response" postAction="" template="update"/>
			<t:ajaxbutton action="Fabricantes!view.action" responseTarget="response" postAction="" template="view"/>
			<t:submitbutton action="Fabricantes!remove.action" postAction="" template="remove" confirmMsg="%{msg.cfmMessage.deletar}"/>
		</t:toolbar>
	</t:panel>
	
	<div id="response"><!--  --></div>
</t:userinterface>
	
</jsp:root>