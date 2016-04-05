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
<script type="text/javascript">
 	function myDatePicker1() {
 		alert("PICK THE START DATE")
 		$("#datetimepicker1").datepicker({
			dateFormat: 'yy-MM-dd'
        });  
	 };
	 function myDatePicker2() {
		 alert("PICK THE END DATE");
	 		$("#datetimepicker2").datepicker({
				dateFormat: 'yy-MM-dd'
            });  
		 };
 </script>
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
					<div class="col-sm-6 col-sm-offset-2" style="padding: 10px;text-align: center;">
						<span style="color: #B22222; font-weight: bold; text-align: center"><%=request.getAttribute("msg")%></span>
					</div>
				<%} %>
				<form role="form" class="form-horizontal" id="rebateform"action="FrysRebateServlet" method="POST">
			 <input type="hidden" id="rebateOperation" name="rebateOperation" value="rebate_generate">
				<div class="form-group">
					<div class="col-sm-6 col-sm-offset-3">
						<input type="text" class="form-control" id="prodId" name="prodId" placeholder="ProidId" required autofocus pattern="[0-9]{7}" title="Only Number allowed, size=7">
					</div>
				</div>
				
				<div class="form-group">
					<div class='col-sm-8 col-sm-offset-2  input-group date'  >
                    	<input type='text' class=" col-sm-2 col-sm-offset-1 form-control" name="startDate" id='datetimepicker1' required autofocus placeholder="Start Date" onclick="myDatePicker1()" />
                   			 <span class="input-group-addon">
                        		<span class="glyphicon glyphicon-calendar"></span>
                    		</span>
                    		
                    		
                    		<input type='text' class=" col-sm-2 col-sm-offset-1 form-control" name="endDate" id='datetimepicker2' required autofocus placeholder="End Date" onclick="myDatePicker2()"/>
                   			 <span class="input-group-addon">
                        		<span class="glyphicon glyphicon-calendar"></span>
                    		</span>
                    		
              		</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6 col-sm-offset-3">
						<input type="text" class="form-control" id="rebate_amt" name="rebate_amt" placeholder="Rebate Amount" required autofocus pattern="[0-9]{2}" title="Only Number allowed">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-4">
						
						<button class="btn btn-primary btn-sm btn-block" type="submit">Submit</button>
					</div>
				</div>
				
			</form>

	</div>


</div>
</body>
</html>