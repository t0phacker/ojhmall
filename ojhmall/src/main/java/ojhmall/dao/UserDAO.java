package ojhmall.dao;

import java.util.Map;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Admin;
import ojhmall.vo.Customer;
import ojhmall.vo.Seller;
import ojhmall.vo.User;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {

	// 마지막 회원번호 추출
	public Map<String, Object> selectMaxNum(Map<String, Object> map)
			throws Exception {
		return (Map<String, Object>) selectOne("signUp.selectMaxNum", map);
	}

	// 구매자 회원가입
	public void setCustomer(Customer customer) throws Exception {

		insert("signUp.insertUser", customer);
		update("signUp.updateUser", customer);
		insert("signUp.insertCustomer", customer);
	}

	// 판매자 회원가입
	public void setSeller(Seller seller) throws Exception {

		insert("signUp.insertUser2", seller);
		update("signUp.updateUser2", seller);
		insert("signUp.insertSeller", seller);
	}
	//로그인
	public User findByIdAndPw(User user) throws Exception {
		User LogInInfo = (User) selectOne("logIn.findByIdAndPw", user);
		return LogInInfo;
	}

	public Admin getAdminInfo(Admin admin) throws Exception {
		Admin adminInfo = (Admin) selectOne("logIn.getAdminInfo", admin);
		return adminInfo;
	}

	public Customer getCustomerInfo(Customer customer) throws Exception {
		Customer customerInfo = (Customer) selectOne("logIn.getCustomerInfo", customer);
		return customerInfo;
	}

	public Seller getSellerInfo(Seller seller) throws Exception {
		Seller sellerInfo = (Seller) selectOne("logIn.getSellerInfo", seller);
		return sellerInfo;
	}
	//회원 정보 변경
	public void updateUser(User user) throws Exception {
		update("signUp.update", user);
	}
	
	public void updateCustomer(Customer customer) throws Exception {
		update("signUp.updateCustomer", customer);
		update("signUp.updateCustomerMoreInfo", customer);
	}

	public void updateSeller(Seller seller) throws Exception {
		update("signUp.updateSeller", seller);
		update("signUp.updateSellerMoreInfo", seller);
	}
}
