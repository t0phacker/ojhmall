package ojhmall.controller.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ojhmall.service.category.CategoryService;
import ojhmall.vo.Product;

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
	// 카테고리 리스트 출력
	@RequestMapping(value="index.do")
    public ModelAndView openCtgr(Map<String, Object> commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("../../index");
        
        List<Map<String,Object>> upperCtgrList = categoryService.selectUpperCtgrList(commandMap);
        mv.addObject("upperCategoryList", upperCtgrList);
        List<Map<String,Object>> lowerCtgrList = categoryService.selectLowerCtgrList(commandMap);
        mv.addObject("lowerCategoryList", lowerCtgrList);
        return mv;
    }
	// 카테고리 페이지로 이동
	@RequestMapping(value="/category/ctgrView.do", method = RequestMethod.GET)
	public ModelAndView ctgrView(@RequestParam(value="ctgrVal") int ctgrVal) throws Exception {
		ModelAndView mv = new ModelAndView("/category/ctgrView");
		Map<String, Object> commandMap = new HashMap();
		List<Map<String,Object>> upperCtgrList = categoryService.selectUpperCtgrList(commandMap);
        mv.addObject("upperCategoryList", upperCtgrList);
        List<Map<String,Object>> lowerCtgrList = categoryService.selectLowerCtgrList(commandMap);
        mv.addObject("lowerCategoryList", lowerCtgrList);
        Product prd = new Product();
        prd.setCtgrNumber(ctgrVal); // 카테고리 번호와 일치하는 상품 조회
        List<Product> prdList = categoryService.dpPrd(prd); // 상품리스트에 상품 추가
        mv.addObject("prdList", prdList);
		return mv;
	}
}
