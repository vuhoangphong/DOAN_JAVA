package web.badminton.modal;

public class OrderDetailOJ {
    String nameProduct;
    String numberProduct;
    String price;
    String nameShop;
    String size;
    String status;
    String nameCompanyShipper;
    int idProduct;
    public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNumberProduct(String numberProduct) {
        this.numberProduct = numberProduct;
    }
    public String getNumberProduct() {
        return numberProduct;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }
    public String getNameShop() {
        return nameShop;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getSize() {
        return size;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setNameCompanyShipper(String nameCompanyShipper) {
        this.nameCompanyShipper = nameCompanyShipper;
    }
    public String getNameCompanyShipper() {
        return nameCompanyShipper;
    }
}
