package org.zigmoi.trinity.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.zigmoi.trinity.usermanagement.model.MyUser;

/** This object wraps {@link MyUser} and makes it {@link UserDetails} so that Spring Security can use it. */
public class UserContext implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private MyUser user;
	
	public UserContext(MyUser user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String role : user.getRole().split(",")) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUserId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if ("N".equalsIgnoreCase(user.getIsAccountLocked())){
			return true;
		}
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
			return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof UserContext
			&& Objects.equals(user, ((UserContext) o).user);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	@Override
	public String toString() {
		return "UserContext{" +
			"user=" + user +
			'}';
	}
}
