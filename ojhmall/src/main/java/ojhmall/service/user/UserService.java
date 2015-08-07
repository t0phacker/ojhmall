package ojhmall.service.user;

import ojhmall.vo.Admin;
import ojhmall.vo.Customer;
import ojhmall.vo.Seller;
import ojhmall.vo.User;

public interface UserService {
	
	void insertCustomerInfo(Customer customer);
	
	void insertSellerInfo(Seller seller);
 
	public User checkIdAndPw(User user) throws Exception;
	
	public User updateAdminInfo(Admin admin);

	public Customer updateCustomerInfo(Customer customer);

	public Seller updatedSellerInfo(Seller seller);

	void deleteUserInfo(User user);

	User getAdminAccNum(User admin) throws Exception;

}