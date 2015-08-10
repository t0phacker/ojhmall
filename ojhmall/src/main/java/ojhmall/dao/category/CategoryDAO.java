package ojhmall.dao.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Product;

import org.springframework.stereotype.Repository;

@Repository("categoryDAO")
public class CategoryDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUpperCtgrList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return (List<Map<String, Object>>)selectList("Category.upperCtgrList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectLowerCtgrList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return (List<Map<String, Object>>)selectList("Category.lowerCtgrList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Product> dpPrd(Product prd) throws Exception {
		return (List<Product>) selectList("Category.dpPrdByHitCnt", prd);
	}

	@SuppressWarnings("unchecked")
	public List<Product> dpPrdByPrice(Product prd) {
		return (List<Product>) selectList("Category.dpPrdByPrice", prd);
	}

}
