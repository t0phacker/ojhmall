package ojhmall.dao.product;

import java.util.List;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Product;
import ojhmall.vo.User;

import org.springframework.stereotype.Repository;

@Repository("productDAO")
public class ProductDAO extends AbstractDAO {
	// 상품 정보 출력
	public Product showPrd(Product prd) throws Exception {
		return (Product) selectOne("Product.showPrd", prd);
	}
	// 상품 검색
	@SuppressWarnings("unchecked")
	public List<Product> schPrd(Product prd) {
		return (List<Product>) selectList("Product.schPrd", prd);
	}
	// 상품 조회수 증가
	public void updateHitCount(Product prd) {
		update("Product.updateHitCount", prd);
	}
	// 메인페이지에 출력할 상품리스트 출력
	public List<Product> initPrd(int initPrdList) {
		return (List<Product>) selectList("Product.initPrd", initPrdList);
	}
	// 판매자가 등록한 상품리스트 출력
	public List<Product> loadSellerPrd(User user) {
		return selectList("Product.loadSellerPrd", user);
	}
	// 상품 등록
	public void addPrd(Product prd) {
		insert("Product.addPrd", prd);
	}
	
	public Product getPrdInfo(Product prd) {
		return (Product) selectOne("Product.getPrdInfo", prd);
	}
	public void updatePrdInfo(Product prd) {
		update("Product.updatePrdInfo", prd);
	}
	public List<Product> getPrdList(int prdStatus) {
		return selectList("Product.getPrdList", prdStatus);
	}
	public void updatePrdStatus(Product prd) {
		update("Product.updatePrdStatus", prd);
	}
}
