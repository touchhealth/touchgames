<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<div class="left">
		<a href="${app}/">
			<img src="${app}/img/logo.png" />
		</a>
	</div>
	
	<div id="cart-search" class="right">
		<a href="${app}/Carrinho.action" class="destaque">
			<img class="imagemBusca" src="${app}/img/cart.png"/> 
			<span>(${carrinhoDeCompras.size})</span>
		</a>

		<form id="buscaForm" action="${app}/Compras!buscarPorNome.action">
			<input id="search" name="nomeDoJogo" placeholder="Buscar"/>
			<a href="javascript:void(0);" onclick="document.getElementById('buscaForm').submit();">
				<img class="imagemBusca" src="${app}/img/lupa.png"/>
			</a>
		</form>
	</div>
</jsp:root>