package com.aurfy.haze.web.vo.API;

public class APICaptureRespVO {
	private String id;
	private String status;
	private String captured;
	private String transaction_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaptured() {
		return captured;
	}

	public void setCaptured(String captured) {
		this.captured = captured;
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
		builder.append("APICaptureRespVO [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", captured=");
		builder.append(captured);
		builder.append(", transaction_id=");
		builder.append(transaction_id);
		builder.append("]");
		return builder.toString();
	}

}
