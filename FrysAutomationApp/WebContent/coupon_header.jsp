<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="bootstrap-3.3.2-dist/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="bootstrap-3.3.2-dist/css/bootstrap.min.css">
<script src="bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

<link href= "common.css" rel="stylesheet" type="text/css">
<style type="text/css">
.logo {
text-align: center;
padding-top: 0px;
}

#couponmsg {
	text-align: center;
	font-weight: bold;
	color: red;
}
#coupon_options {
text-align: center;
}



.navbar {
 margin-top: 20px;
}

.navbar-custom {
 background-color: white;
 border: 1px solid #337AB7;
}

.navbar-nav > li > a {
 background-color: white;
    color: #337AB7;
} 

.navbar-nav > li > a:hover {
 background-color: #337AB7;
 color: white;

</style>
</head>
<body>
<div class="container-fluid">


	<nav class="navbar navbar-custom col-xs-8 col-xs-offset-2 ">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="generate_coupon.jsp">Generate Coupon</a></li>
				<li><a href="delete_coupon.jsp">Delete Coupon</a></li>
				
			</ul>
	</nav>

<div class="logo col-sm-12 col-xs-12">
		<a href="index.jsp"><img src="img/logo.png" alt="logo"></a>
</div>
<div class="col-sm-8 col-sm-offset-2" id="couponmsg">
		<h2>Welcome to Frys Coupon Data Automation Application</h2>
</div>

</div>
</body>
</html>