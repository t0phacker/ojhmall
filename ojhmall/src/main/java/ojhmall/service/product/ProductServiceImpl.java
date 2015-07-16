package ojhmall.service.product;

import java.util.List;

import javax.annotation.Resource;

import ojhmall.dao.product.ProductDAO;
import ojhmall.vo.Product;

import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource(name = "productDAO")
	private ProductDAO productDAO;
	// 상품 정보 출력
	@Override
	public Product showPrd(Product prd) throws Exception {
		Product product = productDAO.showPrd(prd);
		productDAO.updateHitCount(product);
		return product;
	}
	// 상품 검색
	@Override
	public List<Product> schPrd(Product prd) throws Exception {
		return productDAO.schPrd(prd);
	}

}
