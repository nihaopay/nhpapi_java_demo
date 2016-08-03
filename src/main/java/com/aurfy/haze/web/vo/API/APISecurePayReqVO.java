package com.aurfy.haze.web.vo.API;

import com.aurfy.haze.web.vo.VO;

public class APISecurePayReqVO implements VO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7478024425472705470L;
	
	private String url;
	private String callback_url;
	private String show_url;
	private String ipn_url;
	private String vendor;
	private String reference;
	private String amount;
	private String currency;
	private String description;
	private String terminal;
	private String timeout;
	private String note;
	private String token;
	
	public String getInput(){
		StringBuilder result=new StringBuilder();
		result.append("callback_url="+ callback_url + "&ipn_url=" + ipn_url + "&vendor=" + vendor);
		result.append("&reference=" + reference + "&amount=" + amount+ "&currency=" + currency);
		if(!(description==null||description.isEmpty())){
			result.append("&description=" + description);
		}
		if(!(terminal==null||terminal.isEmpty())){
			result.append("&terminal=" + terminal);
		}
		if(!(timeout==null||timeout.isEmpty())){
			result.append("&timeout=" + timeout);
		}
		if(!(note==null||note.isEmpty())){
			result.append("&note=" + note);
		}
		if(!(show_url==null||show_url.isEmpty())){
			result.append("&show_url=" + show_url);
		}
		return result.toString();
	}


	@Override
	public String toString() {
		return "APISecurePayReqVO [url=" + url + ", callback_url="
				+ callback_url + ", ipn_url=" + ipn_url + ", vendor=" + vendor
				+ ", reference=" + reference + ", amount=" + amount
				+ ", currency=" + currency + ", description=" + description
				+ ", terminal=" + terminal + ", timeout=" + timeout + ", note="
				+ note + ", token=" + token + "]";
	}





	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}
	public String getIpn_url() {
		return ipn_url;
	}
	public void setIpn_url(String ipn_url) {
		this.ipn_url = ipn_url;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}


	public String getTerminal() {
		return terminal;
	}


	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}


	public String getTimeout() {
		return timeout;
	}


	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}


	public String getShow_url() {
		return show_url;
	}


	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	

}
