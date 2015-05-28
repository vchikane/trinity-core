package org.zigmoi.trinity.usermanagement.api.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.zigmoi.trinity.core.api.OHeader;

public class OWUserDetails extends OHeader {

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("user_name")
	private String userName;

	@JsonProperty("role")
	private String role;

	@JsonProperty("is_active")
	private String active;


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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

}
