package ojhmall.vo;

public enum UserType {
	ADMIN(0), CUSTOMER(1), SELLER(2);
	private int value;
	
	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	private UserType(int value) {
		this.value = value;
	}
}
