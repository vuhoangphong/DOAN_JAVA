package web.badminton.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.badminton.constant.Constant;
import web.badminton.dto.RegisterShopDTO;
import web.badminton.service.ShopService;
import web.badminton.vo.User;

@Controller
public class RegisterShopController {

	@Autowired
	HttpSession session;
	@Autowired
	ShopService	shopService;
	
	@RequestMapping(value = "/registerShop", method = RequestMethod.POST)
	@ResponseBody
	public String registerShop(@RequestParam String nameShop,@ModelAttribute RegisterShopDTO registerShopDTO) {	
		
		if(shopService.checkExistNameShop(nameShop) == 1) {			
			return "exist";
		}if(shopService.insertShop(registerShopDTO)== 1) {
			return "registerShopSuccess";
		}else {
			return"registerShopFail";
		}
	}

	
}
