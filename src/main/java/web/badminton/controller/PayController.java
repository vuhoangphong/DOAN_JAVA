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
import web.badminton.dto.UserDTO;

import web.badminton.modal.ResponseMOMO;
import web.badminton.service.CartSevice;

import web.badminton.vo.Payment;
import web.badminton.vo.User;
import web.badminton.service.PayService;;
@Controller
public class PayController {
	@Autowired
	CartSevice cartSevice;
	@Autowired
	HttpSession session;
	@Autowired
	PayService payService;
	@RequestMapping("/pay")
	public ModelAndView Pay() {
		ModelAndView view = new ModelAndView("pay");
		User user = (User)session.getAttribute(Constant.USER);
		if(user !=null){
			view.addObject("loginSuccess",user.getFullName());
			view.addObject("user",user);
			view.addObject("listProduct",cartSevice.listCart(user.getIdUser()));
			view.addObject("listShippers",payService.getListShippers());
		}	
		return view;
	}
	
	
	@RequestMapping(value = "/payment" , method = RequestMethod.POST)
	@ResponseBody
	public String payment(@ModelAttribute UserDTO userDTO, @RequestParam int payment, @RequestParam int totalMoney,@RequestParam int idCompanyShipper) {
	
		User user = (User)session.getAttribute(Constant.USER);
		payService.checkChangeUserPayment(userDTO, user);
		int idOrder = 0;
		// payment on delivery
		if(payment==1) {
			payService.paymentOnDelivery( user, totalMoney, idCompanyShipper);
			return "profile-ordermanagement";
		}
		// payment with MOMO
		if(payment==2) {
			idOrder = payService.getIdOrderAndInsertOrder(user, totalMoney, idCompanyShipper);
			ResponseMOMO response= payService.paymentWithMOMO( user,  totalMoney ,  idCompanyShipper,idOrder);
			if(response.getErrorCode()==0) {
				Payment p = new Payment();
				p.setTransactionStatus(1);
				p.setIdOrder(idOrder);
				session.setAttribute(Constant.PAYMENT, p);
				return response.getPayUrl();
			}
		}	
		return null;
	}
}
