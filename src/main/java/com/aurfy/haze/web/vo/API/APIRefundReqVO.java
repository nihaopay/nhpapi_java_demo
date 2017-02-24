package com.aurfy.haze.web.vo.API;

import com.aurfy.haze.web.vo.VO;

public class APIRefundReqVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5001493015988084673L;

	private String url;
	private String token;
	private String transaction_id;
	private String amount;
	private String currency;
	private String rmb_amount;
	private String reason;

	public String getInput() {
		StringBuilder result = new StringBuilder();
		result.append("currency=" + currency);
		
		if(!(amount==null||amount.isEmpty())){
			result.append("&amount=" + amount);
		}
		
		if(!(rmb_amount==null||rmb_amount.isEmpty())){
			result.append("&rmb_amount=" + rmb_amount);
		}
		
		if (!(reason == null || reason.isEmpty())) {
			result.append("&reason=" + reason);
		}
		return result.toString();
	}

	public String getRmb_amount() {
		return rmb_amount;
	}


	public void setRmb_amount(String rmb_amount) {
		this.rmb_amount = rmb_amount;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "APIRefundReqVO [url=" + url + ", token=" + token
				+ ", transaction_id=" + transaction_id + ", amount=" + amount
				+ ", currency=" + currency + ", rmb_amount=" + rmb_amount
				+ ", reason=" + reason + "]";
	}
	
}
