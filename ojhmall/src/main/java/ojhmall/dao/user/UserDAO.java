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
	public void insertUser(User user) {
		insert("signUp.insertUser", user);
	}

	// 회원번호 추출
	public int selectUserNum(User user) {
		return (Integer) selectOne("signUp.selectUserNum", user);
	}

	// 구매자 회원가입
	public void setCustomer(Customer customer) throws Exception {
		update("signUp.updateUser", (User) customer);
		insert("signUp.insertCustomer", customer);
	}

	// 판매자 회원가입
	public void setSeller(Seller seller) throws Exception {
		update("signUp.updateUser2", seller);
		insert("signUp.insertSeller", seller);
	}

	// 로그인
	public User getUserBaseInfo(User user)  {
		return (User) selectOne("logIn.findByIdAndPw", user);
	}

	public Admin getAdminInfo(User user) throws Exception {
		Admin adminInfo = (Admin) selectOne("logIn.getAdminInfo", user);
		return adminInfo;
	}

	public Customer getCustomerInfo(User user) throws Exception {
		Customer customerInfo = (Customer) selectOne("logIn.getCustomerInfo",
				user);
		return customerInfo;
	}

	public Seller getSellerInfo(User user) throws Exception {
		Seller sellerInfo = (Seller) selectOne("logIn.getSellerInfo", user);
		return sellerInfo;
	}

	// 관리자 회원 정보 변경
	public void updateAdmin(Admin admin) throws Exception {
		update("signUp.updateAdmin", admin);
	}

	// 구매자 회원 정보 변경
	public void updateCustomer(Customer customer) throws Exception {
		update("signUp.updateCustomer", customer);
		update("signUp.updateCustomerMoreInfo", customer);
	}

	// 판매자 회원 정보 변경
	public void updateSeller(Seller seller) throws Exception {
		update("signUp.updateSeller", seller);
		update("signUp.updateSellerMoreInfo", seller);
	}

	public void removeUser(User user) {
		update("signUp.removeUser", user);
	}

	public void removeAdmin(User user) {
		update("signUp.removeAdmin", user);
	}

	public void removeCustomer(User user) {
		update("signUp.removeCustomer", user);
	}

	public void removeSeller(User user) {
		update("signUp.removeSeller", user);
	}

	public Admin getAdminAcc(User admin) {
		return (Admin) selectOne("logIn.getAdminAcc", admin);
	}

	public String checkId(String id) {
		return (String) selectOne("signUp.checkId", id);
	}

}