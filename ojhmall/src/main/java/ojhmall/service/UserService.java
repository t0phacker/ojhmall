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
	
	public User updateUser(User user) throws Exception;
	/*
	Admin getAdminInfo(Admin adminOn) throws Exception;

	Customer getCustomerInfo(Customer customer) throws Exception;

	Seller getSellerInfo(Seller seller) throws Exception;
	
	User getUserInfo(User user) throws Exception;*/

	Customer updateCustomer(Customer customer) throws Exception;
}

