package web.badminton.modal;

import java.sql.Date;

public class Order {
	private int idOrder;
	private int idUser;
	private String idCompanyShipper;
	private Date dateBuy;
	private String deliveryDate;
	private int totalBought;
	private int totalMoney;
	private String statusOrder;
	private String shippingStatus;
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getIdCompanyShipper() {
		return idCompanyShipper;
	}
	public void setIdCompanyShipper(String idCompanyShipper) {
		this.idCompanyShipper = idCompanyShipper;
	}
	public Date getDateBuy() {
		return dateBuy;
	}
	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String date) {
		this.deliveryDate = date;
	}
	public int getTotalBought() {
		return totalBought;
	}
	public void setTotalBought(int totalBought) {
		this.totalBought = totalBought;
	}
	
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}
	
	
	
}
