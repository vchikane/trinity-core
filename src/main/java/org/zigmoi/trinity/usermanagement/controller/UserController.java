package org.zigmoi.trinity.usermanagement.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zigmoi.trinity.usermanagement.api.request.IWActivateUser;
import org.zigmoi.trinity.usermanagement.api.request.IWChangePassword;
import org.zigmoi.trinity.usermanagement.api.request.IWChangeRole;
import org.zigmoi.trinity.usermanagement.api.request.IWDeActivateUser;
import org.zigmoi.trinity.usermanagement.api.request.IWModifyUser;
import org.zigmoi.trinity.usermanagement.api.request.IWSignUp;
import org.zigmoi.trinity.usermanagement.api.request.IWUserDetails;
import org.zigmoi.trinity.usermanagement.api.response.OWActivateUser;
import org.zigmoi.trinity.usermanagement.api.response.OWChangePassword;
import org.zigmoi.trinity.usermanagement.api.response.OWChangeRole;
import org.zigmoi.trinity.usermanagement.api.response.OWDeActivateUser;
import org.zigmoi.trinity.usermanagement.api.response.OWListUsers;
import org.zigmoi.trinity.usermanagement.api.response.OWModifyUser;
import org.zigmoi.trinity.usermanagement.api.response.OWSignUp;
import org.zigmoi.trinity.usermanagement.api.response.OWUserDetails;
import org.zigmoi.trinity.usermanagement.api.response.User;
import org.zigmoi.trinity.usermanagement.model.MyUser;
import org.zigmoi.trinity.usermanagement.service.MyUserService;

/**
 * Handles requests for the user management stuff.
 */
@RestController
public class UserController {

