package org.zigmoi.trinity.usermanagement.api.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.IHeader;

public class IWChangeRole extends IHeader {
	
	//Input JSON Object should have field names as specified in @JsonProperty
	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("role")
	private String role;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	// validations 
	public boolean validate(){
		return true;
		
	}


}


