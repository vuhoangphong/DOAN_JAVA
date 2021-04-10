package web.badminton.service;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import web.badminton.constant.Constant;
import web.badminton.dto.ProductDTO;
import web.badminton.enums.ProductEnum;
import web.badminton.enums.StatusProductEnum;
import web.badminton.modal.Clothes;
import web.badminton.modal.Shoes;
import web.badminton.repository.ClothesSizeRepository;
import web.badminton.repository.NumberOfClothesSizeRepository;
import web.badminton.repository.NumberOfShoesSizeRepository;
import web.badminton.repository.ProductPortfolioRepository;
import web.badminton.repository.ProductRepository;
import web.badminton.repository.ShoesSizeRepository;
import web.badminton.vo.ClothesSize;

import web.badminton.vo.Product;
import web.badminton.vo.ShoesSize;
import web.badminton.vo.User;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ShopService shopService;
	@Autowired
	ClothesSizeRepository clothesSizeRepository;
	@Autowired
	NumberOfClothesSizeRepository numberOfClothesSizeRepository;
	@Autowired
	ShoesSizeRepository shoesSizeRepository;
	@Autowired
	NumberOfShoesSizeRepository numberOfShoesSizeRepository;
	@Autowired
	ProductPortfolioRepository productPortfolioRepository;
	@Autowired
	HttpSession session;
	
	public List<Product> getProductShow(){
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		int idShop = shopService.getIdShop(idUser);
		return productRepository.getProductOfShop(idShop,StatusProductEnum.SHOW.get());
	} 
	
	public List<Product> getProductSoldOut(){
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		int idShop = shopService.getIdShop(idUser);
		return productRepository.getProductOfShop(idShop,StatusProductEnum.SOLDOUT.get()); 	
	} 
	
	public List<Product> getProductHide(){
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		int idShop = shopService.getIdShop(idUser);
		return productRepository.getProductOfShop(idShop,StatusProductEnum.HIDE.get());
	}
	
	public List<Product> getProductBan(){
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		int idShop = shopService.getIdShop(idUser);
		return productRepository.getProductOfShop(idShop,StatusProductEnum.BAN.get());
	}
	
	
	
	public int insertProduct(ProductDTO addProductDTO,MultipartFile avatarProduct )
	{
		User user =(User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		int idShop = shopService.getIdShop(idUser);
		if(productRepository.checkNameExist(addProductDTO.getNameProduct(), addProductDTO.getIdBrand(),idShop)==0) {
			return productRepository.inserProduct(addProductDTO, idShop, avatarProduct);
		}
		return ProductEnum.FALSE.get();
	
	}
	
	public int insertNumberOfClothesSize(List<Integer>numberOfClothesSize , int idProduct) {
		try {
			List<ClothesSize>listIdTypeClothesSize = clothesSizeRepository.getListClothesSize();
			int i = 0;
			for (ClothesSize clothesSize : listIdTypeClothesSize) {	
				numberOfClothesSizeRepository.insertNumberOfClothesSize(clothesSize.getIdTypeClothesSize(), idProduct, numberOfClothesSize.get(i));
				i++;
			}
			return ProductEnum.TRUE.get();
		} catch (Exception e) {
			return ProductEnum.FALSE.get();
		}		
	}
	
	public int  insertNumberOfShoesSize(List<Integer> numberOfShoesSize , int idProduct) {
		try {
			List<ShoesSize> listShoesSize = shoesSizeRepository.getListShoesSize();
			int i= 0;
			for (ShoesSize shoesSize : listShoesSize) {
				numberOfShoesSizeRepository.insertNumberOfShoesSize(idProduct, shoesSize.getIdTypeShoesSize(), numberOfShoesSize.get(i));
				i++;
			}
			return ProductEnum.TRUE.get();
		} catch (Exception e) {
			return ProductEnum.FALSE.get();
		}
	}
	
	public List<Product> searchProduct(String  brand , String portfolio, String keySearch , int status){
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		int idShop = shopService.getIdShop(idUser);
		int idBrand;
		int idType;
		try {
			idBrand = Integer.parseInt(brand);		
		} catch (Exception e) {
			idBrand = -1;
		}
		
		try {
			idType = Integer.parseInt(portfolio);
		} catch (Exception e) {
			idType = -1;
		}		
		return productRepository.searchProduct(idShop, idBrand, idType, keySearch,status);
	}
	
	public Blob getAvatarProduct(int idProduct) {
		return productRepository.getAvatarProduct(idProduct);		
	}
	
	public List<Product> getAllProduct()
	{
		return productRepository.getAllProduct(StatusProductEnum.SHOW.get());
	}
	
	public List<Product> getListProduct(int idType) {
		return productRepository.getListProduct(StatusProductEnum.SHOW.get(), idType);
	}
	
	public List<Product> getProductLowTohight(int idType) {
		return productRepository.getProductLowToHight(StatusProductEnum.SHOW.get(), idType);
	}
	
	public List<Product> getProductHightToLow(int idType) {
		return productRepository.getProductHightToLow(StatusProductEnum.SHOW.get(), idType);
	}
	
	public List<Product> getAllProductLowTohight()
	{
		return productRepository.getAllProductLowToHight(StatusProductEnum.SHOW.get());
	}
	
	public List<Product> getAllProductHightToLow()
	{
		return productRepository.getAllProductHightToLow(StatusProductEnum.SHOW.get());
	}
	
	public Product getProduct(int idProduct )
	{
		return productRepository.getProduct(idProduct,StatusProductEnum.SHOW.get());
	}
	
	public List<Integer> countProductType()
	{
		List<Integer> countProductType = new ArrayList<Integer>();
		for(int i = 0 ;i<productPortfolioRepository.countType()+1;i++) {
			countProductType.add(productRepository.countProductType(i, StatusProductEnum.SHOW.get()));
		}
		return countProductType;	
	}
	
	public int  getType(int idProduct) {
		return productRepository.getType(idProduct, StatusProductEnum.SHOW.get());
	}
	public List<Clothes> listNumberClothes(int idProduct) {
		return numberOfClothesSizeRepository.listNumberClothes(idProduct);
	}
	
	public List<Shoes> listNumberShoes(int idProduct) {
		return numberOfShoesSizeRepository.ListNumberShoes(idProduct);
	}
	
	public int  getNumberShoes(int idProduct, int idTypeShoesSize) {
		return numberOfShoesSizeRepository.getNumberShoes(idProduct, idTypeShoesSize);
	}
	
	public int  getNumberClothes(int idProduct, int idTypeClothesSize) {
		return numberOfClothesSizeRepository.getNumberClothes(idProduct, idTypeClothesSize);
	}
	
	
}
