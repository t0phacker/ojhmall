package ojhmall.service.product;

import java.util.List;

import javax.annotation.Resource;

import ojhmall.dao.product.ProductDAO;
import ojhmall.vo.Product;
import ojhmall.vo.User;

import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource(name = "productDAO")
	private ProductDAO productDAO;
	// 상품 정보 출력
	@Override
	public Product showPrdInfo(Product prd) throws Exception {
		Product product = productDAO.showPrd(prd);
		productDAO.updateHitCount(product);
		return product;
	}
	// 상품 검색
	@Override
	public List<Product> showPrdSchRes(Product prd) throws Exception {
		return productDAO.showPrdSchRes(prd);
	}
	// 메인페이지 상품리스트 출력
	@Override
	public List<Product> initPrdList(int initPrdCount) throws Exception {
		return productDAO.initPrd(initPrdCount);
	}
	// 판매자가 등록한 상품리스트 출력
	@Override
	public List<Product> showSellerPrdList(User user) throws Exception {
		return productDAO.loadSellerPrd(user);
	}
	// 상품 등록
	@Override
	public void addPrd(Product prd) throws Exception {
		productDAO.addPrd(prd);
	}
	// 상품정보 변경을 위한 상품 출력 from mypageController
	@Override
	public Product showPrdInfoForUpdate(Product prd) throws Exception {
		return productDAO.getPrdInfo(prd);
	}
	// 상품정보 변경 from mypageController
	@Override	
	public void updatePrdInfo(Product prd) throws Exception {
		productDAO.updatePrdInfo(prd);
	}
	// 관리자가 상품 전시 승인을 위한 상품 목록 from mypageController
	@Override
	public List<Product> getPrdListToAdmin(int prdStatus) throws Exception {
		return productDAO.getPrdList(prdStatus);
	}
	// 상품 전시 승인(상품 상태 1 -> 2)
	@Override
	public void updatePrdStatus(Product prd) throws Exception {
		productDAO.updatePrdStatus(prd);
	}

}
