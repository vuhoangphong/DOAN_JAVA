package web.badminton.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import web.badminton.constant.Constant;
import web.badminton.dto.RegisterUserDTO;
import web.badminton.service.ProductService;
import web.badminton.service.SearchService;
import web.badminton.service.UserService;
import web.badminton.service.WelcomeService;
import web.badminton.vo.ProductPortfolio;
import web.badminton.vo.User;

@Controller
public class WelcomeController {
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired 
	WelcomeService welcomeService;
	

	@RequestMapping("/")
	public ModelAndView firstPage() {
		ModelAndView view = new ModelAndView("welcome");
		User user = (User) session.getAttribute(Constant.USER);
		if (user != null) {
			view.addObject("loginSuccess", user.getFullName());
			view.addObject("user", user.getIdUser());
		}
		List<ProductPortfolio> a =  welcomeService.getAllNamePortfolios();
		view.addObject("listPortfolios", a);
		view.addObject("listProduct", productService.getAllProduct());
		view.addObject("countProductTpye", productService.countProductType());
	
		return view;
	}

	// //Login
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	// public String login(@RequestParam String accountLogin, @RequestParam String
	// passwordLogin) {
	// ModelAndView view = new ModelAndView("welcome");
	// if( userService.checkExist(accountLogin, passwordLogin) == 1) {
	// User user = userService.getUser(accountLogin, passwordLogin);
	// session.setAttribute(Constant.USER, user);

	// }else {
	// view.addObject("loginSuccess", false);
	// }
	// return "redirect:/";
	// }

	// logout
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String outLogin(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth !=null){
			new SecurityContextLogoutHandler().logout(request, response,auth );
		}
		return "redirect:/";
		
	}
	

	
	@RequestMapping(value = "/getproduct", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam int idType) {		
		ModelAndView view = new ModelAndView("search");
		try {
			User user = (User)session.getAttribute(Constant.USER);
			view.addObject("user",user.getIdUser());
		} catch (Exception e) {
			//TODO: handle exception
		}	
		if(idType!=0) {
			view.addObject("listProduct",productService.getListProduct(idType));	
			view.addObject("checked",idType);
			
		}else {
			view.addObject("listProduct",productService.getAllProduct());
			view.addObject("checked",idType);
			
		}		
			
		view.addObject("countProductTpye", productService.countProductType());
		view.addObject("listPortfolios", welcomeService.getAllNamePortfolios());
	
		return view;
	}

	// @RequestMapping(value = "/getproductbrands", method = RequestMethod.GET)
	// public ModelAndView getProductBrands(@RequestParam int idBrand) {		
	// 	ModelAndView view = new ModelAndView("search");
	// 	if(idBrand!=0) {
	// 		view.addObject("listProduct",searchService.findProduct(idBrand));	
			
			
	// 	}else {
	// 		view.addObject("listProduct",searchService.findProduct(idBrand));
	// 	}				
	// 	view.addObject("countProductTpye", productService.countProductType());
	// 	view.addObject("listPortfolios", welcomeService.getAllNamePortfolios());
	// 	return view;
	// }
	
	@RequestMapping(value = "/getproductlowtohight", method = RequestMethod.GET)
	public ModelAndView getProductLowToHight(@RequestParam int idType ,@RequestParam int value ) {
		ModelAndView view = new ModelAndView("search");
		if(idType!=0) {
			view.addObject("listProduct",productService.getProductLowTohight(idType));	
			view.addObject("checked",idType);
			view.addObject("value",value);
			
		}else {
			view.addObject("listProduct",productService.getAllProductLowTohight());
			view.addObject("checked",idType);
			view.addObject("value",value);
		}		
		view.addObject("countProductTpye", productService.countProductType());
		view.addObject("listPortfolios", welcomeService.getAllNamePortfolios());
		return view;
	}
	

	@RequestMapping(value = "/getproducthighttolow", method = RequestMethod.GET)
	public ModelAndView getProductHightToLow(@RequestParam int idType, @RequestParam int value) {
		ModelAndView view = new ModelAndView("welcome");
		if(idType!=0) {
			view.addObject("listProduct",productService.getProductHightToLow(idType));	
			view.addObject("checked",idType);
			view.addObject("value",value);
		}else {
			view.addObject("listProduct",productService.getAllProductHightToLow());
			view.addObject("checked",idType);
			view.addObject("value",value);
		}				
		view.addObject("countProductTpye", productService.countProductType());
		view.addObject("listPortfolios", welcomeService.getAllNamePortfolios());
		return view;
	}
}
