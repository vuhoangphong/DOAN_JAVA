package web.badminton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.repository.ProductPortfolioRepository;
import web.badminton.vo.ProductPortfolio;

@Service
public class PortfolioService {
	
	@Autowired
	ProductPortfolioRepository prodcPortfolioRepository;
	
	public List<ProductPortfolio> getNamePortfolio(){
		return prodcPortfolioRepository.getNamePortfolio();
	}
	
	public int countType()
	{
		return prodcPortfolioRepository.countType();
	}
}
