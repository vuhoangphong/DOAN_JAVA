package web.badminton.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Blob;

import web.badminton.constant.Constant;
import web.badminton.enums.ProductEnum;
import web.badminton.enums.SizeEnum;
import web.badminton.enums.StatusProductEnum;
import web.badminton.enums.UserEnum;
import web.badminton.modal.Shoes;
import web.badminton.service.CartSevice;
import web.badminton.service.ImageProductService;
import web.badminton.service.ProductDetalService;
import web.badminton.service.ProductService;
import web.badminton.service.UserService;
import web.badminton.vo.ProductReview;
import web.badminton.vo.User;

@Controller
public class ProductDetailController {
	@Autowired
	ProductService productService;
	@Autowired
	ImageProductService imageProductService;
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	CartSevice cartSevice;
	@Autowired
	ProductDetalService productDetalService ;
	
	@RequestMapping("/productdetail")
	public ModelAndView productDetail(int idProduct )
	{
		ModelAndView view = new ModelAndView("productdetail");
		ModelAndView viewSuspend = new ModelAndView("suspend-business");
		User user = (User)session.getAttribute(Constant.USER);
		//check session user
		if(user !=null){
			view.addObject("loginSuccess",user.getFullName());
			view.addObject("user",user.getIdUser());	
			viewSuspend.addObject("user",user.getIdUser());
			// check product licensed review
			if(productDetalService.productLicensedReview(idProduct, user.getIdUser())==1){
			view.addObject("statusComment", productDetalService.productLicensedReview(idProduct, user.getIdUser()));
		}	
		}else {
			view.addObject("user",UserEnum.NULL.get());	

		}
		//add review
		List<ProductReview> listReview = productDetalService.getReview(idProduct);
		if(listReview.size()>0){
			view.addObject("listReview",listReview);
			view.addObject("percentage", productDetalService.percentage(idProduct)) ;
		}
		try {
			view.addObject("product", productService.getProduct(idProduct));
		} catch (Exception e) {
			
			return viewSuspend;
		}
		//average ranking
		List<Float> average =  productDetalService.averageRanking(idProduct);
		if(average.get(0)>0)
			view.addObject("averageRanking",average.get(0));
		else
			view.addObject("averageRanking",0);
		view.addObject("numberOfReviews",average.get(1).intValue());

		view.addObject("listImage",imageProductService.getListIdImage(idProduct));
		//add list size 
		int idType =productService.getType(idProduct);
		if(idType == ProductEnum.AO.get() || idType == ProductEnum.QUAN.get()) {
			view.addObject("listSize",productService.listNumberClothes(idProduct));
			view.addObject("type",productService.getType(idProduct));
			view.addObject("sizeSelect",SizeEnum.NULLSIZE.get());
			return view;
		}
		if(idType == ProductEnum.GIAY.get()) {
			view.addObject("listSize",productService.listNumberShoes(idProduct));
			view.addObject("type",productService.getType(idProduct));
			view.addObject("sizeSelect",SizeEnum.NULLSIZE.get());
			return view;
		}		
		List<Shoes> list = new ArrayList<Shoes>();
		view.addObject("listSize",list);
		//add size select
		view.addObject("sizeSelect",SizeEnum.NONEEDSIZE.get());
	 	return view;
	}
	
	
	@RequestMapping("/productdetail-size")
	public ModelAndView productDetailSize(int idProduct ,int size )
	{
		ModelAndView view = new ModelAndView("productdetail");
		User user = (User)session.getAttribute(Constant.USER);
		//check session user
		
		if(user !=null){
			view.addObject("loginSuccess",user.getFullName());
			view.addObject("user",user.getIdUser());		
		}else {
			view.addObject("user",UserEnum.NULL.get());	
		}	
			
		view.addObject("product", productService.getProduct(idProduct));
		view.addObject("listImage",imageProductService.getListIdImage(idProduct));
		
		//add list size 
		int idType =productService.getType(idProduct);
		if(idType == ProductEnum.AO.get() || idType == ProductEnum.QUAN.get()) {
			view.addObject("listSize",productService.listNumberClothes(idProduct));
			view.addObject("type",productService.getType(idProduct));
			view.addObject("numberSize",productService.getNumberClothes(idProduct, size));
		}
		if(idType == ProductEnum.GIAY.get()) {
			view.addObject("listSize",productService.listNumberShoes(idProduct));
			view.addObject("type",productService.getType(idProduct));
			view.addObject("numberSize",productService.getNumberShoes(idProduct, size));
		}		
		
		//add size select
		view.addObject("sizeSelect",size);
		return view;
	}
	
	
	
	
	
