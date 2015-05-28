package org.zigmoi.trinity.core.controller;

import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed; //add dependency in pom for jsr250-api version 1.0 in group id javax.annotation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zigmoi.trinity.core.security.AuthenticationService;
import org.zigmoi.trinity.core.security.TokenInfo;
import org.zigmoi.trinity.core.security.TokenManager;

@RestController
public class MainRestController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenManager tokenManager;

	@PostConstruct
	public void init() {
		System.out.println(" *** MainRestController.init with: " + applicationContext);
	}

	@RequestMapping(value = "/login", produces = "text/plain", method = RequestMethod.POST)
	public String login() {
		System.out.println(" *** MainRestController.login");
		return "Login with POST request containing username password in Headers as X-Username & X-Password, "
				+ "will return X-Auth-Token.";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		System.out.println(" *** MainRestController.logout");
		return "Logout invalidates token on server-side. It must come as a POST request with valid X-Auth-Token, URL is configured for MyAuthenticationFilter.";
	}

	@RequestMapping("/test")
	public String test() {
		System.out.println(" *** MainRestController.test");
		return "SecurityContext: " + SecurityContextHolder.getContext();
	}

	// standard JSR 250 annotation
	@RolesAllowed("ADMIN")
	@RequestMapping("/admin")
	public String admin() {
		System.out.println(" *** MainRestController.admin");
		return "Cool, you're admin!";
	}

	@RequestMapping("/secure/service1")
	public String service1() {
		System.out.println(" *** MainRestController.service1");
		return "Any authorized user should have access.";
	}

	@RequestMapping("/secure/mytokens")
	public Collection<TokenInfo> myTokens() {
		System.out.println(" *** MainRestController.myTokens");
		UserDetails currentUser = authenticationService.currentUser();
		return tokenManager.getUserTokens(currentUser);
	}

	// Spring annotation virtually equivalent with @RolesAllowed - except for...
	// WARNING: @Secured by default works only with roles starting with ROLE_ prefix, see this for more:
	//Both roles should be there to access this url
	@Secured({"ROLE_SPECIAL", "ADMIN"})
	@RequestMapping("/secure/special")
	public String special() {
		System.out.println(" *** MainRestController.special");
		return "ROLE_SPECIAL users should have access.";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/secure/allusers")
	public Map<String, UserDetails> allUsers() {
		System.out.println(" *** MainRestController.allUsers");
		return tokenManager.getValidUsers();
	}
}
