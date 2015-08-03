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
	
	User userOn = new User(); // 유저 공통 클래스 변수
	Admin adminOn = new Admin(); // 관리자 클래스 변수
	Customer customerOn = new Customer(); // 구매자 클래스 변수
	Seller sellerOn = new Seller(); // 판매자 클래스 변수
	
	// 구매자 회원가입
	@Override
	public void setCustomer(Customer customer) throws Exception {
		userDAO.insertUser(customer); // user 테이블에 회원 기본정보 삽입 
		customer.setUserNumber(userDAO.selectUserNum(customer)); // user 테이블에서 회원번호 추출
		userDAO.setCustomer(customer); // user 테이블에 회원번호로 생성자, 수정자 정보 입력
	}

	// 판매자 회원가입
	@Override
	public void setSeller(Seller seller) throws Exception {
		userDAO.insertUser(seller); // user 테이블에 회원 기본정보 삽입
		seller.setUserNumber(userDAO.selectUserNum(seller)); // user 테이블에서 회원번호 추출
		userDAO.setSeller(seller);
	}
	// 로그인
	// 의미없는 Exception을 던지고 있음
	// enum 초기화 ADMIN(1)
	@Override
	public User findByIdAndPw(User user) throws Exception {
		//ID와 Password로 유저 기본 정보 조회, userType 변수 추출
		User userBaseInfo = userDAO.getUserBaseInfo(user);
		userBaseInfo.setUserType();
		
		UserInfoService userInfoService = UserInfoServiceFactory.getUserInfoService(userBaseInfo);
		return userInfoService.getUserAllInfo(userBaseInfo);
	}
	//관리자 회원정보 변경
	@Override
	public Admin updateAdmin(Admin admin) throws Exception {
		userDAO.updateAdmin(admin);
		adminOn = userDAO.getAdminInfo(admin);
		return adminOn;
	}
	// 구매자 회원정보 변경
	@Override
	public Customer updateCustomer(Customer customer) throws Exception {
		userDAO.updateCustomer(customer);
		customerOn = userDAO.getCustomerInfo(customer);
		return customer;
	}
	// 판매자 회원정보 변경
	@Override
	public Seller updatedSeller(Seller seller) throws Exception {
		userDAO.updateSeller(seller);
		sellerOn = userDAO.getSellerInfo(seller);
		return sellerOn;
	}
	// 회원정보 삭제
	@Override
	public void removeUser(User user) throws Exception {
		//회원 id에서 @ 제거 후 저장
		String[] tempId = user.getId().split("@");
		String discardedId = tempId[0] + tempId[1];
		user.setId(discardedId);
		userDAO.removeUser(user);
		switch (user.getUserType()) {
		case ADMIN:
			userDAO.removeAdmin(user);
			break;
		case CUSTOMER:
			userDAO.removeCustomer(user);
			break;
		case SELLER:
			userDAO.removeSeller(user);
			break;
			default:
				System.out.println("no matching data");
		}
	}

	@Override
	public User getAdminAcc(User admin) throws Exception {
		return userDAO.getAdminAcc(admin);
	}
}