package ojhmall.service.user;

import javax.annotation.Resource;

import ojhmall.dao.user.UserDAO;
import ojhmall.vo.User;

import org.springframework.stereotype.Service;

@Service("adminInfoService")
public class AdminInfoServiceImpl implements UserInfoService {
	
	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Override
	public User getUserAllInfo(User user) throws Exception {
		return userDAO.getAdminInfo(user);
	}
}
