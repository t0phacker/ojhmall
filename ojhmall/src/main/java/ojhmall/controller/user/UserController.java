package ojhmall.controller.user;

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
	public ModelAndView openSignInForm()
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/SignUpForm");

		return mv;
	}

	// 구매자 회원가입 화면으로 이동
	@RequestMapping(value = "/user/CustomerSignUpForm.do")
	public ModelAndView goCustomerSign()
			throws Exception {
		ModelAndView mv = new ModelAndView("/user/CustomerSignUpForm");

		return mv;
	}

	// 판매자 회원가입 화면으로 이동
	@RequestMapping(value = "/user/SellerSignUpForm.do")
	public ModelAndView goSellerSign()
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
		userService.setSeller(seller);

		return mv;
	}
	// 로그인 화면 출력
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
			HttpServletRequest request){
		session.setAttribute("userLogInInfo", null);
		ModelAndView mv = new ModelAndView(
				"redirect:http://localhost:8080/ojhmall/");
		return mv;

	}

	// 관리자 회원정보 화면 출력
	@RequestMapping(value = "/user/AdminInfoForm.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showAdminInfoForm(){
		ModelAndView mv = new ModelAndView("/user/AdminInfoForm");
		return mv;
	}

	// 구매자 회원정보 화면 출력
	@RequestMapping(value = "/user/CustomerInfoForm.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showCustomerInfoForm(){
		ModelAndView mv = new ModelAndView("/user/CustomerInfoForm");
		return mv;
	}

	// 판매자 회원정보 화면 출력
	@RequestMapping(value = "/user/SellerInfoForm.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showSellerInfoForm(){
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
}