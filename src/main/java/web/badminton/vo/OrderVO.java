package web.badminton.vo;

import java.sql.Date;
import java.time.LocalDateTime;

public class OrderVO {
	private int idOrder;
	private int idUser;
	private int idCompanyShipper;
	private Date dateBuy;
	private String deliveryDate;
	private int totalBought;
	private Float totalMoney;
	private int statusOrder;
	private int shippingStatus;
	public int getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(int shippingStatus) {
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
	public int getIdCompanyShipper() {
		return idCompanyShipper;
	}
	public void setIdCompanyShipper(int idCompanyShipper) {
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
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getTotalBought() {
		return totalBought;
	}
	public void setTotalBought(int totalBought) {
		this.totalBought = totalBought;
	}
	public Float getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public int getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(int statusOrder) {
		this.statusOrder = statusOrder;
	}
	
}
