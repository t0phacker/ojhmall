package ojhmall.service.mypage;

import javax.annotation.Resource;

import ojhmall.dao.mypage.MypageDAO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("mypageService")
public class MypacgeServiceImpl implements MypageService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "mypageDAO")
	private MypageDAO mypageDAO;
}
