package org.zigmoi.trinity.usermanagement.model;

//import javax.validation.constraints.Min;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "USER_MASTER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) //TODO comment required for serialization of entity classes
public class MyUser {

	public MyUser() {
		// TODO Auto-generated constructor stub
	}

	public MyUser(MyUser u) {
		this.setUserId(u.getUserId()); 
		this.setFullname(u.getFullname());  
		this.setPassword(u.getPassword());  
		this.setActive(u.getActive());  
		this.setLastUpdated(u.getLastUpdated());  
		this.setValidTill(u.getValidTill());  
		this.setRole(u.getRole());  
		this.setCreatedOn(u.getCreatedOn());  
		this.setIsAccountLocked(u.getIsAccountLocked());  
	}
	
	@Id
	@Column(name = "USER_ID")
	// @Min(12)
	private String userId;

	@Column(name = "USER_NAME")
	private String fullname;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "LAST_UPDATED")
	private long lastUpdated;

	@Column(name = "VALID_TILL")
	private long validTill;

	@Column(name = "ROLE_ID")
	private String role;

	@Column(name = "CREATED_ON")
	private long createdOn;

	@Column(name = "LOCKED")
	private String isAccountLocked;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public long getValidTill() {
		return validTill;
	}

	public void setValidTill(long validTill) {
		this.validTill = validTill;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public String getIsAccountLocked() {
		return isAccountLocked;
	}

	public void setIsAccountLocked(String isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	@Override
	public String toString() {
		return "MyUser [userId=" + userId + ", fullname=" + fullname
				+ ", password=" + password + ", active=" + active
				+ ", lastUpdated=" + lastUpdated + ", validTill=" + validTill
				+ ", role=" + role + ", createdOn=" + createdOn
				+ ", isAccountLocked=" + isAccountLocked + "]";
	}
}
