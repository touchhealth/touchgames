<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" 
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<g:screen>
		<div id="jogos-lista">
			<c:forEach items="${jogos}" var="jogo">
				<a href="${app}/Compras!jogoDetalhes.action?jogoSelecionado.id=${jogo.id}">
					<div class="jogo">
						<div>
							<img src="${app}/img/games/chrono-trigger.jpg"/>
						</div>
						<div class="nome">
							${jogo.nome}
						</div>
						<div class="preco destaque">
							<fmt:setLocale value="pt_BR"/>
							<fmt:formatNumber type="currency" value="${jogo.precoComDesconto}" />
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</g:screen>
</jsp:root>