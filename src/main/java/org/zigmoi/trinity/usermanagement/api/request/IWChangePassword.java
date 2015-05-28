package org.zigmoi.trinity.usermanagement.api.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.IHeader;

public class IWChangePassword extends IHeader{
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("password")
	private String password;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	// validations 
	public boolean validate(){
			return true;
	}
	 
}
