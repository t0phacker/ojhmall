package ojhmall.vo;

public class Cart {
	private int cartNumber; // 장바구니 번호
	private int prdNumber; // 상품 번호
	private int userNumber; //회원 번호
	private int prdAmount; // 상품 수량
	private int cartPrice; // 장바구니 가격
	private int newUser; // 생성자
	private int newDate; // 생성일
	private int updateDate; // 수정자
	private int updateUser; // 수정일
	private String prdName; // 상품 이름
	private int deliveryFee; // 배송비
	private String image; // 상품 이미지
	private int user_userNumber; // 판매자 회원 번호
	private String id; // 판매자 아이디
	private int price; //상품 가격
	private int stock; // 상품 재고
	private final int freeDlvrPrc = 30000;
	private final int baseDlvrFee = 3000;
	
	public int getBaseDlvrFee() {
		return baseDlvrFee;
	}
	public int getFreeDlvrPrc() {
		return freeDlvrPrc;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUser_userNumber() {
		return user_userNumber;
	}
	public void setUser_userNumber(int user_userNumber) {
		this.user_userNumber = user_userNumber;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public int getPrdAmount() {
		return prdAmount;
	}
	public void setPrdAmount(int prdAmount) {
		this.prdAmount = prdAmount;
	}
	public int getCartNumber() {
		return cartNumber;
	}
	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}
	public int getPrdNumber() {
		return prdNumber;
	}
	public void setPrdNumber(int prdNumber) {
		this.prdNumber = prdNumber;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getCartPrice() {
		return cartPrice;
	}
	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}
	public int getNewUser() {
		return newUser;
	}
	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}
	public int getNewDate() {
		return newDate;
	}
	public void setNewDate(int newDate) {
		this.newDate = newDate;
	}
	public int getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(int updateDate) {
		this.updateDate = updateDate;
	}
	public int getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}
	
}
