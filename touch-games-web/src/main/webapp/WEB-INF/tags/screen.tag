<html lang="en">
	<head>
		<title>Touch Games - Um toque de diversao</title>

		<link href="${app}/css/games.css" rel="stylesheet"/>
	</head>

	<body>
		<div id="screen">
			<div id="header">
				<jsp:include page="/WEB-INF/tags/header.jsp"/>
			</div>

			<div id="top-navigation">
				<jsp:include page="/WEB-INF/tags/menu_plataformas.jsp"/>
			</div>

			<%--
			COTEUDO PASSADO NO CORPO DA TAG
			--%>
			<div id="center">
				<jsp:doBody/>
			</div>

			<div id="footer">
				<jsp:include page="/WEB-INF/tags/footer.jsp"/>
			</div>
		</div>
	</body>
</html>
