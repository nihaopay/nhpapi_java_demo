package com.aurfy.haze.web.vo.API;

public class APIExpressPayRespVO {

	private String id;
	private String status;
	private String amount;
	private String currency;
	private String captured;
	private String time;
	private String reference;
	private String note;

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

	public String getCaptured() {
		return captured;
	}

	public void setCaptured(String captured) {
		this.captured = captured;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIExpressPayRespVO [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", captured=");
		builder.append(captured);
		builder.append(", time=");
		builder.append(time);
		builder.append(", reference=");
		builder.append(reference);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}

}
