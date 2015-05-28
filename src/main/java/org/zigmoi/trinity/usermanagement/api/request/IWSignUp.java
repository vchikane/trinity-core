package org.zigmoi.trinity.usermanagement.api.request;

import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.IHeader;

public class IWSignUp extends IHeader {
	
	//Input JSON Object should have field names as specified in @JsonProperty
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("user_name")
	private String userName;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("role")
	private String role;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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


