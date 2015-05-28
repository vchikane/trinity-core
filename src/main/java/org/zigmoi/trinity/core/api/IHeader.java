package org.zigmoi.trinity.core.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class IHeader {

	@JsonProperty("hdrInterceptRule")
	private String hdrInterceptRule;

	// UI rule mirror (Following  don't have any affect on JSON parsing)
	public final static String INTERCEPT_RULE_A = "pass_response_to_app_and_show_error";
	public final static String INTERCEPT_RULE_B = "dont_pass_response_to_app_and_show_error";
	public final static String INTERCEPT_RULE_C = "pass_response_to_app_and_donot_show_error";

	public void setInterceptRule(String interceptRule) {
		this.hdrInterceptRule = interceptRule;
	}

	public String getInterceptRule() {
		return this.hdrInterceptRule;
	}

	public final IHeader getHeader() {
		return this;
	}
}
