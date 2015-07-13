package ojhmall.service.category;

import ojhmall.vo.Category;
import ojhmall.vo.Product;

import java.util.List;
import java.util.Map;

public interface CategoryService {

	List<Map<String, Object>> selectUpperCtgrList(Map<String, Object> commandMap) throws Exception;

	List<Map<String, Object>> selectLowerCtgrList(Map<String, Object> commandMap) throws Exception;

	List<Product> dpPrd(Product prd) throws Exception;

}
