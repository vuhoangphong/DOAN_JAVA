package web.badminton.controller;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Blob;

import web.badminton.constant.Constant;
import web.badminton.dto.RegisterUserDTO;
import web.badminton.modal.OrderDetailOJ;
import web.badminton.modal.ResponseCheckTransactionStatusMOMO;

import web.badminton.service.PayService;
import web.badminton.service.ProfileService;
import web.badminton.service.UserService;
import web.badminton.vo.Payment;
import web.badminton.vo.User;

@Controller
public class ProfileController {
@Autowired
HttpSession session;
@Autowired 
PayService payService;
@Autowired
UserService userService;
@Autowired
ProfileService profileService;

	@RequestMapping("/profile")
	public ModelAndView proFile() {
		ModelAndView view = new ModelAndView("user/profile");
		User user = (User)session.getAttribute(Constant.USER);
		if(user != null){
			view.addObject("userInfo",user);
			view.addObject("user",user.getIdUser());
		}
		return view ;
	}
	
	@RequestMapping(value = "uploadUser" , method = RequestMethod.POST)
	public ModelAndView proFileUploadUser(@ModelAttribute RegisterUserDTO register, String address, int idUser,@RequestParam MultipartFile imageUpload) {
		ModelAndView view = new ModelAndView("user/profile");
		userService.uploadUser(register, address, idUser,imageUpload);
		User user = (User)session.getAttribute(Constant.USER);
		// User infoUser = userService.getUser(user.getAccount(), user.getPassWord());
		//User infoUser = userService.getUser(user.getAccount());
		view.addObject("userInfo",user);
		view.addObject("user",user.getIdUser());
		return view ;
	}
	
	
	@RequestMapping("/profile-ordermanagement")
	public ModelAndView proFileOrderManagement() {
		ModelAndView view = new ModelAndView("user/profile-ordermanagement");
		User user = (User)session.getAttribute(Constant.USER);
		view.addObject("userInfo",user);
		view.addObject("user",user.getIdUser());
		Payment p =  (Payment) session.getAttribute(Constant.PAYMENT);
		if(p != null) {
			if(p.getTransactionStatus() == 1  ) {
				ResponseCheckTransactionStatusMOMO response = payService.checkTransactionStatusMOMO(p.getIdOrder());
					if(response.getErrorCode() == 0) {
						String a =String.valueOf(profileService.checkTransaction(response.getOrderId(), response.getAmount(), response.getTransId())) ;
						if(a.isEmpty())
						{
							view.addObject("transactionStatus",-1);
						}
						view.addObject("transactionStatus",a);
						view.addObject("listOrder",profileService.getListOrders());
					}
					view.addObject("listOrder",profileService.getListOrders());
				return view ;
			}
		}
		view.addObject("listOrder",profileService.getListOrders());
		return view ;
	}
	
	@RequestMapping("/profile-favoriteproduct")
	public ModelAndView proFileFavoriteProduct() {
		ModelAndView view = new ModelAndView("user/profile-favoriteproduct");
		User user = (User)session.getAttribute(Constant.USER);
		view.addObject("userInfo",user);
		view.addObject("user",user.getIdUser());
		return view ;
	}
	
	@RequestMapping("/profile-reviewproduct")
	public ModelAndView proFileReviewProduct() {
		ModelAndView view = new ModelAndView("user/profile-reviewproduct");
		User user = (User)session.getAttribute(Constant.USER);
		view.addObject("userInfo",user);
		view.addObject("user",user.getIdUser());
		view.addObject("listProduct", profileService.getProductsBought(user.getIdUser()));
		return view ;
	}
	
	@RequestMapping("/profile-viewedproduct")
	public ModelAndView proFileViewedProduct() {
		ModelAndView view = new ModelAndView("user/profile-viewedproduct");
		User user = (User)session.getAttribute(Constant.USER);
		view.addObject("userInfo",user);
		view.addObject("user",user.getIdUser());
		return view ;
	}
	
	@RequestMapping(value = "/getAvatarUser" , method = RequestMethod.GET)
	public void getImage(HttpServletResponse response ,@RequestParam int idUser ) throws SQLException ,IOException{
		response.setContentType("image/jpeg");
		byte[] bytes;
		Blob file = userService.getAvatarUser(idUser);
		if(file == null){
			File a =  new ClassPathResource("../main/static/img/avatar.png").getFile();
			 InputStream inputStream = new DataInputStream(new FileInputStream(a));
			 IOUtils.copy(inputStream, response.getOutputStream());
		}else{
			bytes = file.getBytes(1, (int)file.length());
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
		}
	
		}

		@RequestMapping("/vieworder")
		public ModelAndView viewOder() {
			return new ModelAndView("user/vieworder");
		}	


	@RequestMapping(value="/order-detail", method=RequestMethod.POST)
	@ResponseBody
	public List<OrderDetailOJ> requestMethodName(@RequestParam String id) {
		return profileService.ListDetail(id);
	}
	
	
}
