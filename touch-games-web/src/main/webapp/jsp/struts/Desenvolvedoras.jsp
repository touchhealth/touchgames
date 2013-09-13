<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Cadastro de Desenvolvedoras</title>
</head>
<body>
	
	<a href="${app}/struts/Desenvolvedoras!create.action">Adicionar</a>
	
	<table>
		<thead>
			<tr><th>Nome</th><th>Editar</th><th>Remover</th></tr>
		</thead>
		<tbody>
		<c:forEach items="${desenvolvedoras}" var="desenvolvedora">
			<tr>
				<td>${desenvolvedora.nome}</td>
				<td>
					<a href="${app}/struts/Desenvolvedoras!update.action?id=${desenvolvedora.id}">Editar</a>
				</td>
				<td>
					<a href="${app}/struts/Desenvolvedoras!remove.action?id=${desenvolvedora.id}">Remover</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${method=='update'}">
		<form action="${app}/struts/Desenvolvedoras!save.action" method="post">
			<fieldset>
				<legend>Editando ${desenvolvedora.nome}</legend>
				
				<div>
					<input type="hidden" name="desenvolvedora.id" value="${desenvolvedora.id}" />
					<input type="text" name="desenvolvedora.nome" value="${desenvolvedora.nome}"/>
				</div>		
				
				<div>
					<button type="submit">Salvar</button>
				</div>
			</fieldset>
		</form>
	</c:if>
	
	<c:if test="${method=='create'}">
		<form action="${app}/struts/Desenvolvedoras!save.action" method="post">
			<fieldset>
				<legend>Nova Desenvolvedora</legend>
				
				<div>
					<input type="text" name="desenvolvedora.nome"/>
				</div>		
				
				<div>
					<button type="submit">Criar</button>
				</div>
			</fieldset>
		</form>
	</c:if>
	
</body>
</html>
</jsp:root>