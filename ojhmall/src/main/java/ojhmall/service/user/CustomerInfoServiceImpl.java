package ojhmall.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ojhmall.dao.user.UserDAO;
import ojhmall.vo.User;

@Service("customerInfoService")
public class CustomerInfoServiceImpl implements UserInfoService {
	@Resource(name = "userDAO")
	UserDAO userDAO;

	@Override
	public User getUserAllInfo(User user) throws Exception {
		return userDAO.getCustomerInfo(user);
	}

	@Override
	public void deleteUserInfo(User user) throws Exception {
		userDAO.deleteCustomerInfo(user);;
	}

}
