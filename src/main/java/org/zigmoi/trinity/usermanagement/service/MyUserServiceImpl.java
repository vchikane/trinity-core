package org.zigmoi.trinity.usermanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zigmoi.trinity.usermanagement.dao.MyUserDAO;
import org.zigmoi.trinity.usermanagement.model.MyUser;

@Service("myuserService")
public class MyUserServiceImpl implements MyUserService {
	
	@Autowired
	@Qualifier("myUserDAO")
	private MyUserDAO myUserDAO;

	@Override
	@Transactional
	public void addMyUser(MyUser u) {
		
		//encrypting password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		this.myUserDAO.addMyUser(u);
	}
	
	@Override
	@Transactional
	public MyUser getUserById(String userId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId =auth.getName();
		boolean roleValid=false;
		for(GrantedAuthority role : auth.getAuthorities()){
			if (role.toString().equals("ADMIN")){
				roleValid=true;
			}
		}
		//System.out.println("Current Logged in User:"+auth.getName());
		if(loggedInUserId.equals(userId) || roleValid){
			MyUser u = new MyUser(this.myUserDAO.getUserById(userId));
			u.setPassword("*"); //this works as new MyUser doesn't create a persistent model object so changes wont go in DB.
			return u;
		}
		else{
			throw new RuntimeException("Cant fetch other users details, Privledges required.");
		}
	}
	
	@Override
	@Transactional
	public List<MyUser> listUsers() {
		List<MyUser> userlist= new ArrayList<MyUser>();
		for(MyUser u: this.myUserDAO.listUsers()){
			//creating a object using new as this wont create a persistent model object
			userlist.add(new MyUser(u));
		}
		
		//hiding password
		for(MyUser u : userlist){
			u.setPassword("*");
		}
		return userlist;
	}
	
	@Override
	@Transactional
	public void changePassword(String userId,String password) {
		//check logged in user  can change only his password.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId =auth.getName();
		boolean roleValid=false;
		for(GrantedAuthority role : auth.getAuthorities()){
			if (role.toString().equals("ADMIN")){
				roleValid=true;
			}
		}
		//System.out.println("Current Logged in User:"+auth.getName());
		if(loggedInUserId.equals(userId) || roleValid){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			this.myUserDAO.changePassword(userId,passwordEncoder.encode(password));
		}
		else{
			throw new RuntimeException("Cant change other users password, Privledges required.");
		}
		
		
	}

	@Override
	@Transactional
	public void activateUser(String userId) {
		this.myUserDAO.activateUser(userId);
	}


	@Override
	@Transactional
	public void deActivateUser(String userId) {
		this.myUserDAO.deActivateUser(userId);
	}

	@Override
	@Transactional
	public void modifyUser(String userId, String userName) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId =auth.getName();
		boolean roleValid=false;
		for(GrantedAuthority role : auth.getAuthorities()){
			if (role.toString().equals("ADMIN")){
				roleValid=true;
			}
		}
		
		if(loggedInUserId.equals(userId) || roleValid){
			this.myUserDAO.modifyUser(userId,userName);
		} else {
			throw new RuntimeException("Cant change other users profile, Privledges required.");
		}

	}

	@Override
	@Transactional
	public void changeRole(String userId, String role) {
       	this.myUserDAO.changeRole(userId, role);
	}

}
