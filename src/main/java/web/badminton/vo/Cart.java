package web.badminton.vo;

public class Cart {
	private int idUser;
	private int idProduct;
	private int idCart;
	private int numberOfSize;
	private int size;
	private String  nameProduct;
	private float price;
	private int statusProduct;
	private String	nameSize;
	
	
	public String getNameSize() {
		return nameSize;
	}
	public void setNameSize(String nameSize) {
		this.nameSize = nameSize;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStatusProduct() {
		return statusProduct;
	}
	public void setStatusProduct(int statusProduct) {
		this.statusProduct = statusProduct;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdCart() {
		return idCart;
	}
	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}
	public int getNumberOfSize() {
		return numberOfSize;
	}
	public void setNumberOfSize(int numberOfSize) {
		this.numberOfSize = numberOfSize;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
