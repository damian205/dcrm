package crm.model.DAO;

import crm.model.User;

public interface UserDAO {
	
	User findByUserName(String username);
	boolean addNewUser(String username, String password);

}
