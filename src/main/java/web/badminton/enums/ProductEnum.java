package web.badminton.enums;

public enum ProductEnum {
	FALSE(0),
	TRUE(1),
	GIAY(2),
	AO(3),
	QUAN(4);
	
	private int product;
	
	ProductEnum(int product){
		this.product = product;
	}
	
	public int get() {
		return this.product;
	}
}
