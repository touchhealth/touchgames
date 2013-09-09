<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags"
	xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<t:userinterface>
		<t:resource type="script" source="js/games" />

		<t:title value="Finalize sua compra"/>
	
		<t:table list="%{carrinhoDeCompras.items}" var="item" index="index">
			<t:tablecolumn property="jogo.nome" title="Jogo"/>
			<t:tablecolumn title="Preço">
				<t:textoutput value="${item.jogo.preco}" id="preco[${index}]"/>
			</t:tablecolumn>
			<t:tablecolumn title="Quantidade">
				<t:singleselect template="simpleselect" list="%{quantidades}" name="%{quantidade}" value="1" id="quantidade[${index}]">
					<t:event eventName="change" handler="atualizaPreco(${index})" />
				</t:singleselect>
			</t:tablecolumn>
			<t:tablecolumn title="Total" >
				<t:textoutput value="${item.jogo.preco}" id="total[${index}]"/>
			</t:tablecolumn>
		</t:table>
		
		<t:submitbutton action="Compras/comprar.action" label="Comprar"/>
	</t:userinterface>
	
</jsp:root>