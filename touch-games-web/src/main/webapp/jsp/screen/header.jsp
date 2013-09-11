<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags"
	xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<div class="left">
		<a href="${app}/">
			<img src="${app}/img/logo.png" />
		</a>
	</div>
	
	<div class="right inline">
		<a id="carrinho" href="${app}/Carrinho.action" class="destaque">
			<img src="${app}/img/cart.png"/>
		</a>

		<form action="${app}/">
			<input id="search" name="" placeholder="Buscar"/>
		</form>
	</div>
</jsp:root>