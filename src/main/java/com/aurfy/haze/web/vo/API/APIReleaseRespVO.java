package com.aurfy.haze.web.vo.API;

public class APIReleaseRespVO {
	private String id;
	private String status;
	private String released;
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

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
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
		builder.append("APIReleaseRespVO [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", released=");
		builder.append(released);
		builder.append(", transaction_id=");
		builder.append(transaction_id);
		builder.append("]");
		return builder.toString();
	}

}
