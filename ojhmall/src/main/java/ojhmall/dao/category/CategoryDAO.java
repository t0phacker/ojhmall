package ojhmall.dao.category;

import java.util.List;
import java.util.Map;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Product;

import org.springframework.stereotype.Repository;

@Repository("categoryDAO")
public class CategoryDAO extends AbstractDAO {

	public List<Map<String, Object>> selectUpperCtgrList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("Category.upperCtgrList", map);
	}

	public List<Map<String, Object>> selectLowerCtgrList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("Category.lowerCtgrList", map);
	}

	public List<Product> dpPrd(Product prd) throws Exception {
		return (List<Product>) selectList("Category.dpPrd", prd);
	}

}
