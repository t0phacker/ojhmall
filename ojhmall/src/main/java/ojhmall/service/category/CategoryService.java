package ojhmall.service.category;

import java.util.List;
import java.util.Map;

import ojhmall.vo.Product;

public interface CategoryService {

	List<Map<String, Object>> selectUpperCtgrList(Map<String, Object> commandMap) throws Exception;

	List<Map<String, Object>> selectLowerCtgrList(Map<String, Object> commandMap) throws Exception;

	List<Product> dpPrd(Product prd) throws Exception;

}
