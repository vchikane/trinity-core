package org.zigmoi.trinity.usermanagement.dao;

import java.util.List;
import org.zigmoi.trinity.usermanagement.model.MyUser;


public interface MyUserDAO {

	public void addMyUser(MyUser u);
	public MyUser getUserById(String id);
	public List<MyUser> listUsers();
	public void changePassword(String userId, String password);
	public void activateUser(String userId);
    public void deActivateUser(String userId);
	public void modifyUser(String userId, String userName);
	public void changeRole(String userId, String role);
}
