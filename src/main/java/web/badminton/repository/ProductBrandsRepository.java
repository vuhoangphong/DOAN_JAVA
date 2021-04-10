package web.badminton.repository;

import java.util.List;

import web.badminton.vo.ProductBrands;

public interface ProductBrandsRepository {
	List<ProductBrands> getBrands();
	List<ProductBrands> getTypeStatus1(int idType);	
	List<ProductBrands> findBrands(String id);
	int insertBrand(String name,String idType,String status);
	int deleteBrand(String id);
}
