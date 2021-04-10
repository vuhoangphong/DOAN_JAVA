package web.badminton.controller;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


import web.badminton.service.UserDetailsServiceImpl;
import web.badminton.service.UserService;

@Controller
@RequestMapping
public class MainController {
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	// @RequestMapping(value = "/register", method = RequestMethod.POST)
	// public ModelAndView register(@ModelAttribute RegisterDTO register) {
	// ModelAndView view = new ModelAndView("welcome");
	//
	// if(registerRepository.exist(register) == 1) {
	// view.addObject("success", "exist");
	// return view;
	// }
	//
	// if(registerRepository.insert(register) == 1) {
	// view.addObject("success", true);
	// }else {
	// view.addObject("success", false);
	// }
	// return view;
	// }
	@RequestMapping("/createUser")
	public ModelAndView createUser() {
		return new ModelAndView("createUser");
	}
	
	
	public static void main(String[] args) {
	
	
	}
}