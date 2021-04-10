package web.badminton.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.badminton.dto.RegisterUserDTO;
import web.badminton.service.RegisterUserService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterUserController {

	@Autowired
	RegisterUserService registerUserService;

	// register user
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	@ResponseBody
	public String register(@ModelAttribute RegisterUserDTO register)
			throws AddressException, MessagingException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		if(registerUserService.checkAccountExist(register) == 1) {			
	 		return "exist";
		 }		
		 if(registerUserService.insertUser(register)== 1) {
			RegisterUserService.sendEmailVerify(register.getAccount(), register.getEmail());
		 	return "true";
		 }else {
		 	return "false";
		 }
		 	
    }
    
     @RequestMapping(value="/verify", method=RequestMethod.GET)
     public ModelAndView checkVerifyMail(@RequestParam String id)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		ModelAndView view = new ModelAndView("verify");
			if(registerUserService.verify(id)==true){
				view.addObject("check", true);
				return view;
			}
			view.addObject("check", false);
		return view;
     }
    
}
