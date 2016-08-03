package com.aurfy.haze.web.vo.API;

import com.aurfy.haze.web.vo.VO;

public class APISingleQueryReqVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9137057512357103511L;

	private String url;
	private String token;
	private String transaction_id;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APISingleQueryReqVO [url=");
		builder.append(url);
		builder.append(", token=");
		builder.append(token);
		builder.append(", transaction_id=");
		builder.append(transaction_id);
		builder.append("]");
		return builder.toString();
	}

}
