package ojhmall.vo;

public class User {
	String inputEmail;
	private UserType userType; // 회원구분
	private int userNumber; // 회원번호
	private String id; // 아이디(email)
	private String userPassword; // 비밀번호
	private String userName; // 이름
	private int userTypeNum;

	public int getUserTypeNum() {
		return userTypeNum;
	}

	public void setUserTypeNum(int userTypeNum) {
		this.userTypeNum = userTypeNum;
	}

	public UserType getUserType() {
		switch (userTypeNum) {
		case 0:
			this.userType = UserType.ADMIN;
			break;
		case 1:
			this.userType = UserType.CUSTOMER;
			break;
		case 2:
			this.userType = UserType.SELLER;
			break;
		default:
			this.userType = UserType.ADMIN; // 에러나 널
			break;
		}
		return this.userType;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

}
