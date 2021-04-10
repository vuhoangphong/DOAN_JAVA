package web.badminton.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;

//import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Contained;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Blob;

import web.badminton.constant.Constant;
import web.badminton.enums.OrderDetailsEnum;
import web.badminton.enums.ShopEnum;
import web.badminton.enums.StatusProductEnum;
import web.badminton.modal.OrderDetail;
import web.badminton.service.PortfolioService;
import web.badminton.service.ProductService;
import web.badminton.service.SalesChannelService;
import web.badminton.service.ShopService;
import web.badminton.vo.Product;
import web.badminton.vo.User;
@Controller

public class SalesChannelController {
	@Autowired
	ShopService	shopService;
	@Autowired
	HttpSession session;
	@Autowired
	ProductService productservice;
	@Autowired
	PortfolioService portfolioService;
	@Autowired
	SalesChannelService salesChannelService;
	
	@RequestMapping("/saleschannel")
	public ModelAndView salesChannel(@RequestParam(required = false) String success) {
		ModelAndView view = new ModelAndView("shop/saleschannel");	
		if(shopService.checkExistShop()==1) {
			view.addObject("shop",shopService.getShop());
			List<Product> product = productservice.getProductShow();
			view.addObject("registerShopSuccess",success);
			view.addObject("listImageProduct",product);
			return view; 
		}
		return new ModelAndView("shop/registershop");			
	}
	
