package ojhmall.dao.product;

import java.util.List;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Product;

import org.springframework.stereotype.Repository;

@Repository("productDAO")
public class ProductDAO extends AbstractDAO {
	// 상품 정보 출력
	public Product showPrd(Product prd) throws Exception {
		return (Product) selectOne("Product.showPrd", prd);
	}
	// 상품 검색
	public List<Product> schPrd(Product prd) {
		return (List<Product>) selectList("Product.schPrd", prd);
	}
	// 상품 조회수 증가
	public void updateHitCount(Product prd) {
		update("Product.updateHitCount", prd);
	}
}
