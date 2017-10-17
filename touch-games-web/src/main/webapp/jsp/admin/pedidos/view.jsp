<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
		  xmlns:t="http://www.touchtec.com.br/twfc-tags" xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

	<t:loadbundle var="i18n" basename="Names" />
	
	<t:panel id="jogoForm" cssClass="form">
		<t:title value="Consulta"/>
		
		<t:set component="label" value="width:150px" property="style" />
		
		<t:field>
			<t:label value="%{i18n.Pedido.id}"/>

				<t:textoutput value="%{pedido.id}"/>
		</t:field>

		<t:field>
			<t:label value="%{i18n.Pedido.data}"/>

			<t:dateoutput format="dd/MM/yyyy HH:mm:ss" value="%{pedido.data}"/>
		</t:field>

		<t:field>
			<t:label value="%{i18n.Pedido.itens}"/>

			<t:table list="%{pedido.itens}" var="item">
				<t:tablecolumn  property="quantidade" title="%{i18n.ItemPedido.quantidade}"/>
				<t:tablecolumn  property="jogo.nome" title="%{i18n.Jogo}"/>
				<t:tablecolumn  property="plataforma.nome" title="%{i18n.Plataforma}"/>
				<t:tablecolumn  title="%{i18n.ItemPedido.valorTotalItem}">
					<fmt:formatNumber type="currency" value="${item.valorTotalItem}" />
				</t:tablecolumn>
			</t:table>
		</t:field>

		<t:field>
			<t:label value="%{i18n.Pedido.valorTotal}"/>

			<span class="twfc-output">
				<fmt:formatNumber type="currency" value="${pedido.valorTotal}" />
			</span>
		</t:field>
		
		<t:toolbar>
			<t:jsbutton template="cancel" action="$('jogoForm').remove(); $t('list').open()"/>
		</t:toolbar>
	</t:panel>

</jsp:root>