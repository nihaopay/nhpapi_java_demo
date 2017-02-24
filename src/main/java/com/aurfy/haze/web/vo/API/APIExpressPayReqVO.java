package com.aurfy.haze.web.vo.API;

import com.aurfy.haze.web.vo.VO;

public class APIExpressPayReqVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2746998490973613014L;

	private String url;
	private String token;
	private String reference;
	private String amount;
	private String currency;
	private String rmb_amount;
	private String card_number;
	private String card_exp_month;
	private String card_exp_year;
	private String card_cvv;
	private String capture;
	private String description;
	private String note;

	public String getInput() {
		StringBuilder result = new StringBuilder();
		result.append("currency=" + currency + "&card_number=" + card_number + "&card_exp_month="
				+ card_exp_month+"&card_exp_year=" + card_exp_year + "&card_cvv=" + card_cvv + "&capture=" + capture);
		if (!(reference == null || reference.isEmpty())) {
			result.append("&reference=" + reference);
		}
		if(!(amount==null||amount.isEmpty())){
			result.append("&amount=" + amount);
		}
		
		if(!(rmb_amount==null||rmb_amount.isEmpty())){
			result.append("&rmb_amount=" + rmb_amount);
		}
		
		if (!(description == null || description.isEmpty())) {
			result.append("&description=" + description);
		}
		if (!(note == null || note.isEmpty())) {
			result.append("&note=" + note);
		}
		return result.toString();
	}


	public String getRmb_amount() {
		return rmb_amount;
	}


	public void setRmb_amount(String rmb_amount) {
		this.rmb_amount = rmb_amount;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCard_exp_month() {
		return card_exp_month;
	}

	public void setCard_exp_month(String card_exp_month) {
		this.card_exp_month = card_exp_month;
	}

	public String getCard_exp_year() {
		return card_exp_year;
	}

	public void setCard_exp_year(String card_exp_year) {
		this.card_exp_year = card_exp_year;
	}

	public String getCard_cvv() {
		return card_cvv;
	}

	public void setCard_cvv(String card_cvv) {
		this.card_cvv = card_cvv;
	}

	public String getCapture() {
		return capture;
	}

	public void setCapture(String capture) {
		this.capture = capture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "APIExpressPayReqVO [url=" + url + ", token=" + token
				+ ", reference=" + reference + ", amount=" + amount
				+ ", currency=" + currency + ", rmb_amount=" + rmb_amount
				+ ", card_number=" + card_number + ", card_exp_month="
				+ card_exp_month + ", card_exp_year=" + card_exp_year
				+ ", card_cvv=" + card_cvv + ", capture=" + capture
				+ ", description=" + description + ", note=" + note + "]";
	}


}
