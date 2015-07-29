package ojhmall.controller.product;

import java.util.List;

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
	// 상품 페이지 출력
	@RequestMapping(value="/product/prdView.do")
    public ModelAndView showPrd(@RequestParam(value="prdNum") int prdNum) throws Exception{
        ModelAndView mv = new ModelAndView("/product/prdView");
        Product prd = new Product();
        prd.setPrdNumber(prdNum);
        prd = productService.showPrd(prd);
        mv.addObject("prdInfo", prd);
        mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
        return mv;
    }
	// 상품 검색
	@RequestMapping(value="/product/prdSearch.do")
    public ModelAndView searchPrd(HttpServletRequest request, Product prd) throws Exception{
        ModelAndView mv = new ModelAndView("/product/prdSearch");
        List<Product> prdSchList = productService.schPrd(prd);
        mv.addObject("prdSchList", prdSchList);
        mv.addObject("prd", prd); // 검색어 결과 페이지에 출력
        mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
        return mv;
    }
	// 메인페이지에 전시할 상품리스트 불러오기(관심지수순)
	@RequestMapping(value="/index.do")
	public ModelAndView initIndex() throws Exception {
		ModelAndView mv = new ModelAndView("../../index");
		int initPrdCount = 6;
		List<Product> prdList = productService.initPrd(initPrdCount);
		mv.addObject("prdList", prdList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
}
