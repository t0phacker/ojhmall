package ojhmall.service.product;

import java.util.List;

import ojhmall.vo.Product;

public interface ProductService {

	Product showPrd(Product prd) throws Exception;

	List<Product> schPrd(Product prd) throws Exception;

}
