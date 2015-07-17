package ojhmall.controller.cart;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import ojhmall.service.cart.CartService;
import ojhmall.service.category.CategoryService;
import ojhmall.service.product.ProductService;
import ojhmall.vo.Cart;
import ojhmall.vo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
	Logger log = Logger.getLogger(this.getClass());
	@Resource(name = "cartService")
	private CartService cartService;
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	@Resource(name = "productService")
	private ProductService productService;

	// 장바구니 정보 출력
	@RequestMapping(value = "/cart/cartView.do")
	public ModelAndView showCart(HttpSession session)
			throws Exception {
		ModelAndView mv = new ModelAndView("/cart/cartView");
		User user = (User) session.getAttribute("userLogInInfo");
		Cart cart = new Cart();
		cart.setUserNumber(user.getUserNumber());
		List<Cart> cartList = cartService.loadCartList(cart);
		mv.addObject("cartList", cartList);
		mv.addObject("totalCartPrice", cartService.calTotalCartPrice(cartList)); 
		mv.addObject("totalDeliveryFee",
				cartService.calTotalDeliveryFee(cartList)); // 배송비 총 금액
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 장바구니 생성
	@RequestMapping(value = "/cart/addPrdToCart.do")
	public ModelAndView addCart(HttpSession session, Cart cart)
			throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/product/prdView.do?prdNum="
						+ cart.getPrdNumber());
		if (session.getAttribute("userLogInInfo") == null)
			cart.setUserNumber(1);
		else {
			User user = (User) session.getAttribute("userLogInInfo");
			cart.setUserNumber(user.getUserNumber());
		}
		cartService.addCart(cart);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 장바구니 삭제
	@RequestMapping(value = "/cart/deleteCart.do")
	public ModelAndView deleteCart(
			@RequestParam(value = "cartNumber") int cartNumber,
			HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/cart/cartView");
		User user = (User) session.getAttribute("userLogInInfo");
		Cart cart = new Cart();
		cart.setUserNumber(user.getUserNumber());
		cartService.removeCart(cartNumber);
		List<Cart> cartList = cartService.loadCartList(cart);
		mv.addObject("cartList", cartList);
		mv.addObject("totalCartPrice", cartService.calTotalCartPrice(cartList));
		mv.addObject("totalDeliveryFee",
				cartService.calTotalDeliveryFee(cartList)); // 배송비 총 금액
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 변경할 장바구니 선택
	@RequestMapping(value = "/cart/changeCartView.do")
	public ModelAndView changeCart(
			@RequestParam(value = "cartNumber") int cartNumber,
			HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/cart/changeCartView");
		Cart cart = cartService.selectCart(cartNumber);
		mv.addObject("selectedCart", cart);
		return mv;
	}
	// 선택 장바구니 변경
	@RequestMapping(value = "/cart/updateCart.do", method = RequestMethod.GET)
	public ModelAndView updateCart(Cart cart, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/cart/cartView");
		cartService.updateCart(cart);
		return mv;
	}
}
