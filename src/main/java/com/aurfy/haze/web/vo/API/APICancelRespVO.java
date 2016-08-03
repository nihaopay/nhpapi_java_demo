package com.aurfy.haze.web.vo.API;

public class APICancelRespVO {
	private String id;
	private String status;
	private String cancelled;
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

	public String getCancelled() {
		return cancelled;
	}

	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
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
		builder.append("APICancelRespVO [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", cancelled=");
		builder.append(cancelled);
		builder.append(", transaction_id=");
		builder.append(transaction_id);
		builder.append("]");
		return builder.toString();
	}

}
