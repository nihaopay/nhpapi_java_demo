<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- viewport meta to reset iPhone inital scale -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="js/jquery-1.10.1.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery.md5.js" charset="utf-8"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<title>SecurePay</title>
</head>
<body>
<div class="container">
	<h3>Payment Request Form</h3>
	<form action="APIQrcode" class="form-horizontal" method="POST">
		<br />
		<h4>NihaoPay URL Settings</h4>
		<div class="form-group">
			<label for="url" class="col-sm-2 control-label">API URL*</label>
			<div class="col-sm-10">
				<select name="url" id="url" class="form-control" required>
					<option value='http://api.test.nihaopay.com/v1.2/transactions/qrcode' selected>http://api.test.nihaopay.com/v1.2/transactions/qrcode</option>
					
					<option value='http://devapi.aurfy.cn:8007/v1.2/transactions/qrcode'>http://devapi.aurfy.cn:8007/v1.2/transactions/qrcode</option>
				</select>
			</div>
		</div>
		<h4>Merchant Settings (normally hidden)</h4>
		<div class="form-group">
			<label for="ipn_url" class="col-sm-2 control-label">IPN URL*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="ipn_url" id="ipn_url" value="http://demo.aurfy.cn:8007/ipn" required>
			</div>
		</div>
		
		<div class="form-group">
			<label for="token" class="col-sm-2 control-label">Token*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="token" id="token" value="4847fed22494dc22b1b1a478b34e374e0b429608f31adf289704b4ea093e60a8" required>
			</div>
		</div>
		<br />
					<% 
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String strDate = formatter.format(currentTime); //将日期时间格式化 
double number=Math.random()*100;
int i=(int)(number * 10000);
%>
		<div class="form-group">
			<label for="reference" class="col-sm-2 control-label">Reference*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="reference" name="reference" value='<%=strDate+i %>' required>
			</div>
		</div>
		<div class="form-group">
			<label for="amount" class="col-sm-2 control-label">Order Amount*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="amount" name="amount" required placeholder="Amount" value="2">
			</div>
		</div>
		<div class="form-group">
			<label for="currency" class="col-sm-2 control-label">Order Currency*</label>
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
			<label for="terminal" class="col-sm-2 control-label">Terminal*</label>
			<div class="col-sm-10">
				<select name="terminal" id="terminal" class="form-control" required>
					<option value='ONLINE' selected>PC</option>
					<option value='WAP'>WAP</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="timeout" class="col-sm-2 control-label">Timeout*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="timeout" id="timeout" value="30" required>
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">Order Description</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="description" id="description" placeholder="Order Description">
			</div>
		</div>
		<div class="form-group">
			<label for="note" class="col-sm-2 control-label">Note</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="note" name="note" placeholder="Enter Note">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>