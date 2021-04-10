package web.badminton.enums;

public enum SizeEnum {
	NONEEDSIZE(-1),
	NULLSIZE(0);
	
	private  int size;
	
	SizeEnum(int size){
		this.size = size;
	}
	
	public int get() {
		return this.size;
	}
}
