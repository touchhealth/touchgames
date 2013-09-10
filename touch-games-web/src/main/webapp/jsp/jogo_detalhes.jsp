<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<g:screen>
		<div id="jogo-detalhes" class="inline">
			<div class="images">
				<div class="main-image">
					<img src="${app}/img/games/chrono-trigger.jpg"/>
				</div>
				<div class="tiny-images">
					<!-- FIXME usar imagens do jogo -->
					<a href="${app}/img/games/chrono-trigger.jpg" target="_blank">
						<img src="${app}/img/games/chrono-trigger.jpg" width="50px"/>
					</a>
					<a href="${app}/img/games/chrono-trigger.jpg" target="_blank">
						<img src="${app}/img/games/chrono-trigger.jpg" width="50px"/>
					</a>
					<a href="${app}/img/games/chrono-trigger.jpg" target="_blank">
						<img src="${app}/img/games/chrono-trigger.jpg" width="50px"/>
					</a>
					<a href="${app}/img/games/chrono-trigger.jpg" target="_blank">
						<img src="${app}/img/games/chrono-trigger.jpg" width="50px"/>
					</a>
					<a href="${app}/img/games/chrono-trigger.jpg" target="_blank">
						<img src="${app}/img/games/chrono-trigger.jpg" width="50px"/>
					</a>
					<a href="${app}/img/games/chrono-trigger.jpg" target="_blank">
						<img src="${app}/img/games/chrono-trigger.jpg" width="50px"/>
					</a>

				</div>
			</div>
			
			<form action="${app}/Carrinho!adicionar.action" method="post">
				<input type="hidden" name="jogoId" value="${jogoSelecionado.id}"/>
				
				<div>
					<h1 class="nome">
						${jogoSelecionado.nome}
					</h1>
					
					<div class="descricao">
						${jogoSelecionado.descricao}
					</div>
					
					<div class="preco destaque">
						<fmt:setLocale value="pt_BR"/>
						<fmt:formatNumber type="currency" value="${jogoSelecionado.precoComDesconto}" />
					</div>
					
					<div class="plataforma">
						<c:forEach items="${jogoSelecionado.plataformas}" var="plataforma">
							<label>
								<input required="required" type="radio" value="${plataforma.id}" name="plataformaId" /> ${plataforma}
							</label>
						</c:forEach>
					</div>
					
					<div class="quantidade">
						<input type="number" min="1" name="quantidade" value="1"/>
					</div>
					
					<div class="comprar">
						<button type="submit">Adicionar ao Carrinho</button>
					</div>
				</div>
			</form>
		</div>
	</g:screen>
</jsp:root>