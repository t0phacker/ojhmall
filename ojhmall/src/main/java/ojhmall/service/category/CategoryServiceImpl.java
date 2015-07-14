package ojhmall.service.category;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

import javax.annotation.Resource;

import ojhmall.dao.category.CategoryDAO;
import ojhmall.vo.Product;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "categoryDAO")
	private CategoryDAO categoryDAO;
	// 상위 카테고리 불러오기
	@Override
	public List<Map<String, Object>> selectUpperCtgrList(Map<String, Object> map) throws Exception {
		return categoryDAO.selectUpperCtgrList(map);
	}
	// 하위 카테고리 불러오기
	@Override
	public List<Map<String, Object>> selectLowerCtgrList(
			Map<String, Object> map) throws Exception {
		return categoryDAO.selectLowerCtgrList(map);
	}
	// 카테고리 상품 진열
	@Override
	public List<Product> dpPrd(Product prd) throws Exception {
		return categoryDAO.dpPrd(prd);
	}
	
}
