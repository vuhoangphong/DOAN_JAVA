package web.badminton.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.badminton.constant.Constant;
import web.badminton.service.ElasticSearchProductService;
import web.badminton.service.ProductService;
import web.badminton.service.SearchService;
import web.badminton.service.WelcomeService;
import web.badminton.vo.Product;
import web.badminton.vo.User;

@Controller
public class SearchController {
    @Autowired
	HttpSession session;
    @Autowired
    ElasticSearchProductService elasticSearchProductService;
    @Autowired
	ProductService productService;
	@Autowired 
    WelcomeService welcomeService;
    @Autowired
    SearchService searchService; 
    
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView requestMethodName(@RequestParam String keyWord ,@RequestParam(value = "min", required=false) String min,@RequestParam(value = "max" ,required = false) String max)throws ParseException {
        ModelAndView view = new ModelAndView("search");
        List<Product> listProduct = elasticSearchProductService.findProduct(keyWord,min,max);
        if(listProduct.size() !=0){
            view.addObject("listProduct",listProduct);
            view.addObject("brands", elasticSearchProductService.getBrand(String.valueOf(listProduct.get(0).getIdBrand())));
        }
    	view.addObject("countProductTpye", productService.countProductType());
        view.addObject("listPortfolios", welcomeService.getAllNamePortfolios());
        view.addObject("keyWord", keyWord);
        try {
			User user = (User)session.getAttribute(Constant.USER);
			view.addObject("user",user.getIdUser());
		} catch (Exception e) {
			//TODO: handle exception
		}	
        return view;
    }

    @RequestMapping(value = "/getproductbrands", method = RequestMethod.GET)
	public ModelAndView getProductBrands(@RequestParam int idBrand,@RequestParam(value = "min", required=false) String min,@RequestParam(value = "max" ,required = false) String max) {		
        ModelAndView view = new ModelAndView("search");
        List<Product> listProduct = elasticSearchProductService.getBrands(String.valueOf(idBrand),min,max);
        if(listProduct.size() !=0){
            view.addObject("listProduct",listProduct);           
        }
		view.addObject("countProductTpye", productService.countProductType());
        view.addObject("listPortfolios", welcomeService.getAllNamePortfolios());
        view.addObject("brands", elasticSearchProductService.getBrand(String.valueOf(idBrand)));
        
		return view;
	}
    
}
