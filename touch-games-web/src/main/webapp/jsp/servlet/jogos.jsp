<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" 
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<fmt:setLocale value="pt_BR"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Cadastro de Jogos</title>
</head>
<body>

	<a href="${app}/jogos/create">Adicionar</a>
	
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Gênero</th>
				<th>Desenvolvedora</th>
				<th>Preço</th>
				<th>Preço com Desconto</th>
				<th>Data de Lançamento</th>
				<th>Ações</th></tr>
		</thead>
		<tbody>
		<c:forEach items="${jogos}" var="jogo">
			<tr>
				<td>${jogo.nome}</td>
				<td>${jogo.genero}</td>
				<td>${jogo.desenvolvedora}</td>
				<td>${jogo.preco}</td>
				<td>${jogo.precoComDesconto}</td>
				<td>${jogo.dataLancamento}</td>
				<td>
					<a href="${app}/jogos/update?id=${jogo.id}">Editar</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${method=='update'}">
		<form action="${app}/jogos" method="post">
			<fieldset>
				<legend>Editando ${jogo.nome}</legend>
				
				<input type="hidden" name="id" value="${jogo.id}" />
				
				<div>
					Nome: 
					<input type="text" name="nome" value="${jogo.nome}"/>
				</div>		
				<div>
					Descrição: 
					<textarea type="text" name="descricao" rows="5" cols="30">
						${jogo.descricao}
					</textarea>
				</div>
				<div>
					Desenvolvedora:
					<select name="desenvolvedora">
						<c:if test="${not empty jogo.desenvolvedora}">
							<option value=""></option>
						</c:if>
						<c:if test="${empty jogo.desenvolvedora}">
							<option value="" selected=""></option>
						</c:if>
					
						<c:forEach items="${desenvolvedoras}" var="desenvolvedora">
							<c:if test="${jogo.desenvolvedora!=desenvolvedora}">
								<option value="${desenvolvedora.id}">${desenvolvedora.nome}</option>
							</c:if>
							<c:if test="${jogo.desenvolvedora==desenvolvedora}">
								<option value="${desenvolvedora.id}" selected="">${desenvolvedora.nome}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div>
					Gênero: 
					<c:forEach items="${generos}" var="genero">
						<c:if test="${jogo.genero!=genero}">
							<input type="radio" name="genero" value="${genero}" >${genero}</input>
						</c:if>
						<c:if test="${jogo.genero==genero}">
							<input type="radio" name="genero" value="${genero}" checked="">${genero}</input>
						</c:if>
					</c:forEach>
				</div>
				<div>
					Data de Lançamento:
					<fmt:formatDate value="${jogo.dataLancamento}" var="dataLancamento" pattern="yyyy-MM-dd" />
					<input  type="date" name="dataLancamento" value="${dataLancamento}"/>
				</div>
				<div>
					Preço:
					<input  type="number" name="preco" value="${jogo.preco}" step="0.01"/>
				</div>
				<div>
					Desconto:
					<input  type="number" name="desconto" value="${jogo.desconto}"/>%
				</div>
						
				<div>
					<button type="submit" name="method" value="save">Salvar</button>
					<button type="submit" name="method" value="remove">Remover</button>
				</div>
			</fieldset>
		</form>
	</c:if>
	
	<c:if test="${method=='create'}">
		<form action="${app}/jogos" method="post">
			<fieldset>
				<legend>Novo Jogo</legend>
				
				<div>
					Nome: 
					<input type="text" name="nome"/>
				</div>		
				<div>
					Descrição: 
					<textarea name="descricao" rows="5" cols="30"><!-- vazio --></textarea>
				</div>
				<div>
					Desenvolvedora:
					<select name="desenvolvedora">
						<option></option>
						<c:forEach items="${desenvolvedoras}" var="desenvolvedora">
							<option value="${desenvolvedora.id}">${desenvolvedora.nome}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					Gênero: 
					<c:forEach items="${generos}" var="genero">
						<input type="radio" name="genero" value="${genero}" >${genero}</input>
					</c:forEach>
				</div>
				<div>
					Data de Lançamento:
					<input  type="date" name="dataLancamento"/>
				</div>
				<div>
					Preço:
					<input  type="number" name="preco" step="0.01"/>
				</div>
				<div>
					Desconto:
					<input  type="number" name="desconto"/>%
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