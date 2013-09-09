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
	
	<a href="${app}/desenvolvedoras/create">Adicionar</a>
	
	<table>
		<thead>
			<tr><th>Nome</th><th>Ações</th></tr>
		</thead>
		<tbody>
		<c:forEach items="${desenvolvedoras}" var="desenvolvedora">
			<tr>
				<td>${desenvolvedora.nome}</td>
				<td>
					<a href="${app}/desenvolvedoras/update?id=${desenvolvedora.id}">Editar</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${method=='update'}">
		<form action="${app}/desenvolvedoras?id=${desenvolvedora.id}" method="post">
			<fieldset>
				<legend>Editando ${desenvolvedora.nome}</legend>
				
				<div>
					<input type="hidden" name="id" value="${desenvolvedora.id}" />
					<input type="text" name="nome" value="${desenvolvedora.nome}"/>
				</div>		
				
				<div>
					<button type="submit" name="method" value="save">Salvar</button>
					<button type="submit" name="method" value="remove">Remover</button>
				</div>
			</fieldset>
		</form>
	</c:if>
	
	<c:if test="${method=='create'}">
		<form action="${app}/desenvolvedoras?id=${desenvolvedora.id}" method="post">
			<fieldset>
				<legend>Nova Desenvolvedora</legend>
				
				<div>
					<input type="text" name="nome" value="${desenvolvedora.nome}"/>
				</div>		
				
				<div>
					<button type="submit" name="method" value="savenew">Criar</button>
				</div>
			</fieldset>
		</form>
	</c:if>
	
</body>
</html>
</jsp:root>