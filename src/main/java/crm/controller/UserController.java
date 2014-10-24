package crm.controller;

import javax.activation.DataSource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import crm.model.User;
import crm.model.DAO.UserDAO;
import crm.model.DAO.UserDAOImpl;


@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserDAO _userdao;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public String defaultPage(Model model) {

		//ModelAndView model = new ModelAndView();
		model.addAttribute("title", "Spring Security + Hibernate Example");
		model.addAttribute("message", "This is default page!");
		//model.setViewName("hello");
		return "hello";

	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String displayAll(Model model,
			@RequestParam("name") String name, HttpServletRequest request) {

		
		
		
		User user = _userdao.findByUserName("alex");
		

		String message = "Hi " + user.getUsername() + "!";
		model.addAttribute("val", message);
		
		return "newUser";

	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String adminPage(Model model) {

		//ModelAndView model = new ModelAndView();
		model.addAttribute("title", "Spring Security + Hibernate Example");
		model.addAttribute("message", "This page is for ROLE_ADMIN only!");
		//model.setViewName("admin");

		return "admin";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		if (error != null) {
			model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}

		return "login";

	}
	
	@RequestMapping(value = "/new")
	public String add(Model model,
			
			HttpServletRequest request) {

		

			
		
	
		
		
		return "newUser";

	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String addUser(Model model,
			@ModelAttribute User user,
			HttpServletRequest request) {

		

			boolean added = _userdao.addNewUser(user.getUsername(), user.getPassword());
			String message;
			if(added)
			{
				message = new String("yes");
			}
			else
			{
				message = new String("no");
			}
			model.addAttribute("val", message);
	
		
		
		return "newUser";

	}
	

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Model model) {

		//ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addAttribute("username", userDetail.getUsername());

		}

		//model.setViewName("403");
		return "403";

	}

}
