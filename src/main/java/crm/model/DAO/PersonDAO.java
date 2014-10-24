package crm.model.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crm.model.Person;

@Repository
@Transactional
public class PersonDAO {
 
	 @Autowired
	 private SessionFactory _sessionFactory;
	  
	 private Session getSession() {
	    return _sessionFactory.getCurrentSession();
	 }
    
     public void save(Person p) {
     	  getSession().save(p);
    	     return;
     }
 
     @SuppressWarnings("unchecked")
     public List<Person> list() {
     	 return getSession().createQuery("from Person").list();
     }
 
}