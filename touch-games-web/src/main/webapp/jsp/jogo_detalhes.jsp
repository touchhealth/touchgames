<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
		  xmlns:c="http://java.sun.com/jsp/jstl/core"
		  xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
		  xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<g:screen>
		<div id="jogo-detalhes" class="inline">
			<div class="images">
				<div class="main-image">
					<img src="${app}/imagens?id=${jogoSelecionado.imagens[0].id}" onerror="this.src='${app}/img/jogo_padrao.png'"/>
				</div>
				<div class="tiny-images">
					<c:if test="${not empty jogoSelecionado.imagens}">
						<c:forEach items="${jogoSelecionado.imagens}" var="imagem">
							<a href="${app}/imagens?id=${imagem.id}" target="_blank">
								<img src="${app}/imagens?id=${imagem.id}" width="50px"/>
							</a>
						</c:forEach> 
					</c:if>
				</div>
			</div>
			
			<form action="${app}/Carrinho!adicionar.action" method="post">
				<input type="hidden" name="jogoId" value="${jogoSelecionado.id}"/>
				
				<div>
					<h1 class="nome">
						${jogoSelecionado.nome} (${jogoSelecionado.desenvolvedora})
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
		
		<div id="jogos-relacionados" data-genero="${jogoSelecionado.genero}">
			<h2>Você também pode gostar:</h2>
			
			<div class="content loading"><!-- Será carregado por AJAX --></div>
				
		</div>
		
		<script src="${app}/js/prototype.js" type="text/javascript" />
		<script src="${app}/js/jogos_recomendados.js" type="text/javascript" />
	</g:screen>
</jsp:root>