	@RequestMapping(value ="/getPhotoDescriptionProduct", method = RequestMethod.GET)
	public void getImage(HttpServletResponse response ,@RequestParam int idProduct,@RequestParam int idImage) throws SQLException, IOException {
		response.setContentType("image/jpeg");
		
		Blob file = imageProductService.getPhotoDescriptionProduct(idProduct,idImage);
			byte[] bytes = file.getBytes(1, (int)file.length());
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	
		@RequestMapping(value = "/loginproductdetail", method = RequestMethod.POST)
		public ModelAndView login(@RequestParam String accountLogin, @RequestParam String passwordLogin,@RequestParam int idProduct ,@RequestParam(required = false) String size) {
			ModelAndView view = new ModelAndView();
			if(Integer.parseInt(size)==-1) {
				view = new ModelAndView("redirect:productdetail?idProduct="+idProduct +"");
			}else {
				 view = new ModelAndView("redirect:productdetail-size?idProduct="+idProduct+"&size="+size+"");
			}
			
			// if( userService.checkExist(accountLogin, passwordLogin) == 1) {
			// 	// User user =	userService.getUser(accountLogin, passwordLogin);
			// 	User user =	userService.getUser(accountLogin);
			// 	session.setAttribute(Constant.USER, user);
			
			// }else {
			// 	view.addObject("loginSuccess", false);
			// }			
			return view;
		}	
		
		
		
		@RequestMapping(value = "/logoutproductdetail", method = RequestMethod.POST)
		public ModelAndView outLogin(HttpSession session,@RequestParam int idProduct ,@RequestParam(required = false) String size) {
			session.invalidate();
			ModelAndView view = new ModelAndView();
			if(Integer.parseInt(size)==0) {
				view = new ModelAndView("redirect:productdetail?idProduct="+idProduct +"");
			}else {
				 view = new ModelAndView("redirect:productdetail-size?idProduct="+idProduct+"&size="+size+"");
			}
			return view;
		}
		
		@RequestMapping(value = "/buy", method = RequestMethod.POST)
		public ModelAndView buy(@RequestParam int idProduct,@RequestParam int numberSize,@RequestParam int size) {		
			ModelAndView view = new ModelAndView("redirect:cart");
			User user = (User)session.getAttribute(Constant.USER);
			cartSevice.insertCart(user.getIdUser(), idProduct, numberSize, size);
			return view;
		}
		
		@RequestMapping(value = "/addCart", method = RequestMethod.POST)
		@ResponseBody
		public String addCrat(@RequestParam int idProduct,@RequestParam int numberSize,@RequestParam int size) {		
			User user = (User)session.getAttribute(Constant.USER);
			try {
				cartSevice.insertCart(user.getIdUser(), idProduct, numberSize, size);
				return "success";
			} catch (Exception e) {
				return "fail";
			}
		
		}


		@RequestMapping(value = "/addComment", method = RequestMethod.POST)
		public String addComment(@RequestParam int rank , @RequestParam String review ,@RequestParam int idProduct ) {
			User user = (User)session.getAttribute(Constant.USER);
			int result = productDetalService.addComment(rank, user.getIdUser(), idProduct, review);
			return "redirect:productdetail?idProduct="+idProduct;
		}

		
	
}
