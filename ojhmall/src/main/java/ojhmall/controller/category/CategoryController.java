package ojhmall.controller.category;

import java.util.List;

import javax.annotation.Resource;

import ojhmall.service.category.CategoryService;
import ojhmall.vo.Product;
import ojhmall.vo.ProductFactory;
import ojhmall.vo.ProductStatus;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller//("/category")
public class CategoryController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "categoryService")
	private CategoryService categoryService;
	
	// 카테고리 페이지로 이동
	@RequestMapping(value="/category/ctgrView.do", method = RequestMethod.GET)
	public ModelAndView ctgrView(@RequestParam(value="ctgrVal") int ctgrVal) throws Exception {
		ModelAndView mv = new ModelAndView("/category/ctgrView");
        int prdStatForDP = 2;
        ProductStatus sellingNow = ProductStatus.SELLING_NOW;
        Product prdByCtgrNum = ProductFactory.createPrdForCtgr(ctgrVal, sellingNow); // 카테고리 번호와 일치하는 상품 조회
        List<Product> prdList = categoryService.dpPrd(prdByCtgrNum); // 상품리스트에 상품 추가
        mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
        mv.addObject("prdList", prdList);
        mv.addObject("ctgrNumber", ctgrVal);
		return mv;
	}
	
	@RequestMapping(value="/category/ctgrViewByPrice.do", method = RequestMethod.GET)
	public ModelAndView ctgrViewByPrice(@RequestParam(value="ctgrVal") int ctgrVal) throws Exception {
		ModelAndView mv = new ModelAndView("/category/ctgrView");
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
        Product prd = new Product();
        prd.setCtgrNumber(ctgrVal); // 카테고리 번호와 일치하는 상품 조회
        List<Product> prdList = categoryService.dpPrdByPrice(prd); // 상품리스트에 상품 추가
        mv.addObject("prdList", prdList);
        mv.addObject("ctgrNumber", ctgrVal);
		return mv;
	}
}
