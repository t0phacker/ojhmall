package ojhmall.service.user;

import javax.annotation.Resource;

import ojhmall.dao.user.UserDAO;
import ojhmall.vo.Admin;
import ojhmall.vo.Customer;
import ojhmall.vo.Seller;
import ojhmall.vo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "userDAO")
	private UserDAO userDAO;
	@Resource(name = "userInfoServiceFactory")
	private UserInfoServiceFactory userInfoServiceFactory;
	
	User userOn = new User(); // 유저 공통 클래스 변수
	Admin adminOn = new Admin(); // 관리자 클래스 변수
	Customer customerOn = new Customer(); // 구매자 클래스 변수
	Seller sellerOn = new Seller(); // 판매자 클래스 변수
	
	// 구매자 회원가입
	@Override
	public void insertCustomerInfo(Customer customer) {
		userDAO.insertUserInfo(customer); // user 테이블에 회원 기본정보 삽입 
		customer.setUserNumber(userDAO.getUserNum(customer)); // user 테이블에서 회원번호 추출
		userDAO.insertCustomerInfo(customer); // user 테이블에 회원번호로 생성자, 수정자 정보 입력
	}

	// 판매자 회원가입
	@Override
	public void insertSellerInfo(Seller seller) {
		userDAO.insertUserInfo(seller); // user 테이블에 회원 기본정보 삽입
		seller.setUserNumber(userDAO.getUserNum(seller)); // user 테이블에서 회원번호 추출
		userDAO.insertSellerInfo(seller);
	}
	// 로그인
	// 의미없는 Exception을 던지고 있음
	@Override
	public User checkIdAndPw(User user) throws Exception {
		User userBaseInfo = userDAO.getUserBaseInfo(user);		
		return userInfoServiceFactory.getUserInfoService(userBaseInfo);
	}
	//관리자 회원정보 변경
	@Override
	public Admin updateAdminInfo(Admin admin) {
		userDAO.updateAdminInfo(admin);
		adminOn = userDAO.getAdminInfo(admin);
		return adminOn;
	}
	// 구매자 회원정보 변경
	@Override
	public Customer updateCustomerInfo(Customer customer) {
		userDAO.updateCustomerInfo(customer);
		customerOn = userDAO.getCustomerInfo(customer);
		return customerOn;
	}
	// 판매자 회원정보 변경
	@Override
	public Seller updatedSellerInfo(Seller seller) {
		userDAO.updateSellerInfo(seller);
		sellerOn = userDAO.getSellerInfo(seller);
		return sellerOn;
	}
	// 회원정보 삭제
	@Override
	public void deleteUserInfo(User user) throws Exception {
		//회원 id에서 @ 제거 후 저장
		String[] tempId = user.getId().split("@");
		String discardedId = tempId[0] + tempId[1];
		user.setId(discardedId);
		userDAO.deleteUserInfo(user);
		userInfoServiceFactory.deleteUserInfo(user);
	}
	// 관리자 계좌번호 추출
	@Override
	public User getAdminAccNum(User admin) {
		return userDAO.getAdminAccNum(admin);
	}
}