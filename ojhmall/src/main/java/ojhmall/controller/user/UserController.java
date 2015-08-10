package ojhmall.controller.user;

import javax.annotation.Resource;
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

@Controller("/user")
public class UserController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "userService")
	private UserService userService;

	// 회원 가입 선택 화면
	@RequestMapping(value = "/user/SiginUpForm.do")
	public ModelAndView openSignInForm() {
		ModelAndView mv = new ModelAndView("/user/SignUpForm");

		return mv;
	}

	// 구매자 회원가입 화면으로 이동
	@RequestMapping(value = "/user/CustomerSignUpForm.do")
	public ModelAndView showCustomerSign() {
		ModelAndView mv = new ModelAndView("/user/CustomerSignUpForm");

		return mv;
	}

	// 판매자 회원가입 화면으로 이동
	@RequestMapping(value = "/user/SellerSignUpForm.do")
	public ModelAndView showSellerSign() {
		ModelAndView mv = new ModelAndView("/user/SellerSignUpForm");

		return mv;
	}

	// 구매자 회원가입
	@RequestMapping(value = "/user/insertCustomer.do", method = RequestMethod.POST)
	public ModelAndView insertCustomerInfo(Customer customer) {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		userService.insertCustomerInfo(customer);

		return mv;
	}

	// 판매자 회원가입
	@RequestMapping(value = "/user/insertSeller.do", method = RequestMethod.POST)
	public ModelAndView insertSellerInfo(Seller seller) {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		userService.insertSellerInfo(seller);

		return mv;
	}

	// 로그인 화면 출력
	@RequestMapping(value = "user/LogInForm.do")
	public ModelAndView showLogInForm() {
		ModelAndView mv = new ModelAndView("/user/LogInForm");

		return mv;
	}

	// 로그인 : 데이터베이스 조회
	@RequestMapping(value = "/user/insertLogInInfo.do", method = RequestMethod.POST)
	public ModelAndView logIn(User user, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");

		try {
			User userLogCheck = userService.checkIdAndPw(user);
			if (userLogCheck != null) {
				session.setAttribute("userLogInInfo", userLogCheck);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + " : exception");
			return mv;
		}

		return mv;
	}

	// 로그아웃
	@RequestMapping(value = "logOut.do", method = RequestMethod.GET)
	public ModelAndView logOut(HttpSession session) {
		session.setAttribute("userLogInInfo", null);
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		return mv;

	}

	// 관리자 회원정보 화면 출력
	@RequestMapping(value = "/user/AdminInfoForm.do", method = RequestMethod.POST)
	public ModelAndView showAdminInfoForm() {
		ModelAndView mv = new ModelAndView("/user/AdminInfoForm");
		return mv;
	}

	// 구매자 회원정보 화면 출력
	@RequestMapping(value = "/user/CustomerInfoForm.do", method = RequestMethod.POST)
	public ModelAndView showCustomerInfoForm() {
		ModelAndView mv = new ModelAndView("/user/CustomerInfoForm");
		return mv;
	}

	// 판매자 회원정보 화면 출력
	@RequestMapping(value = "/user/SellerInfoForm.do", method = RequestMethod.POST)
	public ModelAndView showSellerInfoForm() {
		ModelAndView mv = new ModelAndView("/user/SellerInfoForm");
		return mv;
	}

	// 관리자 회원정보 변경
	@RequestMapping(value = "/user/changeAdminInfo.do", method = RequestMethod.POST)
	public ModelAndView updateAdminInfo(Admin admin, HttpSession session) {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		session.setAttribute("userLogInInfo",
				userService.updateAdminInfo(admin));
		return mv;
	}

	// 구매자 회원정보 변경
	@RequestMapping(value = "/user/changeCustomerInfo.do", method = RequestMethod.POST)
	public ModelAndView updateCustomerInfo(Customer customer,
			HttpSession session) {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		session.setAttribute("userLogInInfo",
				userService.updateCustomerInfo(customer));
		return mv;
	}

	// 판매자 회원정보 변경
	@RequestMapping(value = "/user/changeSellerInfo.do", method = RequestMethod.POST)
	public ModelAndView updateSellerInfo(Seller seller, HttpSession session) {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		session.setAttribute("userLogInInfo",
				userService.updatedSellerInfo(seller));
		return mv;
	}

	// 메인으로 복귀
	@RequestMapping(value = "/user/backToMain.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backToMain() {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall");
		return mv;
	}

	// 회원정보 삭제
	@RequestMapping(value = "/user/removeUserInfo.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView deleteUserInfo(User user, HttpSession session) {
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		userService.deleteUserInfo(user);
		session.setAttribute("userLogInInfo", null);
		return mv;
	}
}