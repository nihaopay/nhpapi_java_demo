<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://g.alicdn.com/??mui/global/1.3.4/global.css,tm/shop/1.2.30/app.css,tm/shop/1.2.30/page/detail.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.md5.js"></script>
<link rel="stylesheet"
	href="http://g.alicdn.com/??tm/detail/1.9.26/app.css,tm/detail/1.9.26/page/itemDetail.css">
<link rel="stylesheet"
	href="http://assets.alicdn.com/apps/taesite/platinum/stylesheet/??common/mods/ww-hover/default-min.css,view/layout-tmall-990-min.css,common/modules-sys-tmall-default-min.css,common/modules-other-tmall-default-min.css?t=_20130725.css">
<title></title>

</head>
<body>
	<table>
		<tr>
			<td><input type="radio" name="payType" id="securepay">securePay</td>
		</tr>
		<tr>
			<td><input type="radio" name="payType" id="expresspay"
				checked="checked">expressPay</td>
		</tr>
	</table>
	<script type="text/javascript">
		$(function() {
			$("#expresspay").click(function() {
				$("#J_FrmBid").attr("action", "apiExpressPay.jsp");
			});
			$("#securepay").click(function() {
				$("#J_FrmBid").attr("action", "apiSecurePay.jsp");
			});
		})
	</script>
	<div>
		<a id="J_LinkBuy" href="javascript:;" data-addfastbuy="true">确认</a>
	</div>
	<form id="J_FrmBid" name="bidForm" action="apiExpressPay.jsp"
		method="post">
		<input type="hidden" name="titles" value="GS3673"> <input
			type="hidden" name="order_id" value="1000983"> <input
			type="hidden" name="seller_id"
			value="b9b750dbef1730c00239d03a7bfb5a25"> <input
			type="hidden" name="price_or" id="price_or" value="1"> <input
			type="hidden" name="price" id="re_price" value="298"> <input
			type="hidden" name="number" id="re_number" value="1">
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#J_LinkBuy").click(function() {
				$("form").submit();
			});
		});
	</script>
</body>
</html>