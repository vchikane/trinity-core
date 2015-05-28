package org.zigmoi.trinity.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zigmoi.trinity.usermanagement.dao.MyUserDAO;
import org.zigmoi.trinity.usermanagement.model.MyUser;

/**
 * Implements Spring Security {@link UserDetailsService} that is injected into authentication provider in configuration XML.
 * It interacts with domain, loads user details and wraps it into {@link UserContext} which implements Spring Security {@link UserDetails}.
 */
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MyUserDAO myUserDAO;


	/**
	 * This will be called from
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
	 * when {@link AuthenticationService#authenticate(java.lang.String, java.lang.String)} calls
	 * {@link AuthenticationManager#authenticate(org.springframework.security.core.Authentication)}.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser mu = myUserDAO.getUserById(username);
		System.out.println("*** Fetched User Details.");
		return new UserContext(mu);
	}
}
