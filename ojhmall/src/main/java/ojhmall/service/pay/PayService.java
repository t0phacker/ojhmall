package ojhmall.service.pay;

import java.util.List;

import ojhmall.vo.Order;
import ojhmall.vo.Payment;

public interface PayService {

	void insertPay(Payment pay) throws Exception;

	List<Payment> getPayList() throws Exception;

	void updatePayStatus(Payment pay) throws Exception;

	List<Payment> getPaidList() throws Exception;

	List<Order> getOrderFromPay(Payment pay) throws Exception;

	void updatePay(Payment pay) throws Exception;

}
