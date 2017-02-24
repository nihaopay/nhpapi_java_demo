<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- viewport meta to reset iPhone inital scale -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ExpressPay</title>
<script type="text/javascript" src="js/jquery-1.10.1.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery.md5.js" charset="utf-8"></script>
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js/jquery.jsonview.js"></script>
<script type="text/javascript" src="js/jsonview.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script src="js/demo.js"></script>
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link href="css/prettify.css" rel="stylesheet" />
<link href="css/jsonview.css" rel="stylesheet" >
<link href="css/demo.css" rel="stylesheet" >
</head>
<body>
	<div class="container">
		<h3>Payment Request Form</h3>
		<form id="apiDebugForm" action="APIExpressPay"
			class="form-horizontal" method="POST">
			<br />
			<h4>NihaoPay URL Settings</h4>
			<div class="form-group">
				<label for="url" class="col-sm-2 control-label">API URL*</label>
				<div class="col-sm-10">
					<select name="url" id="url" class="form-control" required>
						<option value='https://apitest.nihaopay.com/v1.2/transactions/expresspay' selected>NihaoPay Test API URL</option>
						<option value='https://api.nihaopay.com/v1.2/transactions/expresspay'>NihaoPay Live API URL</option>
					    
					</select>
				</div>
			</div>
			<h4>Merchant Settings</h4>
			<div class="form-group">
				<label for="token" class="col-sm-2 control-label">Token*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="token" name="token"
						value="4847fed22494dc22b1b1a478b34e374e0b429608f31adf289704b4ea093e60a8" required>
				</div>
			</div>
			<br />
			<h4>Customer Entered Information</h4>
			<%
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
						"yyyyMMddHHmmss");
				java.util.Date currentTime = new java.util.Date();//得到当前系统时间
				String strDate = formatter.format(currentTime); //将日期时间格式化 
				double number = Math.random() * 100;
				int i = (int) (number * 10000);
			%>
			<div class="form-group">
				<label for="reference" class="col-sm-2 control-label">Reference</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="reference"
						name="reference" value='<%=strDate + i%>' required>
				</div>
			</div>
			<div class="form-group">
				<label for="currency" class="col-sm-2 control-label">Order
					Currency*</label>
				<div class="col-sm-10">
					<select name="currency" id="currency" class="form-control" required>
						<option value='USD' selected>USD</option>
						<option value='JPY'>JPY</option>
						<option value='EUR'>EUR</option>
						<option value='GBP'>GBP</option>
						<option value='CAD'>CAD</option>
						<option value='HKD'>HKD</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="amount" class="col-sm-2 control-label">Order
					Amount*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="amount" name="amount" 
						placeholder="Amount" value="2">
				</div>
			</div>
			<div class="form-group">
				<label for="amount" class="col-sm-2 control-label">RMB Amount*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="rmb_amount" name="rmb_amount" 
						placeholder="RMB Amount" >
				</div>
			</div>
			<div class="form-group">
				<label for="card_number" class="col-sm-2 control-label">Card
					number*</label>
				<div class="col-sm-10">
					<select name="card_number" id="card_number" class="form-control" required>
						<option value='6221558812340000' selected>6221558812340000</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="card_exp_month" class="col-sm-2 control-label">Card
					expiration month*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="card_exp_month"
						id="card_exp_month" placeholder="Card expiration month" required value="11">
				</div>
			</div>
			<div class="form-group">
				<label for="card_exp_year" class="col-sm-2 control-label">Card
					expiration year*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="card_exp_year"
						id="card_exp_year" placeholder="Card expiration year" required value="2017">
				</div>
			</div>

			<div class="form-group">
				<label for="card_cvv" class="col-sm-2 control-label">Card
					CVV*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="card_cvv"
						id="card_cvv" placeholder="Card CVV" required value="123">
				</div>
			</div>
			<div class="form-group">
				<label for="capture" class="col-sm-2 control-label">Capture*</label>
				<div class="col-sm-10">
					<input id="purchase" type="radio" name="capture" checked="checked" value="true" required>Purchase
					<input id="pre_Auth" type="radio" name="capture" value="false" required>Pre-Auth
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label">Description</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="description"
						id="description" placeholder="Description">
				</div>
			</div>
			<div class="form-group">
				<label for="note" class="col-sm-2 control-label">Note</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="note" name="note"
						placeholder="Enter Note">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" id="btnSubmit" class="btn btn-default">Submit</button>
				</div>
			</div>
            <h4>Request URL</h4>
            <div class="well">
                <label id="apiRequestUrlInfo" style="color:green"></label>
                <br>
                <label><span id="apiRequest"></span></label>
            </div>
            <h4>Request params</h4>
            <div id="requestBody" class="requestBody"></div>
            <h4>Response body</h4>
            <div id="responseBody" class=""></div>
		</form>
		<script type="text/javascript">
		$(function() {
	          $("#pre_Auth").change(function() {
                  $("#purchase").removeAttr("checked");
              });
          });
		</script>
	</div>
</body>
</html>