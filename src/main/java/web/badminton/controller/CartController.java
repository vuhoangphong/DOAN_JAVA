package web.badminton.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.badminton.constant.Constant;
import web.badminton.service.CartSevice;
import web.badminton.vo.User;
@Controller

public class CartController {
	@Autowired
	CartSevice cartSevice;
	@Autowired
	HttpSession	session; 

	@RequestMapping("/cart")
	public ModelAndView Bill() {
		ModelAndView view = new ModelAndView("cart");
		User user = (User)session.getAttribute(Constant.USER);
		if(user !=null){
			view.addObject("loginSuccess",user.getFullName());
			view.addObject("user",user.getIdUser());
			view.addObject("listProduct",cartSevice.listCart(user.getIdUser()));
		}		
		return view;
	}
	@RequestMapping(value = "/checkCart", method = RequestMethod.POST)
	@ResponseBody
	public List<web.badminton.modal.CheckCart> CheckCart() {
		return  cartSevice.checkCart(); 
	}
	
	@RequestMapping(value = "/plus", method = RequestMethod.POST)
	@ResponseBody
	public int plus(@RequestParam int idProduct ,@RequestParam int numberOfSize ,@RequestParam int size ) {
		return  cartSevice.upDownCart( idProduct, numberOfSize, size) ;
	}
	
	@RequestMapping(value = "/deleteRowCart", method = RequestMethod.POST)
	@ResponseBody
	public int deleteRowCart(@RequestParam int idProduct ,@RequestParam int size ) {
		return  cartSevice.deleteCart(idProduct, size);
	
	}
}
