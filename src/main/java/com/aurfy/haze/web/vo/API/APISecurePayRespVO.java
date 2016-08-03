package com.aurfy.haze.web.vo.API;

public class APISecurePayRespVO {

	private String id;
	private String status;
	private String amount;
	private String currency;
	private String time;
	private String reference;
	private String note;

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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "APISecurePayRespVO [id=" + id + ", status=" + status
				+ ", amount=" + amount + ", currency=" + currency + ", time="
				+ time + ", reference=" + reference + ", note=" + note + "]";
	}
	
}
