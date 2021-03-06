package org.zigmoi.trinity.usermanagement.api.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.OHeader;

public class OWActivateUser extends OHeader {
	
	@JsonProperty("msg")
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
