package ojhmall.dao.user;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Admin;
import ojhmall.vo.Customer;
import ojhmall.vo.Seller;
import ojhmall.vo.User;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {

	// 유저 기본정보 삽입
	public void insertUserInfo(User user) {
		insert("signUp.insertUserInfo", user);
	}

	// 회원번호 추출
	public int getUserNum(User user) {
		return (Integer) selectOne("signUp.getUserNum", user);
	}

	// 구매자 회원가입
	public void insertCustomerInfo(Customer customer) {
		update("signUp.updateUserInfo", (User) customer);
		insert("signUp.insertCustomerInfo", customer);
	}

	// 판매자 회원가입
	public void insertSellerInfo(Seller seller) {
		update("signUp.updateUser", seller);
		insert("signUp.insertSellerInfo", seller);
	}

	// 로그인
	public User getUserBaseInfo(User user)  {
		return (User) selectOne("logIn.getUserBaseInfo", user);
	}

	public Admin getAdminInfo(User user) {
		Admin adminInfo = (Admin) selectOne("logIn.getAdminInfo", user);
		return adminInfo;
	}

	public Customer getCustomerInfo(User user) {
		Customer customerInfo = (Customer) selectOne("logIn.getCustomerInfo",
				user);
		return customerInfo;
	}

	public Seller getSellerInfo(User user) {
		Seller sellerInfo = (Seller) selectOne("logIn.getSellerInfo", user);
		return sellerInfo;
	}

	// 관리자 회원 정보 변경
	public void updateAdminInfo(Admin admin) {
		update("signUp.updateAdminInfo", admin);
	}

	// 구매자 회원 정보 변경
	public void updateCustomerInfo(Customer customer) {
		update("signUp.updateCustomerInfo", customer);
		update("signUp.updateCustomerExtraInfo", customer);
	}

	// 판매자 회원 정보 변경
	public void updateSellerInfo(Seller seller) {
		update("signUp.updateSellerInfo", seller);
		update("signUp.updateSellerExtraInfo", seller);
	}

	public void deleteUserInfo(User user) {
		update("signUp.deleteUserInfo", user);
	}

	public void deleteAdminInfo(User user) {
		update("signUp.deleteAdminInfo", user);
	}

	public void deleteCustomerInfo(User user) {
		update("signUp.deleteCustomerInfo", user);
	}

	public void deleteSellerInfo(User user) {
		update("signUp.deleteSellerInfo", user);
	}

	public Admin getAdminAccNum(User admin) {
		return (Admin) selectOne("logIn.getAdminAccNum", admin);
	}

	public String checkId(String id) {
		return (String) selectOne("signUp.checkId", id);
	}

}