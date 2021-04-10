package web.badminton.vo;

public class OrderDetailVO {
	private int idOrder;
	private int idProduct;
	private int numberProductPurchased;
	private float totalMoneyProduct;
	private int idTypeSize;
	private int statusDetails;
	public int getStatusDetails(){
		return statusDetails;
	}
	public void setStatusDetails(int status){
		this.statusDetails = status;
	}
	public int getIdTypeSize() {
		return idTypeSize;
	}
	public void setIdTypeSize(int idTypeSize) {
		this.idTypeSize = idTypeSize;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getNumberProductPurchased() {
		return numberProductPurchased;
	}
	public void setNumberProductPurchased(int numberProductPurchased) {
		this.numberProductPurchased = numberProductPurchased;
	}
	public float getTotalMoneyProduct() {
		return totalMoneyProduct;
	}
	public void setTotalMoneyProduct(float totalMoneyProduct) {
		this.totalMoneyProduct = totalMoneyProduct;
	}
}
