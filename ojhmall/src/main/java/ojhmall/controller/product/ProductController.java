package ojhmall.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import ojhmall.service.category.CategoryService;
import ojhmall.service.product.ProductService;
import ojhmall.vo.Product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	Map<String, Object> commandMap = new HashMap(); // 카테고리 저장
	// 상품 페이지 출력
	@RequestMapping(value="/product/prdView.do")
    public ModelAndView showPrd(@RequestParam(value="prdNum") int prdNum) throws Exception{
        ModelAndView mv = new ModelAndView("/product/prdView");
        Product prd = new Product();
        prd.setPrdNumber(prdNum);
        prd = productService.showPrd(prd);
        mv.addObject("prdInfo", prd);
        List<Map<String,Object>> upperCtgrList = categoryService.selectUpperCtgrList(commandMap);
        mv.addObject("upperCategoryList", upperCtgrList);
        List<Map<String,Object>> lowerCtgrList = categoryService.selectLowerCtgrList(commandMap);
        mv.addObject("lowerCategoryList", lowerCtgrList);
        return mv;
    }
	// 상품 검색
	@RequestMapping(value="/product/prdSearch.do")
    public ModelAndView searchPrd(HttpServletRequest request, Product prd) throws Exception{
        ModelAndView mv = new ModelAndView("/product/prdSearch");
        List<Product> prdSchList = productService.schPrd(prd);
        mv.addObject("prdSchList", prdSchList);
        mv.addObject("prd", prd); // 검색어 결과 페이지에 출력
        List<Map<String,Object>> upperCtgrList = categoryService.selectUpperCtgrList(commandMap);
        mv.addObject("upperCategoryList", upperCtgrList);
        List<Map<String,Object>> lowerCtgrList = categoryService.selectLowerCtgrList(commandMap);
        mv.addObject("lowerCategoryList", lowerCtgrList);
        return mv;
    }
}
