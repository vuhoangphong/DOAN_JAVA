package web.badminton.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import web.badminton.dto.ProductDTO;
import web.badminton.vo.Product;

public interface ProductRepository {
	int inserProduct(ProductDTO addProductDTO,int idShop,MultipartFile avatarProduct);
	List<Product> getProductOfShop(int idShop,int status); 	
	Blob getAvatarProduct(int idProduct);
	int checkNameExist(String nameProduct,int idBrand,int idShop);
	List<Product> searchProduct(int idShop,int idBrand,int idType, String keySearch,int statusProduct);
	List<Product> AdminSearchProduct(int idBrand,int idType, String keySearch,int statusProduct);
	List<Product> getAllProduct(int status); 	
	List<Product> getListProduct(int status, int idType); 
	List<Product> getProductLowToHight(int status, int idType);
	List<Product> getProductHightToLow(int status, int idType);
	List<Product> getAllProductLowToHight(int status); 	
	List<Product> getAllProductHightToLow(int status); 	
	List<Product> findProduct(int idBrand);
	Product getProduct(int idProduct , int status);
	int countProductType(int idType,int status);
	int getType(int idProduct , int Status);
	String getNameProduct(int idProduct , int status);
	float getPriceProduct(int idProduct , int status);
	int getNumberProduct(int idProduct , int status);
	int updateProduct(int idProduct , int number );
	int changeStatusProduct(int idProduct ,int status);
}
