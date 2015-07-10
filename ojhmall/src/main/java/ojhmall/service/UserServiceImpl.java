package ojhmall.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import ojhmall.dao.UserDAO;
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
	
	User userOn = new User();
	Admin adminOn = new Admin();
	Customer customerOn = new Customer();
	Seller sellerOn = new Seller();
	
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		User resultUserLogIn = userDAO.findByIdAndPw(user);
		switch (resultUserLogIn.getUserType()) {
		case 0://줄이기 유저 객체 상속, 
			Admin admin = new Admin();
			admin.setId(user.getId());
			admin.setUserPassword(user.getUserPassword());
			adminOn = userDAO.getAdminInfo(admin);
			return adminOn;
		case 1:
			Customer customer = new Customer();
			customer.setId(user.getId());
			customer.setUserPassword(user.getUserPassword());
			customerOn = userDAO.getCustomerInfo(customer);
			System.out.println(customerOn);
			return customerOn;
		case 2:
			Seller seller = new Seller();
			seller.setId(user.getId());
			seller.setUserPassword(user.getUserPassword());
			sellerOn = userDAO.getSellerInfo(seller);
			System.out.println(sellerOn);
			return sellerOn;
		default:
			System.out
					.println("There is no user information matched with id and password");
			return null;
		}
		// return resultUserLogIn;
	}
	//관리자 회원정보 변경
	@Override
	public User updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
		switch (user.getUserType()) {
		case 0:
			userDAO.updateUser(user);
			Admin admin = new Admin();
			admin.setId(user.getId());
			admin.setUserPassword(user.getUserPassword());
			admin.setUserNumber(user.getUserNumber());
			adminOn = userDAO.getAdminInfo(admin);
			System.out.println("admin" + adminOn.getUserName());
			return adminOn;
		case 1:
			userDAO.updateUser(user);
			/*Customer customer = new Customer();
			customer.setId(user.getId());
			customer.setUserPassword(user.getUserPassword());
			customer.setUserNumber(user.getUserNumber());
			userDAO.updateCustomer(customer);
			System.out.println("customer updated : " + customer.getPhNumber());*/
			//customerOn = userDAO.getCustomerInfo(customer);
			
			return user;
		case 2:
			Seller seller = new Seller();
			seller.setUserNumber(user.getUserNumber());
			userDAO.updateSeller(seller);
			System.out.println("customer updated : " + seller.getPhNumber());
			return seller;
		default:
			System.out.println("No matching data");
			return null;
		}
	}
	/*
	 * @Override public Admin getAdminInfo(Admin admin) throws Exception { Admin
	 * adminOn = userDAO.getAdminInfo(admin);
	 * System.out.println("adminservice : " + adminOn.getUserName()); return
	 * adminOn; }
	 * 
	 * @Override public Customer getCustomerInfo(Customer customer) throws
	 * Exception { Customer customerOn = userDAO.getCustomerInfo(customer);
	 * return customerOn; }
	 * 
	 * @Override public Seller getSellerInfo(Seller seller) throws Exception {
	 * Seller sellerOn = userDAO.getSellerInfo(seller); return sellerOn; }
	 * 
	 * @Override public User getUserInfo(User user) throws Exception { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public Customer updateCustomer(Customer customer) throws Exception {
		userDAO.updateCustomer(customer);
		System.out.println("customer updated : " + customer.getPhNumber());
		return customer;
	}
}