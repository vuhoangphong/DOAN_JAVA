package web.badminton.enums;

public enum ShopEnum {
	IMAGEFAIL(-1),
	INFORMATIONFAIL(0),
	SUCCESS(1);
	
	private int shop;
	
	ShopEnum(int shop){
		this.shop = shop;
	}
	
	public int get() {
		return this.shop;
	}
}
