package web.badminton.modal;
import java.sql.Date;
import java.time.LocalDateTime;
public class OrderDetail {
    private int idOrder;
    private int idProduct;
    private int numberProductPurchased;
    private float totalMoneyProduct;
    private int idTypeSize;
    private int statusDetails;
    private  LocalDateTime dateBuy;
    private String nameProduct;

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public String getNameProduct() {
        return nameProduct;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getIdProduct() {
        return idProduct;
    }

    public void setNumberProductPurchased(int numberProductPurchased) {
        this.numberProductPurchased = numberProductPurchased;
    }
    public int getNumberProductPurchased() {
        return numberProductPurchased;
    }

    
    public void setTotalMoneyProduct(float totalMoneyProduct) {
        this.totalMoneyProduct = totalMoneyProduct;
    }
    public float getTotalMoneyProduct() {
        return totalMoneyProduct;
    }

    public void setIdTypeSize(int idTypeSize) {
        this.idTypeSize = idTypeSize;
    }
    public int getIdTypeSize() {
        return idTypeSize;
    }

    public void setStatusDetails(int statusDetails) {
        this.statusDetails = statusDetails;
    }
    public int getStatusDetails() {
        return statusDetails;
    }

    public LocalDateTime getDateBuy() {
		return dateBuy;
	}
	public void setDateBuy(LocalDateTime dateBuy) {
		this.dateBuy = dateBuy;
	}
}
