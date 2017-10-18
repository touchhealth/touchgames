<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul id="menu-plataformas" class="clearfix">
	<c:forEach items="${todasPlataformas}" var="plataforma">
		<li>
			<a href="${app}/Compras!jogosPorPlataforma.action?plataformaSelecionada.id=${plataforma.id}">${plataforma.nome}</a>
		</li>
	</c:forEach>
</ul>