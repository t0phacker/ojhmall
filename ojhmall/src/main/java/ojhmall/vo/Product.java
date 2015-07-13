package ojhmall.vo;

public class Product {
	private int prdNumber; // 상품 번호
	private String prdName; // 상품 이름
	private int userNumber; // 상품 등록자 번호
	private int ctgrNumber; // 카테고리 번호
	private int price; // 가격
	private int stock; // 재고
	private int prdStatus; // 상품 상태 0 = 등록 대기, 1 = 판매 중, 2 = 판매 대기, 3 = 품절
	private String image;
	private String text;
	
	public int getPrdNumber() {
		return prdNumber;
	}
	public void setPrdNumber(int prdNumber) {
		this.prdNumber = prdNumber;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public int getUser_userNumber() {
		return userNumber;
	}
	public void setUser_userNumber(int user_userNumber) {
		this.userNumber = user_userNumber;
	}
	public int getCtgrNumber() {
		return ctgrNumber;
	}
	public void setCtgrNumber(int ctgrNumber) {
		this.ctgrNumber = ctgrNumber;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrdStatus() {
		return prdStatus;
	}
	public void setPrdStatus(int prdStatus) {
		this.prdStatus = prdStatus;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
