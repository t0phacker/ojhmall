package ojhmall.service.user;

import ojhmall.vo.User;
import ojhmall.vo.UserType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userInfoServiceFactory")
public class UserInfoServiceFactory {
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	@Qualifier("adminInfoService")
	private UserInfoService adminInfoService;

	@Autowired
	@Qualifier("customerInfoService")
	private UserInfoService customerInfoService;

	@Autowired
	@Qualifier("sellerInfoService")
	private UserInfoService sellerInfoService;

	public User getUserInfoService(User user) throws Exception {
		
		UserType userType = user.getUserType();
		if (UserType.ADMIN == userType) {
			log.debug("admin enter");
			return adminInfoService.getUserAllInfo(user);
		}

		if (UserType.SELLER == userType) {
			log.debug("seller enter");
			return sellerInfoService.getUserAllInfo(user);
		}

		log.debug("customer enter");
		return customerInfoService.getUserAllInfo(user);
	}
	
	public void deleteUserInfo(User user) throws Exception {
		UserType userType = user.getUserType();
		
		if (UserType.ADMIN == userType) {
			adminInfoService.deleteUserInfo(user);
		}

		if (UserType.SELLER == userType) {
			sellerInfoService.deleteUserInfo(user);
		}

		log.debug("customer enter");
		customerInfoService.deleteUserInfo(user);
	}
}
