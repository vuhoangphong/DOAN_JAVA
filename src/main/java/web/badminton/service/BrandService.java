package web.badminton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.repository.ProductBrandsRepository;
import web.badminton.vo.ProductBrands;

@Service
public class BrandService {
	@Autowired
	ProductBrandsRepository productBrandsRepository;
	
	public List<ProductBrands> getTypeStatus1(int idType){
		return productBrandsRepository.getTypeStatus1(idType);
	}
	
	
}
