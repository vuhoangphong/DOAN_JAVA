package web.badminton.vo;

import java.util.List;

public class ReponsitoryImage {
	private int idImage;
	private int idProduct;
	List<byte[]> image;
	
	

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public List<byte[]> getImage() {
		return image;
	}

	public void setImage(List<byte[]> image) {
		this.image = image;
	}

	
}
