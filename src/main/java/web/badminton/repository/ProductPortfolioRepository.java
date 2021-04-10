package web.badminton.repository;

import java.util.List;

import web.badminton.vo.ProductBrands;
import web.badminton.vo.ProductPortfolio;

public interface ProductPortfolioRepository {
	List<ProductPortfolio> getNamePortfolio();
	int countType();
	List<ProductBrands> findNamePortfolio(String id);
	int insertPortfolio(String name);
	int deletePortfolio(String id);
}
