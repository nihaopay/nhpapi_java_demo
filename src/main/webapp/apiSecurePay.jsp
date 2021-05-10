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
	<form action="APISecurePay" class="form-horizontal" method="POST">
		<br />
		<h4>NihaoPay URL Settings</h4>
		<div class="form-group">
			<label for="url" class="col-sm-2 control-label">API URL*</label>
			<div class="col-sm-10">
				<select name="url" id="url" class="form-control">
					<option value='https://apitest.nihaopay.com/v1.2/transactions/securepay' selected>NihaoPay Test API URL</option>
					<option value='https://api.nihaopay.com/v1.2/transactions/securepay'>NihaoPay Live API URL</option>
				</select>
			</div>
		</div>
		<h4>Merchant Settings</h4>
		<div class="form-group">
			<label for="callback_url" class="col-sm-2 control-label">Callback URL*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="callback_url" id="callback_url" value="http://localhost:8080/Demo/callback">
			</div>
		</div>
		<div class="form-group">
			<label for="ipn_url" class="col-sm-2 control-label">IPN URL*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="ipn_url" id="ipn_url" value="http://demo.aurfy.cn/ipn">
			</div>
		</div>
		<div class="form-group">
			<label for="token" class="col-sm-2 control-label">Token*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="token" id="token" value="4847fed22494dc22b1b1a478b34e374e0b429608f31adf289704b4ea093e60a8">
			</div>
		</div>
		<h4>Customer Entered Information</h4>
		<div class="form-group">
			<label for="vendor" class="col-sm-2 control-label">Vendor*</label>
			<div class="col-sm-10">
				<select name="vendor" id="vendor" class="form-control">
					<option value='unionpay' selected>unionpay</option>
					<option value='alipay'>alipay</option>
					<option value='wechatpay'>wechatpay</option>
				</select>
			</div>
		</div>
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
				<input type="text" class="form-control" id="reference" name="reference" value='<%=strDate+i %>'>
			</div>
		</div>
		<div class="form-group">
			<label for="currency" class="col-sm-2 control-label">Order Currency*</label>
			<div class="col-sm-10">
				<select name="currency" id="currency" class="form-control">
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
			<label for="amount" class="col-sm-2 control-label">Order Amount*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="amount" name="amount"  value="2">
			</div>
		</div>
		
		<div class="form-group">
			<label for="amount" class="col-sm-2 control-label">RMB Amount*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="rmb_amount" name="rmb_amount" >
			</div>
		</div>
		
		<div class="form-group">
			<label for="terminal" class="col-sm-2 control-label">Terminal*</label>
			<div class="col-sm-10">
				<select name="terminal" id="terminal" class="form-control">
					<option value='ONLINE' selected>PC</option>
					<option value='WAP'>WAP</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="timeout" class="col-sm-2 control-label">Timeout*</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="timeout" id="timeout" value="30">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">Order Description</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="description" id="description" >
			</div>
		</div>
		<div class="form-group">
			<label for="note" class="col-sm-2 control-label">Note</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="note" name="note" >
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