	@RequestMapping("/saleschannel-allproduct-soldout")
	public ModelAndView salesChannelSoldOut()
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-allproduct-soldout");
		List<Product> product = productservice.getProductSoldOut();
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		view.addObject("listImageProduct",product);
		view.addObject("shop",shopService.getShop());
		return view;
	}
	
	@RequestMapping("/saleschannel-allproduct-show")
	public ModelAndView salesChannelShow()
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-allproduct-show");
		List<Product> product = productservice.getProductShow();
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		view.addObject("listImageProduct",product);
		view.addObject("shop",shopService.getShop());
		return view;
	}

	@RequestMapping(value = "/hideProduct" , method = RequestMethod.POST)
	@ResponseBody
	public String hideProduct(@RequestParam int idProduct){
		if(salesChannelService.changeStatusProduct(idProduct,StatusProductEnum.HIDE.get())==1)
			return "success";
		return "fail";
	}

	@RequestMapping(value = "/showProduct" , method = RequestMethod.POST)
	@ResponseBody
	public String showProduct(@RequestParam int idProduct){
		if(salesChannelService.changeStatusProduct(idProduct,StatusProductEnum.SHOW.get())==1)
			return "success";
		return "fail";
	}



	
	@RequestMapping("/saleschannel-allproduct-hide")
	public ModelAndView salesChannelHide()
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-allproduct-hide");
		List<Product> product = productservice.getProductHide();
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		view.addObject("listImageProduct",product);
		view.addObject("shop",shopService.getShop());
		return view;
	}
	
	@RequestMapping("/saleschannel-allproduct-hide-search")
	public ModelAndView salesChannelHideSearch(@RequestParam(required = false)  String  portfolio,@RequestParam(required = false) String brand, @RequestParam String idSearch  )
	{		
		ModelAndView view = new ModelAndView("shop/saleschannel-allproduct-hide");
		List<Product> product = productservice.searchProduct(brand, portfolio, idSearch,StatusProductEnum.HIDE.get());
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		view.addObject("listImageProduct",product);
		view.addObject("shop",shopService.getShop());
		return view;
	}
	
	@RequestMapping("/saleschannel-allproduct-show-search")
	public ModelAndView salesChannelShowSearch(@RequestParam(required = false)  String  portfolio,@RequestParam(required = false) String brand, @RequestParam String idSearch  )
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-allproduct-show");
		List<Product> product = productservice.searchProduct(brand, portfolio, idSearch,StatusProductEnum.SHOW.get());
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		view.addObject("listImageProduct",product);
		view.addObject("shop",shopService.getShop());
		return view;
	}
	
	@RequestMapping("/saleschannel-allproduct-soldout-search")
	public ModelAndView salesChannelSoldOutSearch(@RequestParam(required = false)  String  portfolio,@RequestParam(required = false) String brand, @RequestParam String idSearch)
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-allproduct-soldout");
		List<Product> product = productservice.searchProduct(brand, portfolio, idSearch,StatusProductEnum.SOLDOUT.get());
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		view.addObject("listImageProduct",product);
		view.addObject("shop",shopService.getShop());
		return view;
	}
	
	@RequestMapping("/saleschannel-settingshop")
	public ModelAndView salesChannelSettingShop()
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-settingshop");
		view.addObject("shop",shopService.getShop());
		return view;
	}
	
	@RequestMapping( value="updateShop" ,method=RequestMethod.POST)
	public ModelAndView salesChannelSettingShopUpdate(@RequestParam int idShop, @RequestParam String nameShop , @RequestParam String addressShop, @RequestParam String numberPhoneShop, @RequestParam
			MultipartFile imageUpload )
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-settingshop");
		int statusUpdate = shopService.updateShop(idShop, nameShop, addressShop, numberPhoneShop, imageUpload );
		if(statusUpdate == ShopEnum.INFORMATIONFAIL.get()) {
			view.addObject("updateShop","informationFail");
			view.addObject("shop",shopService.getShop());
			return view;
		}
		if(statusUpdate == ShopEnum.IMAGEFAIL.get() ) {
			view.addObject("updateShop","imageFail");
			view.addObject("shop",shopService.getShop());
			return view;
		}
		view.addObject("updateShop","success");
		view.addObject("shop",shopService.getShop());
		return view;
	}
	
	@RequestMapping(value ="/getAvatarShop", method = RequestMethod.GET)
	public void getImageAvatarShop(HttpServletResponse response ,@RequestParam int idShop) throws SQLException, IOException {
		response.setContentType("image/jpeg");
		
		Blob file = shopService.getAvatarShop(idShop);
			byte[] bytes = file.getBytes(1, (int)file.length());
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value ="/getImage", method = RequestMethod.GET)
	public void getImage(HttpServletResponse response ,@RequestParam int idProduct) throws SQLException, IOException {
		response.setContentType("image/jpeg");
		
		Blob file = productservice.getAvatarProduct(idProduct);
			byte[] bytes = file.getBytes(1, (int)file.length());
			InputStream inputStream = new ByteArrayInputStream(bytes);
			IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	@RequestMapping("/saleschannel-product-banned")
	public ModelAndView salesChannelProductBanned()
	{
		ModelAndView view = new ModelAndView("shop/saleschannel-product-banned");	
		List<Product> product = productservice.getProductBan();
		view.addObject("listProduct",product);
		view.addObject("shop",shopService.getShop());
		return view;
	}

	// wait confirmation
	@RequestMapping("/waitconfirmation")
	public ModelAndView waitConfirmation()
	{
		ModelAndView view = new ModelAndView("shop/waitconfirmation");
		view.addObject("shop",shopService.getShop());
		view.addObject("listOrder",salesChannelService.getDetails(OrderDetailsEnum.ORDER_SUCCESS.get()));
		return view;	
	}
	// order confirmation or order cancellation
	@RequestMapping(value="confirmation", method=RequestMethod.POST)
	@ResponseBody
	public void confirmation(@RequestParam String idOrder,@RequestParam String status,@RequestParam String idProduct) {
		if(status.equals("0")){
			//  order confirmation
			salesChannelService.updateOrderDetail(OrderDetailsEnum.CLOSE_PACKAGE.get(),Integer.parseInt(idOrder),idProduct);
		}else{
			// cancellation
			salesChannelService.updateOrderDetail(OrderDetailsEnum.ORDER_CANCELLATION.get(),Integer.parseInt(idOrder),idProduct);
		}
	}

	// close package
	@RequestMapping("/closepackage")
	public ModelAndView closePackage()
	{
		ModelAndView view = new ModelAndView("shop/closepackage");
		view.addObject("shop",shopService.getShop());
		view.addObject("listOrder",salesChannelService.getDetails(OrderDetailsEnum.CLOSE_PACKAGE.get()));
		return view;	
	}

	// close package or order cancellation
	@RequestMapping(value="closepackage", method=RequestMethod.POST)
	@ResponseBody
	public void closePackage(@RequestParam String idOrder,@RequestParam String status ,@RequestParam String idProduct) {
		if(status.equals("0")){
			//  being transport
			salesChannelService.updateOrderDetail(OrderDetailsEnum.BEING_TRANSPORT.get(),Integer.parseInt(idOrder),idProduct);
		}else{
			// cancellation
			salesChannelService.updateOrderDetail(OrderDetailsEnum.ORDER_CANCELLATION.get(),Integer.parseInt(idOrder),idProduct);
		}
	}


	// being transport
	@RequestMapping("/beingtransported")
	public ModelAndView beingTransport()
	{
		ModelAndView view = new ModelAndView("shop/beingtransported");
		view.addObject("shop",shopService.getShop());
		view.addObject("listOrder",salesChannelService.getDetails(OrderDetailsEnum.BEING_TRANSPORT.get()));
		return view;	
	}

	// successful delivery
	@RequestMapping("/successfuldelivery")
	public ModelAndView successfulDelivery()
	{
		ModelAndView view = new ModelAndView("shop/successfuldelivery");
		view.addObject("shop",shopService.getShop());
		view.addObject("listOrder",salesChannelService.getDetails(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get()));
		return view;	
	}

	// cancel order
	@RequestMapping("/cancelorder")
	public ModelAndView cancelOrder()
	{
		ModelAndView view = new ModelAndView("shop/cancelorder");
		view.addObject("shop",shopService.getShop());
		view.addObject("listOrder",salesChannelService.getDetails(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get()));
		return view;	
	}

	// 
	@RequestMapping("/statistical")
	public ModelAndView statistical()
	{
		ModelAndView view = new ModelAndView("shop/statistical");
		// get data order cancel
		view.addObject("dataCancel",salesChannelService.orderStatistical(OrderDetailsEnum.ORDER_CANCELLATION.get(), Integer.parseInt(Year.now().toString()), 1)) ;
		// get sum order cancel
		view.addObject("sumCancel",salesChannelService.orderStatistical(OrderDetailsEnum.ORDER_CANCELLATION.get(), Integer.parseInt(Year.now().toString()), 0)) ;
		// get data order success
		view.addObject("dataSuccess",salesChannelService.orderStatistical(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(), Integer.parseInt(Year.now().toString()),1));
		// get sum order success
		view.addObject("sumSuccess",salesChannelService.orderStatistical(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(), Integer.parseInt(Year.now().toString()),0));
		// get data order 
		view.addObject("dataOrder",salesChannelService.orderStatisticalYear( Integer.parseInt(Year.now().toString()),1));
		// get sum order 
		view.addObject("sumOrder",salesChannelService.orderStatisticalYear( Integer.parseInt(Year.now().toString()),0));
		view.addObject("shop",shopService.getShop());
		return view;	
	}

	@RequestMapping("/statisticalyear")
	public ModelAndView statistical(@RequestParam String Year)
	{
		ModelAndView view = new ModelAndView("shop/statistical");
		// get data order cancel
		view.addObject("dataCancel",salesChannelService.orderStatistical(OrderDetailsEnum.ORDER_CANCELLATION.get(), Integer.parseInt(Year), 1)) ;
		// get sum order cancel
		view.addObject("sumCancel",salesChannelService.orderStatistical(OrderDetailsEnum.ORDER_CANCELLATION.get(), Integer.parseInt(Year), 0)) ;
		// get data order success
		view.addObject("dataSuccess",salesChannelService.orderStatistical(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(), Integer.parseInt(Year),1));
		// get sum order success
		view.addObject("sumSuccess",salesChannelService.orderStatistical(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(), Integer.parseInt(Year),0));
		// get data order 
		view.addObject("dataOrder",salesChannelService.orderStatisticalYear( Integer.parseInt(Year),1));
		// get sum order 
		view.addObject("sumOrder",salesChannelService.orderStatisticalYear( Integer.parseInt(Year),0));
		view.addObject("shop",shopService.getShop());
		return view;	
	}

	
}
