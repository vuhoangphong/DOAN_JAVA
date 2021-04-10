package web.badminton.vo;

public class Shop {
	private int idShop;
	private String nameShop;
	private int idUser;
	private String addressShop;
	private String numberPhoneShop;
	private int statusShop;
	private byte[] avatarShop;
	
	
	public byte[] getAvatarShop() {
		return avatarShop;
	}
	public void setAvatarShop(byte[] avatarShop) {
		this.avatarShop = avatarShop;
	}
	public int getIdShop() {
		return idShop;
	}
	public void setIdShop(int idShop) {
		this.idShop = idShop;
	}
	public String getNameShop() {
		return nameShop;
	}
	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getAddressShop() {
		return addressShop;
	}
	public void setAddressShop(String addressShop) {
		this.addressShop = addressShop;
	}
	public String getNumberPhoneShop() {
		return numberPhoneShop;
	}
	public void setNumberPhoneShop(String numberPhoneShop) {
		this.numberPhoneShop = numberPhoneShop;
	}
	public int getStatusShop() {
		return statusShop;
	}
	public void setStatusShop(int statusShop) {
		this.statusShop = statusShop;
	} 
}
