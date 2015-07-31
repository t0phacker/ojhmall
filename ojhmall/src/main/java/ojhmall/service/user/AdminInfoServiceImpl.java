package ojhmall.service.user;

import javax.annotation.Resource;

import ojhmall.dao.user.UserDAO;
import ojhmall.vo.User;

import org.springframework.stereotype.Service;

@Service("userInfoService")
public class AdminInfoServiceImpl implements UserInfoService {
	
	@Resource(name = "userDAO")
	UserDAO userDAO;

	@Override
	public User getUserWholeInfo(User user) throws Exception {
		System.out.println("admin");
		return userDAO.getAdminInfo(user);
	}
}
