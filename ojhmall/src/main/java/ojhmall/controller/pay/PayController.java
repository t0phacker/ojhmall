package ojhmall.controller.pay;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ojhmall.service.cart.CartService;
import ojhmall.service.category.CategoryService;
import ojhmall.service.order.OrderService;
import ojhmall.service.pay.PayService;
import ojhmall.service.user.UserService;
import ojhmall.vo.Order;
import ojhmall.vo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PayController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "payService")
	private PayService payService;
	@Resource(name = "orderService")
	private OrderService orderService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	@Resource(name = "cartService")
	private CartService cartService;
	// 결제 정보 출력, 장바구니에서 복수 상품 주문시
	@RequestMapping(value = "/pay/payView.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showPayBill(HttpSession session,
			HttpServletRequest request)
			throws Exception {
		ModelAndView mv = new ModelAndView("pay/payView");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보 추출
		String orderNumber = request.getParameter("orderNumber");
		String cartNumber = request.getParameter("cartNumber");
		Order order = new Order();
		order.setUserNumber(user.getUserNumber()); // 주문 객체에 회원 번호 삽입
		order.setOrderNumber(Integer.parseInt(orderNumber));
		List<Order> orderList = orderService.getOrderList(order);
		orderService.updateOrderStatus(order); // 주문 상태 2로 변경(결제 대기)
		orderList = orderService.calTotalDel(orderList); // 상품별 배송비 계산
		int totalPrice = orderService.calTotalPrice(orderList);
		cartService.removeCartList(cartNumber); // 주문된 카트리스트 삭제
		User admin = new User();
		admin.setUserType(0);
		admin = userService.getAdminAcc(admin); // 관리자 정보 조회하여 입금 계좌번호 출력
		orderService.updateDelAndPrc(orderList); // 주문 묶음 및 개별 주문 배송비 책정
		
		mv.addObject("adminInfo", admin);
		mv.addObject("totalPrice", totalPrice);
		mv.addObject("userInfo", user);
		mv.addObject("orderList", orderList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 장바구니에서 단일 상품 주문
	@RequestMapping(value = "/pay/payViewCartOne.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showBillCartOne(int cartNumber, int orderNumber, HttpSession session, Order order)
			throws Exception {
		ModelAndView mv = new ModelAndView("pay/payView");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보 추출
		order.setUserNumber(user.getUserNumber()); // 주문 객체에 회원 번호 삽입
		order.setOrderNumber(orderNumber);
		List<Order> orderList = orderService.getOrderList(order);
		orderService.updateOrderStatus(order); // 주문 상태 2로 변경(결제 대기)
		orderList = orderService.calTotalDel(orderList); // 상품별 배송비 계산
		int totalPrice = orderService.calTotalPrice(orderList);
		
		User admin = new User();
		admin.setUserType(0);
		admin = userService.getAdminAcc(admin); // 관리자 정보 조회하여 입금 계좌번호 출력
		
		cartService.removeCart(cartNumber); // 주문된 장바구니 삭제
		orderService.updateDelAndPrc(orderList);
		
		mv.addObject("adminInfo", admin);
		mv.addObject("totalPrice", totalPrice);
		mv.addObject("userInfo", user);
		mv.addObject("orderList", orderList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 상품페이지에서 상품 바로 주문
	@RequestMapping(value = "/pay/payViewPrdOne.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showBillPrdOne(int orderNumber, HttpSession session, Order order)
			throws Exception {
		ModelAndView mv = new ModelAndView("pay/payView");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보 추출
		order.setUserNumber(user.getUserNumber()); // 주문 객체에 회원 번호 삽입
		order.setOrderNumber(orderNumber);
		List<Order> orderList = orderService.getOrderList(order);
		orderService.updateOrderStatus(order); // 주문 상태 2로 변경(결제 대기)
		orderList = orderService.calTotalDel(orderList); // 상품별 배송비 계산
		int totalPrice = orderService.calTotalPrice(orderList);
		
		
		User admin = new User();
		admin.setUserType(0);
		admin = userService.getAdminAcc(admin); // 관리자 정보 조회하여 입금 계좌번호 출력
		orderService.updateDelAndPrc(orderList);
		
		mv.addObject("adminInfo", admin);
		mv.addObject("totalPrice", totalPrice);
		mv.addObject("userInfo", user);
		mv.addObject("orderList", orderList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 결제창 출력
	@RequestMapping(value="/pay/payOrder.do")
    public ModelAndView showBankSystem(@RequestParam(value="orderNumber") int orderNumber,
    		HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView("/pay/payByBank");
        
        User admin = new User();
		admin.setUserType(0);
		admin = userService.getAdminAcc(admin); // 관리자 정보 조회하여 입금 계좌번호 출력
		Order order = orderService.getOrderPrice(orderNumber); // 주문 가격정보 불러오기
		User user = (User) session.getAttribute("userLogInInfo");
		mv.addObject("orderInfo", order);
		mv.addObject("adminInfo", admin);
		mv.addObject("userInfo", user);
        return mv;
	}
}
