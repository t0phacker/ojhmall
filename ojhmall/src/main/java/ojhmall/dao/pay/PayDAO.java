package ojhmall.dao.pay;

import java.util.List;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Order;
import ojhmall.vo.Payment;

import org.springframework.stereotype.Repository;

@Repository("payDAO")
public class PayDAO extends AbstractDAO {

	public void insertPay(Payment pay) {
		insert("Pay.insertPay", pay);
	}
	// 결제확인 전 리스트
	public List<Payment> getPayList() {
		return selectList("Pay.getPayList");
	}

	public void updatePayStatus(Payment pay) {
		update("Pay.updatePayStatus", pay);
	}
	// 결제확인된 리스트
	public List<Payment> getPaidList() {
		return selectList("Pay.getPaidList");
	}
	public List<Order> getOrderFromPay(Payment pay) {
		return selectList("Pay.getOrderFromPay", pay);
	}
	public void updatePay(Payment pay) {
		update("Pay.updatePay", pay);
	}

}
