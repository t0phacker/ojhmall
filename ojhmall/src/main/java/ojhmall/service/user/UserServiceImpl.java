package ojhmall.service.user;

import java.util.HashMap;
import java.util.Map;

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
		Map<String, Object> map = new HashMap<String, Object>();
		map = userDAO.selectMaxNum(map);

		customer.setUserNumber((Integer) map.get("UserNumber") + 1);

		userDAO.setCustomer(customer);

	}

	// 회원번호 끝자리 추출
	@Override
	public Map<String, Object> selectMaxNum(Map<String, Object> map)
			throws Exception {
		return userDAO.selectMaxNum(map);
	}

	// 판매자 회원가입
	@Override
	public void setSeller(Seller seller) throws Exception {
		userDAO.setSeller(seller);
	}

	// 로그인
	@Override
	public User findByIdAndPw(User user) throws Exception {
		//ID와 Password로 유저 기본 정보 조회, userType 변수 추출
		User userBaseInfo = userDAO.getUserBaseInfo(user);
		switch (userBaseInfo.getUserType()) {
		case 0://줄이기 유저 객체 상속, 
			return userDAO.getAdminInfo(userBaseInfo);
		case 1:
			return userDAO.getCustomerInfo(userBaseInfo);
		case 2:
			return userDAO.getSellerInfo(userBaseInfo);
		default:
			System.out.println("There is no user information matched with id and password");
			return null;
		}
		// return resultUserLogIn;
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
		case 0:
			userDAO.removeAdmin(user);
			break;
		case 1:
			userDAO.removeCustomer(user);
			break;
		case 2:
			userDAO.removeSeller(user);
			break;
			default:
				System.out.println("no matching data");
		}
	}
}