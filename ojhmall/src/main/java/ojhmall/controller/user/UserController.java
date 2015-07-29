package ojhmall.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ojhmall.service.user.UserService;
import ojhmall.vo.Admin;
import ojhmall.vo.Customer;
import ojhmall.vo.Seller;
import ojhmall.vo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
// ("/user")
public class UserController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "userService")
	private UserService userService;

	// 회원 가입 선택 화면
	@RequestMapping(value = "/user/SiginUpForm.do")
	public ModelAndView openSignInForm(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/SignUpForm");

		return mv;
	}

	// 구매자 회원가입 화면으로 이동
	@RequestMapping(value = "/user/CustomerSignUpForm.do")
	public ModelAndView goCustomerSign(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/CustomerSignUpForm");

		return mv;
	}

	// 판매자 회원가입 화면으로 이동
	@RequestMapping(value = "/user/SellerSignUpForm.do")
	public ModelAndView goSellerSign(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/SellerSignUpForm");

		return mv;
	}

	// 구매자 회원가입
	@RequestMapping(value = "/user/insertCustomer.do", method = RequestMethod.POST)
	public ModelAndView setCustomer(Customer customer) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		userService.setCustomer(customer);

		return mv;
	}

	// 판매자 회원가입
	@RequestMapping(value = "/user/insertSeller.do", method = RequestMethod.POST)
	public ModelAndView setSeller(Seller seller) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		Map<String, Object> map = new HashMap<String, Object>();
		map = userService.selectMaxNum(map);

		seller.setUserNumber((Integer) map.get("UserNumber") + 1);
		userService.setSeller(seller);

		return mv;
	}

	@RequestMapping(value = "user/LogInForm.do")
	public ModelAndView showLogIn(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/LogInForm");

		return mv;
	}

	// 로그인 : 데이터베이스 조회
	@RequestMapping(value = "/user/insertLogInInfo.do", method = RequestMethod.POST)
	public ModelAndView logIn(User user, HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		try {
			User userLogCheck = userService.findByIdAndPw(user);
			
			if (userLogCheck != null) {
				session.setAttribute("userLogInInfo", userLogCheck);
			}
		} catch (Exception e) {
			return mv;
		}
		

		return mv;
	}

	// 로그아웃
	@RequestMapping(value = "logOut.do", method = RequestMethod.GET)
	// {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView LogOut(Customer customer, HttpSession session,
			HttpServletRequest request) throws Exception {
		session.setAttribute("userLogInInfo", null);
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		return mv;

	}

	// 관리자 회원정보 화면 출력
	@RequestMapping(value = "/user/AdminInfoForm.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showAdminInfoForm(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/AdminInfoForm");
		return mv;
	}

	// 구매자 회원정보 화면 출력
	@RequestMapping(value = "/user/CustomerInfoForm.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showCustomerInfoForm(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/CustomerInfoForm");
		return mv;
	}

	// 판매자 회원정보 화면 출력
	@RequestMapping(value = "/user/SellerInfoForm.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showSellerInfoForm(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/SellerInfoForm");
		return mv;
	}

	// 관리자 회원정보 변경
	@RequestMapping(value = "/user/changeAdminInfo.do", method = RequestMethod.POST)
	public ModelAndView updateAdmin(Admin admin, HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		Admin updateAdmin = (Admin) userService.updateAdmin(admin);
		session.setAttribute("userLogInInfo", updateAdmin);
		return mv;
	}

	// 구매자 회원정보 변경
	@RequestMapping(value = "/user/changeCustomerInfo.do", method = RequestMethod.POST)
	public ModelAndView changeCustomer(Customer customer, HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		Customer updatedCustomer = userService.updateCustomer(customer);
		session.setAttribute("userLogInInfo", updatedCustomer);
		return mv;
	}

	// 판매자 회원정보 변경
	@RequestMapping(value = "/user/changeSellerInfo.do", method = RequestMethod.POST)
	public ModelAndView changeSeller(Seller seller, HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		Seller updatedSeller = userService.updatedSeller(seller);
		session.setAttribute("userLogInInfo", updatedSeller);
		return mv;
	}

	@RequestMapping(value = "/user/backToMain.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backToMain(Map<String, Object> commandMap)
			throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		return mv;
	}

	// 회원정보 삭제
	@RequestMapping(value = "/user/removeUserInfo.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView removeUserInfo(User user, HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		userService.removeUser(user);
		session.setAttribute("userLogInInfo", null);
		System.out.println("do you want to discard?");
		return mv;
	}
	/*
	 * @RequestMapping(value="/user/insertBoard.do") public ModelAndView
	 * setCustomer(Customer customer) throws Exception{ ModelAndView mv = new
	 * ModelAndView("redirect:/user/openBoardList.do");
	 * 
	 * userService.setCustomer(customer);
	 * 
	 * return mv; }
	 */

	/*
	 * @RequestMapping(value="/user/insertBoard.do", method =
	 * RequestMethod.POST) public ModelAndView insertBoard(HttpServletRequest
	 * request) throws Exception{ ModelAndView mv = new
	 * ModelAndView("redirect:/user/openBoardList.do");
	 * //System.out.println(commandMap);
	 * //userService.insertBoard(commandMap.getMap());
	 * //System.out.println(commandMap.getMap());
	 * 
	 * String id = request.getParameter("inputEmail");
	 * 
	 * Customer customer = new Customer(); customer.setId(id);
	 * 
	 * CommandMap map = new CommandMap(); //map.put(key, value);
	 * 
	 * System.out.println(customer.getId()); return mv; }
	 */

	/*
	 * switch (userLogCheck.getUserType()) { case 0: Admin admin = new Admin();
	 * admin.setId(user.getId()); admin.setUserPassword(user.getUserPassword());
	 * Admin adminOn = userService.getAdminInfo(admin);
	 * session.setAttribute("userLogInInfo", adminOn);
	 * System.out.println("final : " + adminOn); case 1: Customer customer = new
	 * Customer(); customer.setId(user.getId());
	 * customer.setUserPassword(user.getUserPassword()); Customer customerOn =
	 * userService.getCustomerInfo(customer);
	 * session.setAttribute("userLogInInfo", customerOn);
	 * System.out.println(customerOn); break; case 2: Seller seller = new
	 * Seller(); seller.setId(user.getId());
	 * seller.setUserPassword(user.getUserPassword()); Seller sellerOn =
	 * userService.getSellerInfo(seller); session.setAttribute("userLogInInfo",
	 * sellerOn); System.out.println(sellerOn); default: System.out.println(
	 * "There is no user information matched with id and password"); }
	 */
}