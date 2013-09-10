<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" 
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<fmt:setLocale value="pt_BR"/>
	
	<g:screen>
		<div id="carrinho-compras">
			<h1>Seu carrinho de compras</h1>
			
			<table>
				<thead>
					<tr>
						<th>Jogo</th>
						<th>Plataforma</th>
						<th class="text-right">Preço</th>
						<th class="text-right">Quantidade</th>
						<th class="text-right">Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${carrinhoDeCompras.items}" var="item">
						<tr>
							<td>${item.jogo}</td>
							<td>${item.plataforma}</td>
							<td class="text-right"><fmt:formatNumber type="currency" value="${item.jogo.precoComDesconto}"/></td>
							<td class="text-right">${item.quantidade}</td>
							<td class="text-right"><fmt:formatNumber type="currency" value="${item.jogo.precoComDesconto * item.quantidade}"/></td>
						</tr>		
					</c:forEach>
				</tbody>
				<tfoot class="total">
					<tr>
						<td colspan="5" class="text-right"><fmt:formatNumber type="currency" value="${carrinhoDeCompras.total}"/></td>
					</tr>
				</tfoot>
			</table>
			
			<div>
				<a href="${app}/Compras.action">&lt;&lt; Continuar Comprando</a>

				<form  action="${app}/Compras!finalizarCompra.action" class="right">
					<button type="submit">Comprar</button>
				</form>
			</div>
			
		</div>
	</g:screen>
	
</jsp:root>