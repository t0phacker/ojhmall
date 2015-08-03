package ojhmall.service.user;

import javax.annotation.Resource;

import ojhmall.vo.User;
import ojhmall.vo.UserType;

import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceFactory {

	@Resource(name = "adminInfoService")
	private static UserInfoService adminInfoService;

	@Resource(name = "customerInfoService")
	private static UserInfoService cusUserInfoService;

	@Resource(name = "sellerInfoService")
	private static UserInfoService sellerInfoService;

	public static UserInfoService getUserInfoService(User user) {
		UserType userType = user.getUserType();

		if (UserType.ADMIN == userType) {
			return adminInfoService;
		}

		if (UserType.SELLER == userType) {
			return sellerInfoService;
		}

		return cusUserInfoService;
	}
}
