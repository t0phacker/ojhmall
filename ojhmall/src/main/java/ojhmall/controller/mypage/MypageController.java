package ojhmall.controller.mypage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import ojhmall.service.category.CategoryService;
import ojhmall.service.mypage.MypageService;
import ojhmall.service.order.OrderService;
import ojhmall.service.pay.PayService;
import ojhmall.service.product.ProductService;
import ojhmall.vo.Order;
import ojhmall.vo.Payment;
import ojhmall.vo.Product;
import ojhmall.vo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MypageController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "mypageService")
	private MypageService mypageService;
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	@Resource(name = "orderService")
	private OrderService orderService;
	@Resource(name = "payService")
	private PayService payService;
	@Resource(name = "productService")
	private ProductService productService;

	// 구매자 마이페이지 출력
	@RequestMapping(value = "/mypage/mypageForCustomer.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showCustomerPage(HttpSession session, Order order)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageForCustomer");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보
																	// 추출
		List<Order> orderList = orderService.getOrderByUser(user); // 회원번호로 주문상태 2 이상(결제대기)인 주문리스트불러오기

		mv.addObject("orderList", orderList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 입금 확인 후 관리자에게 입금정보 알림
	@RequestMapping(value = "/mypage/completePay.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView completePay(HttpSession session, Payment pay)
			throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");

		payService.insertPay(pay);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 관리자 페이지 출력
	@RequestMapping(value = "/mypage/mypageForAdmin.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showAdminPage(HttpSession session, Payment pay)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageForAdmin");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보
																	// 추출
		List<Payment> payList = payService.getPayList(); // 결제 상태 1(결제 확인 전)인
															// 결제리스트 불러오기
		List<Payment> paidList = payService.getPaidList(); // 결제 상태 2(결제 확인)인
															// 결제리스트 불러오기

		// 체크드 예외 공부 자바 기본
		mv.addObject("payList", payList);
		mv.addObject("paidList", paidList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 결제상태 1에서 2로 변경(결제확인)
	@RequestMapping(value = "/mypage/checkPay.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView checkPay(HttpSession session, Payment pay)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageForAdmin");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보
																	// 추출
		payService.updatePayStatus(pay);
		List<Payment> payList = payService.getPayList(); // 결제 상태 1(결제 확인 전)인
															// 결제리스트 불러오기
		List<Payment> paidList = payService.getPaidList(); // 결제 상태 2(결제 확인)인
															// 결제리스트 불러오기
		List<Order> orderList = payService.getOrderFromPay(pay); // 결제 정보에서 주문정보
																	// 불러오기
		orderService.updateOrderByPay(orderList); // 주문 상태 3(결제 완료)로 변경
		// 체크드 예외 공부 자바 기본
		mv.addObject("payList", payList);
		mv.addObject("paidList", paidList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 판매자 마이페이지 출력
	@RequestMapping(value = "/mypage/mypageForSeller.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showSellerPage(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageForSeller");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보
																	// 추출
		List<Order> orderList = orderService.getOrderForSeller(user); // 주문정보
																		// 불러오기

		// 체크드 예외 공부 자바 기본
		mv.addObject("orderList", orderList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 판매자 주문확인: 주문상태 3(결제완료) -> 4(배송중)
	@RequestMapping(value = "/mypage/checkOrder.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView checkOrder(HttpSession session, Order order)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageForSeller");
		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보
																	// 추출
		order.setOrderStatus(4);
		orderService.updateOrdered(order);
		Payment pay = new Payment();
		pay.setOrderNumber(order.getOrderNumber());
		pay.setPayStatus(4);
		payService.updatePay(pay);
		// 체크드 예외 공부 자바 기본
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 구매자 배송완료
	@RequestMapping(value = "/mypage/checkDelivery.do")
	public ModelAndView checkDelivery(
			@RequestParam(value = "orderNumber") int orderNumber,
			HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageForCustomer");
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		order.setOrderStatus(5);
		orderService.updateOrdered(order);

		User user = (User) session.getAttribute("userLogInInfo"); // 세션에서 로그인 정보
																	// 추출
		List<Order> orderList = orderService.getOrderByUser(user); // 회원번호로 주문상태 2 이상(결제 대기)인
																	// 주문리스트 불러오기
		mv.addObject("orderList", orderList);

		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 판매자 상품 확인 페이지
	@RequestMapping(value = "/mypage/sellerCheckPrd.do")
	public ModelAndView SellerCheckPrd(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageSellerCheckPrd");
		User user = (User) session.getAttribute("userLogInInfo");
		List<Product> prdList = productService.showSellerPrdList(user); // 판매자 회원번호로 상품 조회하여 리스트 작성
		mv.addObject("userInfo", user);
		mv.addObject("prdList", prdList);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 상품 등록 페이지
	@RequestMapping(value = "/mypage/sellerAddPrd.do")
	public ModelAndView sellerAddPrd(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageSellerAddPrd");
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 상품 등록
	@RequestMapping(value = "/mypage/addPrdBySeller.do", method = RequestMethod.POST)
	public ModelAndView addPrdBySeller(HttpSession session, Product prd)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageSellerCheckPrd");
		User user = (User) session.getAttribute("userLogInInfo");
		prd.setUserNumber(user.getUserNumber());
		productService.addPrd(prd);
		List<Product> prdList = productService.showSellerPrdList(user); // 판매자 회원번호로 상품 조회하여  리스트 작성

		mv.addObject("prdList", prdList);
		mv.addObject("userInfo", user);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 상품정보 변경 페이지
	@RequestMapping(value = "/mypage/changePrdInfo.do", method = RequestMethod.POST)
	public ModelAndView changePrdInfo(HttpSession session, Product prd)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageSellerChangePrd");
		User user = (User) session.getAttribute("userLogInInfo");
		prd = productService.showPrdInfoForUpdate(prd);
		mv.addObject("prdInfo", prd);
		mv.addObject("userInfo", user);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}

	// 상품 변경
	@RequestMapping(value = "/mypage/changedPrdBySeller.do", method = RequestMethod.POST)
	public ModelAndView changedPrdInfo(HttpSession session, Product prd)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageSellerCheckPrd");
		User user = (User) session.getAttribute("userLogInInfo");
		productService.updatePrdInfo(prd);
		List<Product> prdList = productService.showSellerPrdList(user); 

		mv.addObject("prdList", prdList);
		mv.addObject("userInfo", user);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 관리자 상품 관리 페이지
	@RequestMapping(value = "/mypage/mypageAdminCheckPrd.do", method = RequestMethod.GET)
	public ModelAndView mypageAdminCheckPrd(HttpSession session, Product prd)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageAdminCheckPrd");
		User user = (User) session.getAttribute("userLogInInfo");
		int prdStatus = 1;
		List<Product> prdList = productService.getPrdListToAdmin(prdStatus);
		mv.addObject("prdList", prdList);
		mv.addObject("userInfo", user);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
	// 상품 전시 승인
	@RequestMapping(value = "/mypage/changePrdStatus.do", method = RequestMethod.POST)
	public ModelAndView changePrdStatus(HttpSession session, Product prd)
			throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/mypageAdminCheckPrd");
		User user = (User) session.getAttribute("userLogInInfo");
		int prdStatusUpdate = 2;
		prd.setPrdStatus(prdStatusUpdate);
		productService.updatePrdStatus(prd);
		
		int prdStatus = 1; // 상품 상태가 1(등록 대기)인 상품 불러오기
		List<Product> prdList = productService.getPrdListToAdmin(prdStatus);
		mv.addObject("prdList", prdList);
		mv.addObject("userInfo", user);
		mv.addObject("upperCategoryList", categoryService.selectUpperCtgrList());
		mv.addObject("lowerCategoryList", categoryService.selectLowerCtgrList());
		return mv;
	}
}
