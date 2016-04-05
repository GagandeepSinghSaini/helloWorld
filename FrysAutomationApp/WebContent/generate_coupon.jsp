<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>coupon</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="bootstrap-3.3.2-dist/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="bootstrap-3.3.2-dist/css/bootstrap.min.css">
<script src="bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

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
<script type="text/javascript">
	function show() {
		document.getElementById("discValue").style.display='block';
	   }
</script>
<link href="common.css" rel="stylesheet" type="text/css">
<style type="text/css">

.logo {
text-align: center;
}
#header {
padding-top: 20px;
}
</style>
</head>
<body>
	<div class="container-fluid">
	<div id="header">
	<%@include file="coupon_header.jsp" %>
	</div>
			<div class="col-sm-4 col-sm-offset-4" style="text-align: center;">
				<%if(request.getAttribute("msg")!=null) { %>
					<div class="col-sm-8 col-sm-offset-2" style="padding: 10px;">
						<span style="color: #B22222; font-weight: bold;"><%=request.getAttribute("msg")%></span>
					</div>
					<%if(request.getAttribute("promoCode")!=null) { %>
					<div class="col-sm-8 col-sm-offset-2" style="padding: 10px;">
						<span style="color: #B22222; font-weight: bold;">PROMO CODE: <%=request.getAttribute("promoCode")%></span>
					</div>
				<%} %>
				<%} %>
			<form role="form" class="form-horizontal" id="couponform"action="FrysCouponServlet" method="POST">
			<input type="hidden" id="couponOperation" name="couponOperation" value="generate_coupon">
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
					<div class="col-sm-8 col-sm-offset-2">
					<!-- <input type="text" class="form-control"placeholder="Promotion Code[ 36 or 37 ]" name="promotionCode" id="promortionCode" required autofocus  pattern="[0-9]{2}" title="Two Digit Number allowed [36,37]"/> -->
					<span style="color: #B22222; font-weight: bold;">PROMOTION CODE: &nbsp;</span>
					<select name="promotionCode" id="promortionCode">
					<option value="" disabled selected>Select</option>
					<option value="36">36</option>
					<option value="37">37</option>
					</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-8 col-sm-offset-2">
					<!-- <input type="text" class="form-control"placeholder="Required Flag" name="requiredFlag" id="requiredFlag" required autofocus pattern="[Y,N]{1}" title="Enter Y or N"/> -->
					<span style="color: #B22222; font-weight: bold;text-align: left">REQUIRED FLAG: &nbsp; &nbsp; &nbsp;</span>
					<select name="requiredFlag" id="requiredFlag" >
					<option value="" disabled selected>Select</option>
					<option value="Y">Y</option>
					<option value="N">N</option>
					</select>
					</div>
				</div>
				
				
				   <div class="form-group">
				<div class="col-sm-8 col-sm-offset-2">
				<label><input type="radio" id="disc" name="optradio" onclick="show()" value="FixedDiscount">Fixed Disc</label>
				<label><input type="radio" id="disc" name="optradio" onclick="show()" value="DiscountPercent">Discount%</label>
				
				<div class="form-group">
					<div class="col-sm-8 col-sm-offset-2">
					<input type="text" style="display:none" id="discValue" name="discValue" class="form-control" placeholder="Enter Discount Value" required autofocus pattern="[0-9]{2}" title="Only Numbers allowed"/>
					</div>
				</div>
				
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