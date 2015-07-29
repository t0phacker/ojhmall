package ojhmall.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private int orderNumber; // 주문 번호
	private int userNumber; // 구매자 회원 번호
	private int orderStatus; // 주문 상태, 0 = 주문대기, 1 = 주문 완료
	private Date orderNewDate; // 주문 날짜
	private int newUser; // 주문자
	private int count; // 주문 수량
	private int stock; // 상품 재고
	private int price; // 주문 가격
	private int user_userNumber; // 판매자 회원 번호
	private int prdNumber; // 주문 상품 번호
	private String image; // 상품 이미지
	private String prdName; // 상품 이름
	private String id; // 판매자 id
	private int deliveryFee; // 배송비
	private int totalDelivery; // 총 배송비
	private int totalPrice; // 총 가격
	private int accNumber; // 계좌정보
	
	 public int getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalDelivery() {
		return totalDelivery;
	}
	public void setTotalDelivery(int totalDelivery) {
		this.totalDelivery = totalDelivery;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	
	public int getPrdNumber() {
		return prdNumber;
	}
	public void setPrdNumber(int prdNumber) {
		this.prdNumber = prdNumber;
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
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderNewDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(orderNewDate);
	}
	public void setOrderNewDate(Date orderNewDate) {
		this.orderNewDate = orderNewDate;
	}
	public int getNewUser() {
		return newUser;
	}
	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getUser_userNumber() {
		return user_userNumber;
	}
	public void setUser_userNumber(int user_userNumber) {
		this.user_userNumber = user_userNumber;
	}
	
}
