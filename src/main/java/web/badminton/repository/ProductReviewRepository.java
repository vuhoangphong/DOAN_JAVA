package web.badminton.repository;

import java.util.List;

import web.badminton.vo.ProductReview;

public interface ProductReviewRepository {
     int addComment(int rank, int idUser,int idProduct , String review);
     List<ProductReview> getReviews(int idProduct);
     List<Integer> getAllRank(int idProduct);
     List<ProductReview> findRank(int rank);
}
