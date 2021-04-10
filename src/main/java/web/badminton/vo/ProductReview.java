package web.badminton.vo;

import java.sql.Date;

public class ProductReview {
	private int idproductreview;
	private int ranking;
	private String review;
	private int idUser;
	private int idProduct;
	private String fullName;
	private Date reviewDate;

	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getIdproductreview() {
		return idproductreview;
	}
	public void setIdproductreview(int idproductreview) {
		this.idproductreview = idproductreview;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	
}
