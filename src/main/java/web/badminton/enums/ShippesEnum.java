package web.badminton.enums;

public enum ShippesEnum {
	BAN(0),
	NOBAN(1);
	
	private int shipper;
	ShippesEnum(int shipper){
		this.shipper = shipper;
	}
	
	public int get() {
		return this.shipper;
	}
}
