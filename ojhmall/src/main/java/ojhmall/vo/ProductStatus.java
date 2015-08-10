package ojhmall.vo;

public enum ProductStatus {
	POST_WAITING(0), SELLING_WAITING(1), SELLING_NOW(2), SOLD_OUT(3);
	private int prdStatNum;
	
	public int getPrdStatus() {
		return prdStatNum;
	}

	ProductStatus(int prdStatNum) {
		this.prdStatNum = prdStatNum;
	}
}
