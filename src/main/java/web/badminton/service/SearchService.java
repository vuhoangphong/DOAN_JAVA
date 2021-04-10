package web.badminton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.repository.ProductRepository;
import web.badminton.vo.Product;

@Service
public class SearchService {
    
    @Autowired
    ProductRepository productRepository;
    public List<Product> findProduct(int idBrand) {
        return productRepository.findProduct(idBrand);
    }
}
