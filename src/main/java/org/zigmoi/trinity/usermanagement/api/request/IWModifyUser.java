package org.zigmoi.trinity.usermanagement.api.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.IHeader;

public class IWModifyUser extends IHeader {
	
	//Input JSON Object should have field names as specified in @JsonProperty
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("user_name")
	private String userName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	// validations 
	
	public boolean validate(){
		return true;
		
	}


}


