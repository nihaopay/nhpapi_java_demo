<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
        <title>NihaoPay</title>
        <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="js/jquery.qrcode.min.js"></script>
    </head>
    <body>
    <div class="top-z">
          <div class="top-r cj"><p>Response parameters:</p></div>
          	${resultMap }
    </div>
    <div class="cj logo"><p>Show page</p></div>
    <!-- <begin>-->
    <hr>
    <div class="big-z">
       <div class="cj pay-z">
            <div class="pay-left">
                <p>Timeout:${resultMap.timeout} minutes after closing</p>
            </div>
            <div class="pay-right">
                 <p><span>Amount:${resultMap.amount}(Amount as an integer of the smallest unit in the currency. For example, $10.50 in USD would be 1050.)</span></p>
            </div>
            <div class="pay-right">
                 <p><span>Currency:${resultMap.currency}</span></p>
            </div>
            <div class="clear"></div>
            <div class="ewei-ma" >
                 <div id="qrcodeimg"></div>
                <script>
					jQuery('#qrcodeimg').qrcode("${resultMap.code_url}");
				</script>
    			<div class="msg" id="_message">Need merchant query payment result.</div>
            </div>
        </div>
    </div>
    <!-- <end>-->
    </body>
</html>