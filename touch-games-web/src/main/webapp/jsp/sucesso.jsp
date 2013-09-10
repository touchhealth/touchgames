<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" 
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<g:screen>
		<h1>Parabéns</h1>
		<p>
			Sua compra foi realizada com sucesso e o número do seu pedido é #${pedido.id}
		</p>
		<p>
			Você receberá um e-mail de confirmação com os detalhes da sua compra.
		</p>
		<p>
			A Touch Games agradece a sua escolha. 
		</p>
	</g:screen>
</jsp:root>