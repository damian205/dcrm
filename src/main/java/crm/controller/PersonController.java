package crm.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;




import crm.model.Person;
import crm.model.DAO.PersonDAO;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

	@Autowired
	private PersonDAO _personDao;
	
    @RequestMapping
    public String sayHello(){
        return "Hello World";
    }
    
    @RequestMapping(value="/save")
    @ResponseBody
    public String create() {
      try {
    	  Person person = new Person();
          person.setName("new"); person.setCountry("shit");
           
          _personDao.save(person);
          
      }
      catch(Exception ex) {
        return ex.getMessage();
      }
      return "User succesfully saved!";
    }
    
}