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
	public User getUserWholeInfo(User user) {
		return null;
	}

}