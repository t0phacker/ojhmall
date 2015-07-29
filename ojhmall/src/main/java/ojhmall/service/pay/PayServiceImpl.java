package ojhmall.service.pay;

import java.util.List;

import javax.annotation.Resource;

import ojhmall.dao.pay.PayDAO;
import ojhmall.vo.Order;
import ojhmall.vo.Payment;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("payService")
public class PayServiceImpl implements PayService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "payDAO")
	private PayDAO payDAO;
	// 결제서 작성
	@Override
	public void insertPay(Payment pay) throws Exception {
		payDAO.insertPay(pay);
	}
	// 관리자가 입금내역을 관리하기 위한 결제정보 목록 불러오기 from MypageController
	@Override
	public List<Payment> getPayList() throws Exception {
		return payDAO.getPayList();
	}
	// 관리자가 결제상태 변경(결제대기 -> 결제완료) from MypageController
	@Override
	public void updatePayStatus(Payment pay) throws Exception {
		payDAO.updatePayStatus(pay);
	}
	// 결제완료인 결제정보 목록을 관리자 마이페이지에 출력 from MypageController
	@Override
	public List<Payment> getPaidList() throws Exception {
		return payDAO.getPaidList();
	}
	// 관리자에게 주문정보 출력
	@Override
	public List<Order> getOrderFromPay(Payment pay) throws Exception {
		return payDAO.getOrderFromPay(pay);
	}
	// 관리자 결제확인(결재상태 : 1 -> 2)
	@Override
	public void updatePay(Payment pay) throws Exception {
		payDAO.updatePay(pay);
	}
}
