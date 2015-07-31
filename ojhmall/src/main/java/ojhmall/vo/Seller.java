package ojhmall.vo;

public class Seller extends User {
	private String phNumber; //전화번호
	private String address; //주소
	private String zipCode; //우편번호
	private String bizNumber; //사업자번호
	private String accNumber; //계좌번호
	
	public String getPhNumber() {
		return phNumber;
	}
	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getBizNumber() {
		return bizNumber;
	}
	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	
}
