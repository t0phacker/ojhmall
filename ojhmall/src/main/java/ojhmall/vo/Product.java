package ojhmall.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
	private int prdNumber; // 상품 번호
	private String prdName; // 상품 이름
	private int userNumber; // 상품 등록자 번호
	private int ctgrNumber; // 카테고리 번호
	private int price; // 가격
	private int stock; // 재고
	private int prdStatusNum; // 상품 상태 0 = 등록 대기, 1 = 판매 중, 2 = 판매 대기, 3 = 품절
	private ProductStatus prdStatus;
	private String image; // 상품 이미지
	private String text; // 상품 설명
	private int hitCount; // 조회수
	private String ctgrName; // 카테고리 이름
	private Date newDate; // 상품 등록일
	private String id; // 판매자 id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(newDate);
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public String getCtgrName() {
		return ctgrName;
	}

	public void setCtgrName(String ctgrName) {
		this.ctgrName = ctgrName;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

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

	public ProductStatus getPrdStatus() {
		return prdStatus;
	}

	public void setPrdStatus(int prdStatusNum) {
		switch (prdStatusNum) {
		case 0:
			prdStatus = ProductStatus.POST_WAITING;
			break;
		case 1:
			prdStatus = ProductStatus.SELLING_WAITING;
			break;
		case 2:
			prdStatus = ProductStatus.SELLING_NOW;
			break;
		case 3:
			prdStatus = ProductStatus.SOLD_OUT;
			break;
		default:
			System.out.println("invalid product status!!!!");
			break;
		}
	}

	public int getPrdStatusNum() {
		return prdStatusNum;
	}

	public void setPrdStatusNum(int prdStatusNum) {
		this.prdStatusNum = prdStatusNum;
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