	@Autowired
	@Qualifier("myuserService")
	private MyUserService myuserService;

	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public OWSignUp addPerson(@RequestBody IWSignUp req) {
		// public OWSignUp addPerson() {
		// IWSignUp req = new IWSignUp();
		// req.setInterceptRule("INTERCEPT_RULE_A");
		// req.setPassword("1");
		// req.setRole("ADMIN");
		// req.setUserId("ashim_u");
		// req.setUserName("ashim usmani");
		OWSignUp resp;
		try {
			MyUser u = new MyUser();
			u.setActive("N");
			u.setCreatedOn(System.currentTimeMillis());
			u.setFullname(req.getUserName());
			u.setIsAccountLocked("N");
			u.setLastUpdated(System.currentTimeMillis());
			u.setUserId(req.getUserId());
			u.setPassword(req.getPassword());
			u.setValidTill(9999999999L);

			if (req.getRole().equalsIgnoreCase("")) {
				u.setRole("USER");
			} else {
				u.setRole(req.getRole());
			}

			this.myuserService.addMyUser(u);

			// setting success response in output wrapper.
			resp = new OWSignUp();
			resp.mirrorInput(req.getHeader());
			resp.setSuccess(true);
			resp.setMsg("OK, User created successfully.");
			return resp;

		} catch (Exception e) {
			// setting failure response in output wrapper.
			resp = new OWSignUp();
			resp.mirrorInput(req.getHeader());
			resp.setSuccess(false);
			resp.setUserMsg("Failed to add new user.");
			resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
					+ " Stack Trace:" + Arrays.toString(e.getStackTrace()));
			return resp;

		}
	}

	// change password
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
	public OWChangePassword changePassword(@RequestBody IWChangePassword inp) {
		OWChangePassword resp;
		try {
			this.myuserService.changePassword(inp.getUserId(), inp.getPassword());
			// setting success response in output wrapper.
			resp = new OWChangePassword();
			resp.mirrorInput(inp.getHeader());
			resp.setSuccess(true);
			resp.setMsg("OK, Password changed successfully.");
			return resp;
		} catch (Exception e) {
			// setting failure response in output wrapper.
			resp = new OWChangePassword();
			resp.mirrorInput(inp.getHeader());
			resp.setSuccess(false);
			resp.setUserMsg("Failed to change user password.");
			resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
					+ " Stack Trace:" + Arrays.toString(e.getStackTrace()));
			return resp;
		}

	}

	// edit user details
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/user/modify/profile", method = RequestMethod.POST)
	public OWModifyUser modifyUser(@RequestBody IWModifyUser req) {
		//cant directly update partial model in hibernate all null values will reflect in db too.
		//alternate is select data from db and set in all null fields and then update.

			OWModifyUser resp;
			try {
				this.myuserService.modifyUser(req.getUserId(), req.getUserName());
				// setting success response in output wrapper.
				resp = new OWModifyUser();
				resp.mirrorInput(req.getHeader());
				resp.setSuccess(true);
				resp.setMsg("OK, User profile changed successfully.");
				return resp;
			} catch (Exception e) {
				// setting failure response in output wrapper.
				resp = new OWModifyUser();
				resp.mirrorInput(req.getHeader());
				resp.setSuccess(false);
				resp.setUserMsg("Failed to change user profile.");
				resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
						+ " Stack Trace:" + Arrays.toString(e.getStackTrace()));
				return resp;
			}
	}

	//edit role
	@RolesAllowed("ADMIN")
	@RequestMapping(value = "/user/modify/role", method = RequestMethod.POST)
	public OWChangeRole changeRole(@RequestBody IWChangeRole req) {
		//cant directly update partial model in hibernate all null values will reflect in db too.
		//alternate is select data from db and set in all null fields and then update.

		OWChangeRole resp;
		try {
			this.myuserService.changeRole(req.getUserId(),req.getRole());
			// setting success response in output wrapper.
			resp = new OWChangeRole();
			resp.mirrorInput(req.getHeader());
			resp.setSuccess(true);
			resp.setMsg("OK, User role changed successfully.");
			return resp;
		} catch (Exception e) {
			// setting failure response in output wrapper.
			resp = new OWChangeRole();
			resp.mirrorInput(req.getHeader());
			resp.setSuccess(false);
			resp.setUserMsg("Failed to change user role.");
			resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
					+ " Stack Trace:" + Arrays.toString(e.getStackTrace()));
			return resp;
		}
	}

	// activate user
	@RolesAllowed("ADMIN")
	@RequestMapping(value = "/user/activate/{userid}", method = RequestMethod.GET)
	public OWActivateUser activateUser(@PathVariable("userid") String id) {
		IWActivateUser req;
		OWActivateUser resp;
		try{
			req = new IWActivateUser();
			req.setUserId(id);

			this.myuserService.activateUser(req.getUserId());

			// setting response
			resp = new OWActivateUser();
			resp.setSuccess(true);
		 	return resp;
		} catch (Exception e) {
			resp = new OWActivateUser();
			resp.setSuccess(false);
			resp.setUserMsg("Failed to activate user.");
			resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
				+ " Stack Trace:" +Arrays.toString(e.getStackTrace()));
			return resp;
		}
	}

	// de activate user
	@RolesAllowed("ADMIN")
	@RequestMapping(value = "/user/deactivate/{userid}", method = RequestMethod.GET)
	public OWDeActivateUser deActivateUser(@PathVariable("userid") String id) {
		IWDeActivateUser req;
		OWDeActivateUser resp;
		try{
			req = new IWDeActivateUser();
			req.setUserId(id);

			this.myuserService.deActivateUser(req.getUserId());

			//setting response
			resp = new OWDeActivateUser();
			resp.setSuccess(true);
			return resp;
		} catch (Exception e) {
			resp = new OWDeActivateUser();
			resp.setSuccess(false);
			resp.setUserMsg("Failed to de-activate user.");
			resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
					+ " Stack Trace:" + Arrays.toString(e.getStackTrace()));
			return resp;
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/user/fetch/{userid}", method = RequestMethod.GET)
	public OWUserDetails getUserById(@PathVariable("userid") String id) {
		IWUserDetails req;
		OWUserDetails resp;
		try{
			req = new IWUserDetails();
			req.setUserId(id);

			MyUser u =  this.myuserService.getUserById(req.getUserId());

			//setting response
			resp  = new OWUserDetails();
			resp.setUserName(u.getFullname());
			resp.setActive(u.getActive());
			resp.setRole(u.getRole());
			resp.setUserId(u.getUserId());
			resp.setSuccess(true);
			return resp;
		}
		catch(Exception e){
			resp = new OWUserDetails();
			resp.setSuccess(false);
			resp.setUserMsg("Failed to fetch user details.");
			resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
					+ " Stack Trace:" + Arrays.toString(e.getStackTrace()));
			return resp;
		}
	}

	@RolesAllowed("ADMIN")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public OWListUsers listUsers() {
		OWListUsers resp;
		try{
			List<User> userlist = new ArrayList<User>();
			User user;
			for (MyUser u : this.myuserService.listUsers()){
				user = new User();
				user.setUserId(u.getUserId());
				user.setUserName(u.getFullname());
				user.setRole(u.getRole());
				user.setActive(u.getActive());
				userlist.add(user);
				user= null;
			}
			resp = new OWListUsers();
			resp.setMsg(userlist);
			resp.setSuccess(true);
			return resp; 
		}
		catch(Exception e){
			resp = new OWListUsers();
			resp.setSuccess(false);
			resp.setUserMsg("Failed to fetch all user's details.");
			resp.setDebugMsg("Exception Message:" + e.getMessage().toString()
					+ " Stack Trace:" + Arrays.toString(e.getStackTrace()));
			return resp; 
		}
	}

}
