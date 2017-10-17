<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Servlets e JSPs</title>
</head>
<body>

	<p>
		JSPs são capazes de gerar HTML dinamicos; não apenas HTML, é verdade;
	</p>
	<p>
		Por ex, sou capaz de imprimir a hora atual do servidor: ${serverTime} <a href="">Atualizar</a>
	</p>
	<p>
		E este aqui é o seu context path (o caminho da sua aplicação): ${app}
	</p>

	<!--
	[É bom saber:]
	JSPs são compilados pelo servidor (Tomcat) e se transformam em uma Servlet.
	-->
</body>
</html>
</jsp:root>