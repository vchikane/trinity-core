package org.zigmoi.trinity.usermanagement.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zigmoi.trinity.usermanagement.model.MyUser;

@Repository("myUserDAO")
public class MyUserDAOImpl implements MyUserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDAOImpl.class);

	@Autowired   
	private SessionFactory sessionFactory;

	@Override
	public void addMyUser(MyUser u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u); // insert operation
		logger.info("User saved successfully, User Details="+u);

	}
	
	@Override
	public MyUser getUserById(String id) {
		Session session = this.sessionFactory.getCurrentSession();		
		MyUser u = (MyUser) session.load(MyUser.class, new String(id)); // Select * operation with where clause
		//u.setPassword("*"); //hiding password dont do this will update the db 
		logger.info("User loaded successfully, User details="+u);
		return u;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MyUser> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		//Model class  name is case Sensitive MyUser is different from myuser
		List<MyUser> usersList = session.createQuery("from MyUser").list(); // Select * operation
		for(MyUser u : usersList){
			logger.info("User List::"+u);
		}
		return usersList;
	}

	@Override
	public void changePassword(String userId,String password) {
		Session session = this.sessionFactory.getCurrentSession();
				
		//following are model and its variables not table and column names.
		Query q = session.createQuery("update MyUser set password=:password where userId=:userId");
		//q.setLong("lastUpdated", 1111L);
		q.setString("password", password);
		q.setString("userId", userId);
		q.executeUpdate();
	}

	@Override
	public void activateUser(String userId) {
		    Session session = this.sessionFactory.getCurrentSession();
		    Query q =session.createQuery("update MyUser set active='Y' where userId=:userId");
			q.setString("userId", userId);
			q.executeUpdate();
	}

	@Override
	public void deActivateUser(String userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q =session.createQuery("update MyUser set active='N' where userId=:userId");
		q.setString("userId", userId);
		q.executeUpdate();
	}

	@Override
	public void modifyUser(String userId,String userName) {
		Session session = this.sessionFactory.getCurrentSession();

		//following are model and its variables not table and column names.
		Query q = session.createQuery("update MyUser set fullname=:userName where userId=:userId");
		q.setString("userName", userName);
		q.setString("userId", userId);
		q.executeUpdate();
	}

	@Override
	public void changeRole(String userId,String role) {
		Session session = this.sessionFactory.getCurrentSession();

		//following are model and its variables not table and column names.
		Query q = session.createQuery("update MyUser set role=:role where userId=:userId");
		q.setString("role", role);
		q.setString("userId", userId);
		q.executeUpdate();
	}
	
}
