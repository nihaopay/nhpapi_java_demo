package com.aurfy.haze.web.vo.API;

import com.aurfy.haze.web.vo.VO;

public class APICaptureReqVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5863460127210140703L;
	private String url;
	private String token;
	private String transaction_id;
	private String amount;
	private String currency;

	public String getInput() {
		StringBuilder result = new StringBuilder();
		result.append("amount=" + amount + "&currency=" + currency);
		return result.toString();
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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APICaptureReqVO [url=");
		builder.append(url);
		builder.append(", token=");
		builder.append(token);
		builder.append(", transaction_id=");
		builder.append(transaction_id);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append("]");
		return builder.toString();
	}

}
