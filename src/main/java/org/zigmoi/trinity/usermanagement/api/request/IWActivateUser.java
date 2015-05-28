package org.zigmoi.trinity.usermanagement.api.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.IHeader;

public class IWActivateUser extends IHeader{
	
	@JsonProperty("user_id")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	// validations
	public boolean validate(){
			return true;
	}
	 
}
