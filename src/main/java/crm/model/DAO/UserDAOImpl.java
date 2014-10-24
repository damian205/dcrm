package crm.model.DAO;

import java.util.ArrayList;
import java.util.List;
 




import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 




import crm.model.User;
 
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
 
	@Autowired
	private SessionFactory sessionFactory;
 
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
 
		List<User> users = new ArrayList<User>();
 
		users = sessionFactory.getCurrentSession()
			.createQuery("from User where username=?")
			.setParameter(0, username)
			.list();
 
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
 
	}
	

	public boolean addNewUser(String username, String password) {
 
		User user = new User(username, password);
		 Session session = this.sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
	        session.persist(user);
	        tx.commit();
	        session.close();
	        	return true;
	}
 
}