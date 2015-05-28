package org.zigmoi.trinity.usermanagement.api.response;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.OHeader;

public class OWListUsers extends OHeader {
	
	@JsonProperty("msg")
	private List<User> msg;

	public List<User> getMsg() {
		return msg;
	}

	public void setMsg(List<User> msg) {
		this.msg = msg;
	}
	
	
}
