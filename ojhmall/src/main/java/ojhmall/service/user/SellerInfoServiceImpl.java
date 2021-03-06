package ojhmall.service.user;

import javax.annotation.Resource;

import ojhmall.dao.user.UserDAO;
import ojhmall.vo.User;

import org.springframework.stereotype.Service;

@Service("sellerInfoService")
public class SellerInfoServiceImpl implements UserInfoService {
	
	@Resource(name = "userDAO")
	UserDAO userDAO;
	@Override
	public User getUserAllInfo(User user) throws Exception {
		return userDAO.getSellerInfo(user);
	}
	@Override
	public void deleteUserInfo(User user) throws Exception {
		userDAO.deleteSellerInfo(user);
	}

}
