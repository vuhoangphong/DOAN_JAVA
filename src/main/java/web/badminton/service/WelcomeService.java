package web.badminton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.repository.ProductPortfolioRepository;
import web.badminton.vo.ProductPortfolio;

@Service
public class WelcomeService {
    @Autowired
    ProductPortfolioRepository productPortfolioRepository;
    public List<ProductPortfolio> getAllNamePortfolios() {
       
       return productPortfolioRepository.getNamePortfolio();
        
    }
}
