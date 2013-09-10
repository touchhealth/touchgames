<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<t:loadbundle var="i18n" basename="Names" />
	<t:loadbundle var="msg" basename="TGames-Messages" />
	
	<t:panel id="jogoForm" cssClass="form">
		<t:title value="%{msg.consulta}"/>
		
		<t:set component="label" value="width:150px" property="style" />
		
		<t:field>
			<t:label value="%{i18n.Jogo.nome}"/>
			<t:textoutput value="%{jogo.nome}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.descricao}"/>
			<t:textinput template="textarea" value="%{jogo.descricao}" readOnly="true"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.genero}"/>
			<t:textoutput value="%{jogo.genero}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.desenvolvedora}"/>
			<t:textoutput value="%{jogo.desenvolvedora}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.preco}"/>
			<t:textoutput value="%{jogo.preco}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.desconto}"/>
			<t:textoutput value="${jogo.desconto}%"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.dataLancamento}"/>
			<t:dateoutput format="dd/MM/yyyy HH:mm:ss" value="%{jogo.dataLancamento}"/>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.plataformas}"/>
			<t:table list="%{jogo.plataformas}">
				<t:tablecolumn  property="nome" title="%{i18n.Plataforma.nome}"/>
			</t:table>
		</t:field>
		<t:field>
			<t:label value="%{i18n.Jogo.estoques}"/>
			<t:table list="%{jogo.estoques}">
				<t:tablecolumn  property="plataforma.nome" title="%{i18n.Plataforma}"/>
				<t:tablecolumn  property="quantidade" title="%{i18n.Estoque.quantidade}"/>
			</t:table>
		</t:field>
		
		<t:toolbar>
			<t:jsbutton template="cancel" action="$('jogoForm').remove()"/>
		</t:toolbar>
	</t:panel>

</jsp:root>