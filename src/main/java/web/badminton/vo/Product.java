package web.badminton.vo;

import java.nio.IntBuffer;
import java.sql.Date;

public class Product {
	private int idProduct;
	private int idShop;
	private String nameProduct;
	private float priceProduct;
	private String origin;
	private int  priceDiscount;
	private int idBrand;
	private int numberProduct;
	private Date dateInput;
	private int statusProduct;
	private String productDescription;
	private String reasonBanned;
	private Date dayBanned;
	private float price;
	private int ranking;
	private String nameShop;

	public String getNameShop() {
		return nameShop;
	}
	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}

	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getIdShop() {
		return idShop;
	}
	public void setIdShop(int idShop) {
		this.idShop = idShop;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public float getPriceProduct() {
		return priceProduct;
	}
	public void setPriceProduct(float priceProduct) {
		this.priceProduct = priceProduct;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int  getPriceDiscount() {
		return priceDiscount;
	}
	public void setPriceDiscount(Integer priceDiscount) {
		this.priceDiscount = priceDiscount;
	}
	public int getIdBrand() {
		return idBrand;
	}
	public void setIdBrand(int idBrand) {
		this.idBrand = idBrand;
	}
	public int getNumberProduct() {
		return numberProduct;
	}
	public void setNumberProduct(int numberProduct) {
		this.numberProduct = numberProduct;
	}
	public Date getDateInput() {
		return dateInput;
	}
	public void setDateInput(Date dateInput) {
		this.dateInput = dateInput;
	}
	public int getStatusProduct() {
		return statusProduct;
	}
	public void setStatusProduct(int statusProduct) {
		this.statusProduct = statusProduct;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getReasonBanned() {
		return reasonBanned;
	}
	public void setReasonBanned(String reasonBanned) {
		this.reasonBanned = reasonBanned;
	}
	public Date getDayBanned() {
		return dayBanned;
	}
	public void setDayBanned(Date dayBanned) {
		this.dayBanned = dayBanned;
	}
	
}
