package ojhmall.vo;

public class ProductFactory extends Product {
	public static Product createPrdForCtgr(int ctgrNumber, ProductStatus prdStatus) {
		Product product = new Product();
		product.setCtgrNumber(ctgrNumber);
		product.setPrdStatusNum(prdStatus.getPrdStatus());
		product.setPrdStatus(product.getPrdStatusNum());
		return product;
	}
}
