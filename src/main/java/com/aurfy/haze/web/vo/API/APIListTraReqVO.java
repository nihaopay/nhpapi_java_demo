package com.aurfy.haze.web.vo.API;

import com.aurfy.haze.web.vo.VO;

public class APIListTraReqVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7667215497414639708L;
	private String url;
	private String token;
	private String limit;
	private String starting_after;
	private String ending_before;

	public String getInput() {
		StringBuilder result = new StringBuilder();
		if (!(limit == null || limit.isEmpty())) {
			result.append("limit=" + limit);
		}
		if (!(starting_after == null || starting_after.isEmpty())) {
			result.append("&starting_after=" + starting_after);
		}
		if (!(ending_before == null || ending_before.isEmpty())) {
			result.append("&ending_before=" + ending_before);
		}
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

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getStarting_after() {
		return starting_after;
	}

	public void setStarting_after(String starting_after) {
		this.starting_after = starting_after;
	}

	public String getEnding_before() {
		return ending_before;
	}

	public void setEnding_before(String ending_before) {
		this.ending_before = ending_before;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIListTraReqVO [url=");
		builder.append(url);
		builder.append(", token=");
		builder.append(token);
		builder.append(", limit=");
		builder.append(limit);
		builder.append(", starting_after=");
		builder.append(starting_after);
		builder.append(", ending_before=");
		builder.append(ending_before);
		builder.append("]");
		return builder.toString();
	}

}
