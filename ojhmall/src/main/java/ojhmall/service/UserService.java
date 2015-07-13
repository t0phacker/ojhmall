package ojhmall.service;

import java.util.Map;

import ojhmall.vo.Admin;
import ojhmall.vo.Customer;
import ojhmall.vo.Seller;
import ojhmall.vo.User;

public interface UserService {

    //
	void setCustomer(Customer customer) throws Exception;
	
	Map<String, Object> selectMaxNum(Map<String, Object> map) throws Exception;
	//
	void setSeller(Seller seller) throws Exception;
 
	public User findByIdAndPw(User user) throws Exception;
	
	public User updateAdmin(Admin admin) throws Exception;

	Customer updateCustomer(Customer customer) throws Exception;

	Seller updatedSeller(Seller seller) throws Exception;

	void removeUser(User user) throws Exception;
}