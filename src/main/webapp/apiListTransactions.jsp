<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- viewport meta to reset iPhone inital scale -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>List transactions</title>
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
		<form id="apiDebugForm" action="APIListTransactions"
			class="form-horizontal" method="POST">
			<br />
			<h4>NihaoPay URL Settings</h4>
			<div class="form-group">
				<label for="url" class="col-sm-2 control-label">API URL*</label>
				<div class="col-sm-10">
					<select name="url" id="url" class="form-control" required>
						<option value='http://api.test.nihaopay.com/v1.1/transactions' selected>http://api.test.nihaopay.com/v1.1/transactions</option>
						<option value='http://devapi.aurfy.cn:8007/v1.1/transactions'>http://devapi.aurfy.cn:8007/v1.1/transactions</option>
					</select>
				</div>
			</div>
			<h4>Merchant Settings</h4>
			<div class="form-group">
				<label for="token" class="col-sm-2 control-label">Token*</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="token" name="token"
						value="4847fed22494dc22b1b1a478b34e374e0b429608f31adf289704b4ea093e60a8"
						required>
				</div>
			</div>
			<br />
			<h4>Customer Entered Information</h4>
			<div class="form-group">
				<label for="limit" class="col-sm-2 control-label">Limit</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="limit" name="limit"
						placeholder="10" value="10">
				</div>
			</div>
			<div class="form-group">
				<label for="starting_after" class="col-sm-2 control-label">Start
					time</label>
				<div class="col-sm-10">
					<input type="text" class="form-control datetimepicker"
						id="starting_after" name="starting_after" placeholder="Start time"
						data-date-format="yyyy-mm-ddThh:ii:ssZ">
				</div>
			</div>
			<div class="form-group">
				<label for="ending_before" class="col-sm-2 control-label">End
					time</label>
				<div class="col-sm-10">
					<input type="text" class="form-control datetimepicker"
						id="ending_before" name="ending_before" placeholder="End time"
						data-date-format="yyyy-mm-ddThh:ii:ssZ">
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