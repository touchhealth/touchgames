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

	<a href="${app}/struts/Jogos!create.action">Adicionar</a>

	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Gênero</th>
				<th>Desenvolvedora</th>
				<th>Preço</th>
				<th>Preço com Desconto</th>
				<th>Data de Lançamento</th>
				<th>Ações</th>
				<th>Editar</th>
				<th>Remover</th>	
			</tr>
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
					<a href="${app}/struts/Jogos!update.action?id=${jogo.id}">Editar</a>
				</td>
				<td>
					<a href="${app}/struts/Jogos!remove.action?id=${jogo.id}">Remover</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${method=='update'}">
		<form action="${app}/struts/Jogos!save.action" method="post">
			<fieldset>
				<legend>Editando ${jogo.nome}</legend>
				
				<input type="hidden" name="jogo.id" value="${jogo.id}" />
				
				<div>
					Nome: 
					<input type="text" name="jogo.nome" value="${jogo.nome}"/>
				</div>		
				<div>
					Descrição: 
					<textarea type="text" name="jogo.descricao" rows="5" cols="30">
						${jogo.descricao}
					</textarea>
				</div>
				<div>
					Desenvolvedora:
					<select required="required" name="jogo.desenvolvedora.id">
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
							<input type="radio" name="jogo.genero" value="${genero}" >${genero}</input>
						</c:if>
						<c:if test="${jogo.genero==genero}">
							<input type="radio" name="jogo.genero" value="${genero}" checked="">${genero}</input>
						</c:if>
					</c:forEach>
				</div>
				<div>
					Data de Lançamento:
					<fmt:formatDate value="${jogo.dataLancamento}" var="dataLancamento" pattern="dd/MM/yyyy" />
					<input  type="text" name="jogo.dataLancamento" value="${dataLancamento}"/> dd/mm/aaaa
				</div>
				<div>
					Preço:
					<fmt:formatNumber type="number" var="preco" value="${jogo.preco}"/>
					<input  type="text" name="jogo.preco" value="${preco}"/>
				</div>
				<div>
					Desconto:
					<input  type="number" name="jogo.desconto" value="${jogo.desconto}"/>%
				</div>
						
				<div>
					<button type="submit">Salvar</button>
				</div>
			</fieldset>
		</form>
	</c:if>
	
	<c:if test="${method=='create'}">
		<form action="${app}/struts/Jogos!save.action" method="post">
			<fieldset>
				<legend>Novo Jogo</legend>
				
				<div>
					Nome: 
					<input type="text" name="jogo.nome"/>
				</div>		
				<div>
					Descrição: 
					<textarea name="jogo.descricao" rows="5" cols="30"><!-- vazio --></textarea>
				</div>
				<div>
					Desenvolvedora:
					<select required="required" name="jogo.desenvolvedora.id">
						<option></option>
						<c:forEach items="${desenvolvedoras}" var="desenvolvedora">
							<option value="${desenvolvedora.id}">${desenvolvedora.nome}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					Gênero: 
					<c:forEach items="${generos}" var="genero">
						<input type="radio" name="jogo.genero" value="${genero}" >${genero}</input>
					</c:forEach>
				</div>
				<div>
					Data de Lançamento:
					<input  type="text" name="jogo.dataLancamento"/> dd/mm/aaaa
				</div>
				<div>
					Preço:
					<input  type="text" name="jogo.preco"/>
				</div>
				<div>
					Desconto:
					<input  type="number" name="jogo.desconto"/>%
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