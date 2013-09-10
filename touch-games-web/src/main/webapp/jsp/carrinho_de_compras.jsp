<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<g:screen>
		<h1>Seu carrinho de compras</h1>
		
		<table>
			<thead>
				<tr>
					<th>Jogo</th><th>Preço</th><th>Quantidade</th><th>Jogo</th><th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${carrinhoDeCompras.items}" var="item">
					<tr>
						<td>${item.jogo}</td>
						<td>${item.jogo.preco}</td>
						<td>${item.quantidade}</td>
						<td>${item.jogo.preco * item.quantidade}</td>
					</tr>				
				</c:forEach>
			</tbody>
		</table>

		
		<form action="${app}/Compras!finalizarCompra.action">
			<button type="submit">Comprar</button>
		</form>
	</g:screen>
	
</jsp:root>