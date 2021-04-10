package web.badminton.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.repository.OrderDetailRepository;
import web.badminton.repository.ProductReviewRepository;
import web.badminton.vo.Product;
import web.badminton.vo.ProductReview;


@Service
public class ProductDetalService {
    @Autowired
    ProductReviewRepository productReviewRepository;
    @Autowired
	OrderDetailRepository orderDetailRepository;

    public int addComment(int rank, int idUser, int idProduct, String review){
        return productReviewRepository.addComment(rank, idUser, idProduct, review);
    }

    public List<ProductReview> getReview(int idProduct) {
        return productReviewRepository.getReviews(idProduct);
    }

    public int  productLicensedReview(int idProduct , int idUser) {
       List<Product> product = orderDetailRepository.getProductsBought(idUser);
       for (Product p : product) {
           if(p.getIdProduct() == idProduct){
               return 1;
           }
       }
       return 0;
    }

    public List<Float> averageRanking(int idProduct) {
        List<Float> average = new ArrayList<>();
       float score = 0;
       int countReview= 0;
       List<ProductReview> productReviews = productReviewRepository.getReviews(idProduct);
        for (ProductReview p : productReviews) {
            if(p.getIdProduct() == idProduct){
               score+=p.getRanking();
               countReview++;
            }
        }
        DecimalFormat f = new DecimalFormat("##.0");
        average.add( Float.parseFloat(f.format(score/countReview)) );
        average.add((float)countReview);
        return average;
    }

    public List<Integer> percentage(int idProduct){
      return  productReviewRepository.getAllRank(idProduct);
    }
}
