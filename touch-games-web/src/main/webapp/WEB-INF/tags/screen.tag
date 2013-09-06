<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jstl/core_rt"
	xmlns:k="http://www.caribe.com.br/caribe-tags"
	xmlns:v="urn:jsptagdir:/WEB-INF/tags">

<jsp:directive.attribute name="id" />

<![CDATA[<!DOCTYPE html>]]>
<html lang="en">

<head>
	<title>Vila Vilekula - Da nossa vila para a sua vida</title>

	<link href='http://fonts.googleapis.com/css?family=Courgette' rel='stylesheet' type='text/css' />
	<link rel="icon" type="image/png" href="${app}/assets/img/favicon.png" />
      
	<k:css file="assets/ext/bootstrap/css/bootstrap" />
	<k:css file="assets/ext/jasny-bootstrap/css/jasny-bootstrap" />
	<k:css file="caribe/caribe" />
	<k:css file="assets/css/vilavilekula" />
	
	<k:js file="assets/ext/jquery/jquery-1.10.1.min" />
	<k:js file="assets/ext/jquery/jquery-ui-1.10.0.custom.min" />
	<k:js file="assets/ext/jquery/jquery-migrate-1.2.1.min" />
	<k:js file="assets/ext/bootstrap/js/bootstrap" />
	<k:js file="assets/ext/jasny-bootstrap/js/jasny-bootstrap" />
	<k:js file="assets/js/bootstrap-patch" />
	<k:js file="assets/js/template" />
	<k:js file="assets/js/vilavilekula" />
</head>

<body data-contextpath="${app}">
 	<div id="screen" class="container-fluid">
		<div id="header" class="row-fluid">
			<jsp:include page="/views/main/header.jsp"/>
		</div>
		
		<div id="header-detail" class="row-fluid resized">
			<!-- detalhe -->
		</div>
		
		<div id="navigation" class="row-fluid">
			<jsp:include page="/views/main/navigation.jsp"/>
		</div>
		
		<div id="messages">
			<v:messages />
		</div>
		
		<div id="center" class="row-fluid">
			<div id="${id}" class="content span10">
				<jsp:doBody/>
			</div>
			
			<div id="side-panel" class="side-panel span2">
				<jsp:include page="/views/main/right_panel.jsp"/>
			</div>
		</div>
		
		<div id="footer" class="theme3 theme1-deep row-fluid">
			<jsp:include page="/views/main/footer.jsp"/>
		</div>
	</div>
	
	
	<div id="screen-loading">loading</div>
</body>

</html>
</jsp:root>