<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rebate</title>
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
padding: 10px;
font-weight: bold;
color: #454545;
text-align: center;
}

</style>
</head>
<body>
<div class="container-fluid">
<div id="header">
	<%@include file="rebate_header.jsp" %>
	</div>

<div class="col-sm-8 col-sm-offset-2" id="welcomemsg">
	<h2>FRYS REBATE DATA AUTOMATION</h2>
</div>

	<div class="col-sm-6 col-sm-offset-3">
			<%if(request.getAttribute("msg")!=null) { %>
				<div class="col-sm-8 col-sm-offset-2" style="padding: 10px;">
					<span style="color: #B22222; font-weight: bold;"><%=request.getAttribute("msg")%></span>
				</div>
		<%} %>
		<form role="form" class="col-sm-6 col-sm-offset-2 form-horizontal" id="rebateform" action="FrysRebateServlet" method="POST">
		<input type="hidden" id="rebateOperation" name="rebateOperation" value="delete_rebate">
			<div class="input-group">
				<input type="text" class="form-control" name="prodId" id="prodId" placeholder="ProidId" required autofocus pattern="[0-9]{7}" title="Only Number allowed, size=7">
					 <span class="input-group-btn">
					<button class="btn btn-primary" type="submit">Delete</button>
				</span>
			</div>
		</form>					
			

	</div>


</div>
</body>
</html>