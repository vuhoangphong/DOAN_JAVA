package web.badminton.vo;

public class Shipper {
	private int idCompanyShipper;
	private String nameCompanyShipper;
	private float freight;
	private int statusShipper;
	public int getIdCompanyShipper() {
		return idCompanyShipper;
	}
	public int getStatusShipper() {
		return statusShipper;
	}
	public void setStatusShipper(int statusShipper) {
		this.statusShipper = statusShipper;
	}
	public void setIdCompanyShipper(int idCompanyShipper) {
		this.idCompanyShipper = idCompanyShipper;
	}
	public String getNameCompanyShipper() {
		return nameCompanyShipper;
	}
	public void setNameCompanyShipper(String nameCompanyShipper) {
		this.nameCompanyShipper = nameCompanyShipper;
	}
	public float getFreight() {
		return freight;
	}
	public void setFreight(float freight) {
		this.freight = freight;
	}
}
