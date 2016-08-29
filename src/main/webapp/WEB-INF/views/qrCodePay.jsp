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
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

		<script type="text/javascript">
		var _status = "";
		var final_data = "";
		function query(){
    		var url = "${url}wechatpay/query/${resultMap.id}?callback=?";
    		jQuery.ajax({
                url: url,
    			async: false,
    			dataType: "jsonp",
                success: function (data) {
    				if(_status != data.status) {
    					_status = data.status;
    					$("#_message").text(data.msg);
    				}
    				if(data.status == 'SUCCESS' || data.status == 'FAIL') {
						$("#pay_form").attr("method", data.formMethod);
						$("#pay_form").attr("action", data.formUrl);
						$("#from_content").html(data.formStr);
						var timeout = setTimeout(function() {
							clearTimeout(timeout);
							document.getElementById("pay_form").submit();
						}, 3000);
					 }
                }
    		});
			setTimeout(query, 3000);
		}
			$(function() {
				query();
			});
		</script>
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
                <img src="${url}wechatpay/getQRCode?qrStr=${resultMap.code_url}">
    			<div class="msg" id="_message"></div>
            </div>
        </div>
    </div>
	<div>
		<form id="pay_form" action="" method="POST">
            <div id="from_content">
			</div>
		</form>
	</div>
	<hr>
    <!-- <end>-->
    </body>
</html>