package ojhmall.controller.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ojhmall.service.cart.CartService;
import ojhmall.service.category.CategoryService;
import ojhmall.service.order.OrderService;
import ojhmall.service.user.UserService;
import ojhmall.vo.Cart;
import ojhmall.vo.Order;
import ojhmall.vo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "orderService")
	private OrderService orderService;
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "cartService")
	private CartService cartService;

	// 주문서 생성
	@RequestMapping(value = "/order/orderByPrd.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addCart(HttpSession session, Order order)
			throws Exception {
		ModelAndView mv = new ModelAndView("order/orderViewFromPrd");

		if (session.getAttribute("userLogInInfo") == null) {
			order.setUserNumber(1);
		}
		User user = (User) session.getAttribute("userLogInInfo");
		order.setUserNumber(user.getUserNumber());

		order.setOrderStatus(1); // 상수 쓰지말고, 이넘 클래스로
		orderService.insertOrder(order); // order table에 레코드 생성
		order.setOrderNumber(orderService.exportOrderNumber(order)
				.getOrderNumber()); // 생성한 주문 레코드에서 주문번호 추출
		order = orderService.getPrdInfo(order); // 
		orderService.insertOrderInfo(order);
		
		mv.addObject("orderNumber", order.getOrderNumber());
		mv.addObject("userInfo", user);
		mv.addObject("orderInfo", order);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 장바구니에서 상품 전체 주문
	@RequestMapping(value = "/order/orderFromCartTotal.do", method = {
			RequestMethod.POST })
	public ModelAndView orderFromCartTotal(HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderViewFromCart");
		Order order = new Order();
		if (session.getAttribute("userLogInInfo") == null) {
			order.setUserNumber(1);
		}
		User user = (User) session.getAttribute("userLogInInfo");
		order.setUserNumber(user.getUserNumber());
		order.setOrderStatus(1);
		orderService.insertOrder(order);
		order.setOrderNumber(orderService.exportOrderNumber(order)
				.getOrderNumber());
		
		Cart cart = new Cart();
		cart.setUserNumber(user.getUserNumber());
		List<Cart> cartList = cartService.loadCartList(cart); // 회원이 보유 중인 장바구니 리스트 불러오기
		String cartNumbers = orderService.mergeCartNum(cartList);
		orderService.setItemsIntoOrder(order, cartList); // 장바구니 내 동일 상품 병합
		List<Order> orderList = orderService.getOrderList(order); // 주문 리스트
		orderList = orderService.calTotalDel(orderList); // 상품별 배송비 계산
		int totalPrice = orderService.calTotalPrice(orderList);
		int totalDelivery = Integer.parseInt(request
				.getParameter("deliveryTotal"));
		
		mv.addObject("cartNumbers", cartNumbers);
		mv.addObject("orderNumber", orderList.get(0).getOrderNumber());
		mv.addObject("totalDelivery", totalDelivery);
		mv.addObject("totalPrice", totalPrice);
		mv.addObject("orderList", orderList);
		mv.addObject("userInfo", user);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 장바구니 선택 상품 주문
	@RequestMapping(value = "/order/orderFromCartSelected.do", method = RequestMethod.POST)
	public ModelAndView orderFromCartSelected(HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderViewFromCart");
		Order order = new Order();
		if (session.getAttribute("userLogInInfo") == null) {
			order.setUserNumber(1);
		}
		User user = (User) session.getAttribute("userLogInInfo");
		order.setUserNumber(user.getUserNumber()); // 길다
		order.setOrderStatus(1);
		orderService.insertOrder(order);
		order.setOrderNumber(orderService.exportOrderNumber(order)
				.getOrderNumber());

		String prdNumber = request.getParameter("prdNumberTotal");
		String prdAmount = request.getParameter("prdAmountTotal");
		String cartPrice = request.getParameter("cartPriceTotal");
		String cartNumbers = request.getParameter("cartNumberTotal");
		Cart cart = new Cart();
		cart.setUserNumber(user.getUserNumber());
		List<Cart> cartList = cartService.loadCartList(cart);
		orderService.trimOrderData(prdNumber, prdAmount, cartPrice, order,
				cartList);
		List<Order> orderList = orderService.getOrderList(order);
		orderList = orderService.calTotalDel(orderList);
		int totalPrice = orderService.calTotalPrice(orderList);
		int totalDelivery = Integer.parseInt(request
				.getParameter("deliveryTotal"));

		mv.addObject("cartNumbers", cartNumbers); // 상품번호 묶음 전송
		mv.addObject("orderNumber", orderList.get(0).getOrderNumber());
		mv.addObject("totalDelivery", totalDelivery);
		mv.addObject("totalPrice", totalPrice);
		mv.addObject("orderList", orderList);
		mv.addObject("userInfo", user);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 장바구니 상품별 주문
	@RequestMapping(value = "/order/orderOneFromCart.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView orderOneFromCart(
			@RequestParam(value = "cartNumber") int cartNumber,
			HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderViewFromCartOne");
		Order order = new Order();
		if (session.getAttribute("userLogInInfo") == null) {
			order.setUserNumber(1);
		}
		User user = (User) session.getAttribute("userLogInInfo");
		order.setUserNumber(user.getUserNumber());
		order.setOrderStatus(1);
		orderService.insertOrder(order);
		order.setOrderNumber(orderService.exportOrderNumber(order)
				.getOrderNumber());
		Cart cart = cartService.selectCart(cartNumber);
		order = orderService.orderOneCart(order, cart);
		
		mv.addObject("cartNumber", cartNumber);
		mv.addObject("orderNumber", order.getOrderNumber());
		mv.addObject("totalDelivery", order.getDeliveryFee());
		mv.addObject("totalPrice", order.getPrice());
		mv.addObject("userInfo", user);
		mv.addObject("orderInfo", order);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 주문서 삭제, 조건 : 주문상태 2 이하(결제 대기)
	@RequestMapping(value = "/order/deleteOrder.do", method = {RequestMethod.POST})
	public ModelAndView deleteOrder(Order order, HttpSession session)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageForCustomer");

		orderService.deleteOrder(order);
		
		try {
			User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보 추출
			List<Order> orderList = orderService.getOrderByUser(user); // 회원번호로 주문상태 2 이상(결제 대기)인 주문리스트 불러오기
			
			mv.addObject("orderList", orderList);
		} catch (Exception e) {
			mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
			mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
			return mv;
		}
		
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
}
