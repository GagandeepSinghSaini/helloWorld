<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="bootstrap-3.3.2-dist/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="bootstrap-3.3.2-dist/css/bootstrap.min.css">
<script src="bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

<link href="common.css" rel="stylesheet" type="text/css">
<style type="text/css">


#header {
padding-top:20px;
}
.logo {
text-align: center;
}
#welcomemsg {
font-weight: bold;
color: red;
text-align: center;
}

</style>
</head>
<body>
<div class="container-fluid">
<div id="header">
	<%@include file="header.jsp" %>
	</div>
	<div class="logo col-sm-12 col-xs-12">
			<a href="index.jsp"><img src="img/logo.png" alt="logo"></a>
		</div>
<div class="col-sm-6 col-sm-offset-3" id="welcomemsg">
<h2>Welcome to Frys data automation app</h2>
</div>
</div>
</body>
</html>