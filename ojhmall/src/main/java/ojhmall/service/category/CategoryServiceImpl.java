package ojhmall.service.category;

import java.util.List;
import java.util.Map;

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
	public List<Map<String, Object>> selectUpperCtgrList() throws Exception {
		return categoryDAO.selectUpperCtgrList();
	}
	// 하위 카테고리 불러오기
	@Override
	public List<Map<String, Object>> selectLowerCtgrList() throws Exception {
		return categoryDAO.selectLowerCtgrList();
	}
	// 카테고리 상품 인기순 정렬
	@Override
	public List<Product> dpPrd(Product prd) throws Exception {
		return categoryDAO.dpPrd(prd);
	}
	// 카테고리 상품 가격순 정렬
	@Override
	public List<Product> dpPrdByPrice(Product prd) throws Exception {
		return categoryDAO.dpPrdByPrice(prd);
	}
	
}
