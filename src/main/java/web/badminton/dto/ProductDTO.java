package web.badminton.dto;

public class ProductDTO {
	private String nameProduct;
	private String productDescription;
	private String origin;
	private int idBrand;
	private float priceProduct;
	private int priceDiscount;
	private int numberProduct;
	private int idPortfolio;
	
	public int getIdPortfolio() {
		return idPortfolio;
	}
	public void setIdPortfolio(int idPortfolio) {
		this.idPortfolio = idPortfolio;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getIdBrand() {
		return idBrand;
	}
	public void setIdBrand(int idBrand) {
		this.idBrand = idBrand;
	}
	public float getPriceProduct() {
		return priceProduct;
	}
	public void setPriceProduct(float priceProduct) {
		this.priceProduct = priceProduct;
	}
	public int getPriceDiscount() {
		return priceDiscount;
	}
	public void setPriceDiscount(int priceDiscount) {
		this.priceDiscount = priceDiscount;
	}
	public int getNumberProduct() {
		return numberProduct;
	}
	public void setNumberProduct(int numberProduct) {
		this.numberProduct = numberProduct;
	}
	
}
