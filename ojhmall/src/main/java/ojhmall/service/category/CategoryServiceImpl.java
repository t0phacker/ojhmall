package ojhmall.service.category;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import ojhmall.dao.UserDAO;
import ojhmall.dao.category.CategoryDAO;
import ojhmall.vo.Category;
import ojhmall.vo.Product;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "categoryDAO")
	private CategoryDAO categoryDAO;

	@Override
	public List<Map<String, Object>> selectUpperCtgrList(Map<String, Object> map) throws Exception {
		return categoryDAO.selectUpperCtgrList(map);
	}

	@Override
	public List<Map<String, Object>> selectLowerCtgrList(
			Map<String, Object> map) throws Exception {
		return categoryDAO.selectLowerCtgrList(map);
	}

	@Override
	public List<Product> dpPrd(Product prd) throws Exception {
		return categoryDAO.dpPrd(prd);
	}
}
