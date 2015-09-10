package ojhmall.service.user;

import ojhmall.vo.User;

public interface UserInfoService {

	public User getUserAllInfo(User user) throws Exception;
	
	public void deleteUserInfo(User user) throws Exception;
}
