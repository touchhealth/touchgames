<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jstl/core_rt">

<jsp:directive.attribute name="id" />

<![CDATA[<!DOCTYPE html>]]>
<html lang="en">

<head>
	<title>Touch Games</title>
	
	<link href="${app}/css/games.css" rel="stylesheet"/>
</head>

<body data-contextpath="${app}">
 	<div id="header">
 		<jsp:include page="/jsp/screen/header.jsp"/>
 		<jsp:include page="/jsp/screen/menu_plataformas.jsp"/>
 	</div>
 	
 	<div id="center">
 		<jsp:doBody/>
 	</div>
 	
 	<div id="footer">
 		<jsp:include page="/jsp/screen/footer.jsp"/>
 	</div>
	
	<script src="${app}/js/games.js" />
</body>

</html>
</jsp:root>