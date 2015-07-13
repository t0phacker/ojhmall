package ojhmall.vo;

public class Category {
	private int ctgrNumber; // 카테고리 번호
	private String ctgrName; // 카테고리 이름
	private int ctgrUpperNumber; // 상위 카테고리 번호
	private int ctgrStatus; // 카테고리 상태 0 = 전시, 1 = 휴시
	
	public int getCtgrNumber() {
		return ctgrNumber;
	}
	public void setCtgrNumber(int ctgrNumber) {
		this.ctgrNumber = ctgrNumber;
	}
	public String getCtgrName() {
		return ctgrName;
	}
	public void setCtgrName(String ctgrName) {
		this.ctgrName = ctgrName;
	}
	public int getCtgrUpperNumber() {
		return ctgrUpperNumber;
	}
	public void setCtgrUpperNumber(int ctgrUpperNumber) {
		this.ctgrUpperNumber = ctgrUpperNumber;
	}
	public int getCtgrStatus() {
		return ctgrStatus;
	}
	public void setCtgrStatus(int ctgrStatus) {
		this.ctgrStatus = ctgrStatus;
	}
	
}
