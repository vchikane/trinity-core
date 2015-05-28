package org.zigmoi.trinity.core.api;

import org.codehaus.jackson.annotate.JsonProperty;


public class OHeader {
	
	//output JSON object will be created with field name as specified in @JsonProperty
	@JsonProperty("success")
	private boolean success;
	
	@JsonProperty("hdrInterceptRule")
	private String hdrInterceptRule;
	
	@JsonProperty("userMsg")
	private String userMsg;
	
	@JsonProperty("debugMsg")
	private String debugMsg;

	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUserMsg() {
		return userMsg;
	}
		
	public String getHdrInterceptRule() {
		return hdrInterceptRule;
	}

	public void setHdrInterceptRule(String hdrInterceptRule) {
		this.hdrInterceptRule = hdrInterceptRule;
	}

	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

	public String getDebugMsg() {
		return debugMsg;
	}

	public void setDebugMsg(String debugMsg) {
		this.debugMsg = debugMsg;
	}

	//method to reflect back some input params for Clients Use for Error Handling.
	public void mirrorInput(IHeader hdr){
		this.setHdrInterceptRule(hdr.getInterceptRule());
	}
}
