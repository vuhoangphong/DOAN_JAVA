package web.badminton.enums;

public enum UserEnum {
	NULL(0);
	
	private int user;
	
	UserEnum(int user) {
		this.user = user;
	}
	
	public int get() {
		return this.user;
	}
}
