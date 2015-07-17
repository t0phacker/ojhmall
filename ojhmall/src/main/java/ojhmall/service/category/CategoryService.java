package ojhmall.service.category;

import java.util.List;
import java.util.Map;

import ojhmall.vo.Product;

public interface CategoryService {

	List<Map<String, Object>> selectUpperCtgrList() throws Exception;

	List<Map<String, Object>> selectLowerCtgrList() throws Exception;

	List<Product> dpPrd(Product prd) throws Exception;

}
