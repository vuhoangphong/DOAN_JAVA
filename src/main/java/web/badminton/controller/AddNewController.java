package web.badminton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import web.badminton.dto.ProductDTO;
import web.badminton.enums.ProductEnum;
import web.badminton.service.BrandService;
import web.badminton.service.ImageProductService;
import web.badminton.service.PortfolioService;
import web.badminton.service.ProductService;
import web.badminton.vo.ProductBrands;

@Controller
public class AddNewController {

	@Autowired
	PortfolioService portfolioService;
	@Autowired
	ProductService  productservice;
	@Autowired
	ImageProductService imageProductService;
	@Autowired
	BrandService brandService;

	
	@RequestMapping("/addnew")
	public ModelAndView addNew() {
		ModelAndView view = new ModelAndView("shop/addnew");
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		return view;
	}
	
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute ProductDTO ProductDTO ,@RequestParam("imageProduct") List<MultipartFile> image ,@RequestParam MultipartFile avatarProduct,@RequestParam("clotheSize") List<Integer> numberOfClothesSize ,@RequestParam("shoesSize") List<Integer> numberOfShoesSize) {
		ModelAndView view = new ModelAndView("shop/addnew");
		view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
		int idProduct = productservice.insertProduct(ProductDTO, avatarProduct);
		if( idProduct == ProductEnum.FALSE.get()) {
			view.addObject("statusAdd","false");
			return view;
		}
	
		if(imageProductService.addImageProduct(idProduct, image) != ProductEnum.TRUE.get()) {
			view.addObject("statusAdd","false");
			return view;
		}
					
		if(ProductDTO.getIdPortfolio() == ProductEnum.AO.get() || ProductDTO.getIdPortfolio()  == ProductEnum.QUAN.get()) {
			if(productservice.insertNumberOfClothesSize(numberOfClothesSize, idProduct) == ProductEnum.TRUE.get()) {
				view.addObject("statusAdd","true");	
				return view;
			}else {
				view.addObject("statusAdd","false");
				return view;
			}
		}
		if(ProductDTO.getIdPortfolio() == ProductEnum.GIAY.get()) {
			if (productservice.insertNumberOfShoesSize(numberOfShoesSize, idProduct)== ProductEnum.TRUE.get()){
				view.addObject("statusAdd","true");
				return view;
			}else {
				view.addObject("statusAdd","false");
				return view;
			}		
		}
		view.addObject("statusAdd","true");	
		return view;
	}
		
	
	
	@RequestMapping(value ="/selectPortfolio", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductBrands> selectPortfolio(@RequestParam int idType)
	{		
		return brandService.getTypeStatus1(idType); 
	}
}
