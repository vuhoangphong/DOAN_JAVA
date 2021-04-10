package web.badminton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    
    // login 
    @RequestMapping(value="login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }
}