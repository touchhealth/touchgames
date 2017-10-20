<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:g="urn:jsptagdir:/WEB-INF/tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

    <g:screen>
        <div id="jogos-lista">
            
            <c:forEach items="${jogos}" var="jogo">
                <a href="${app}/Compras!jogoDetalhes.action?jogoSelecionado.id=${jogo.id}">
                    <div class="jogo">
                        <img src="${app}/imagens?id=${jogo.imagens[0].id}" onerror="this.src='${app}/img/jogo_padrao.png'"/>
                        
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
            
            <c:if test="${empty jogos}">
                <c:choose>
                    <c:when test="${not empty nomeDoJogo}">
                        Nenhum jogo foi encontrado para a busca "${nomeDoJogo}".
                    </c:when>
                    <c:when test="${not empty plataformaSelecionada}">
                        Nenhum jogo foi encontrado para essa plataforma.
                    </c:when>
                    <c:otherwise>
                        Nenhum jogo foi encontrado.
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>

    </g:screen>
</jsp:root>