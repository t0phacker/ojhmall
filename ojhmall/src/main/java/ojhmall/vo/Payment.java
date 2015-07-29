package ojhmall.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
	private int payNumber; // 결제번호
	private int orderNumber; // 주문번호
	private int userNumber; // 주문회원번호
	private int money; // 결제 금액
	private String accNum; // 입금 계좌번호
	Date newDate; // 결제일
	private int payStatus; // 결제 상태 1 = 결제확인 전, 2 = 결제 확인
	
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public int getPayNumber() {
		return payNumber;
	}
	public void setPayNumber(int payNumber) {
		this.payNumber = payNumber;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getNewDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(newDate);
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	
}
