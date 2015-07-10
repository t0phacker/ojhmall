package ojhmall.vo;

public class Admin extends User {
	private int adminType; //관리자 타입
	private String accNum; //관리자 계좌번호
	
	public int getAdminType() {
		return adminType;
	}
	public void setAdminType(int adminType) {
		this.adminType = adminType;
	}
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	
}
