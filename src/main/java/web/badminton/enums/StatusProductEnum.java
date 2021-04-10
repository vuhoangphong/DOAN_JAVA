package web.badminton.enums;

public enum StatusProductEnum {
	HIDE(0),
	SHOW(1),
	SOLDOUT(2),
	BAN(3);
	
	private int status;
	
	StatusProductEnum(int status){
		this.status = status;
	}
	
	public int get() {
		return this.status;
	}
}
