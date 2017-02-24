<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- viewport meta to reset iPhone inital scale -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Refund</title>
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
		<h3>Request Form</h3>
		<form id="apiDebugForm" action="APIRefund" class="form-horizontal"
			method="POST">
			<br />
			<h4>NihaoPay URL Settings</h4>
			<div class="form-group">
				<label for="url" class="col-sm-2 control-label">API URL*</label>
				<div class="col-sm-10">
					<select name="url" id="url" class="form-control" required>
						<option value='https://apitest.nihaopay.com/v1.2/transactions/{transaction_id}/refund' selected>https://apitest.nihaopay.com/v1.2/transactions/{transaction_id}/refund</option>
						<option value='https://api.nihaopay.com/v1.2/transactions/{transaction_id}/refund'>https://api.nihaopay.com/v1.2/transactions/{transaction_id}/refund</option>
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
			<h4>Customer Entered Information</h4>
			<div class="form-group">
				<label for="transaction_id" class="col-sm-2 control-label">Transaction
					ID*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="transaction_id"
						name="transaction_id" placeholder="Trensaction ID" value="" required>
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
				<label for="amount" class="col-sm-2 control-label">Refund
					Amount*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="amount" name="amount"
						placeholder="Amount" value="1" >
				</div>
			</div>
			<div class="form-group">
				<label for="amount" class="col-sm-2 control-label">Refund
					RMB Amount*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="rmb_amount" name="rmb_amount"
						placeholder="RMB Amount" >
				</div>
			</div>
			<div class="form-group">
				<label for="reason" class="col-sm-2 control-label">Reason</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="reason" id="reason"
						placeholder="Reason" value="">
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
	</div>
</body>
</html>