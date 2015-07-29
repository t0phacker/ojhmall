package ojhmall.dao.order;

import java.util.List;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Cart;
import ojhmall.vo.Order;
import ojhmall.vo.User;

import org.springframework.stereotype.Repository;

@Repository("orderDAO")
public class OrderDAO extends AbstractDAO {
	// 주문서 DB에 추가
	public void insertOrder(Order order) {
		insert("Order.insertOrder", order);
	}
	// 주문서 번호 생성 후 추출
	public Order exportOrderNumber(Order order) {
		return (Order) selectOne("Order.exportOrderNumber", order);
	}
	// 상품 번호로 상품 정보 추출
	public Order getPrdInfo(Order order) {
		return (Order) selectOne("Order.getPrdInfo", order);
	}
	// 주문서 번호를 입력하여 주문 정보 입력
	public void insertOrderInfo(Order order) {
		insert("Order.insertOrderInfo", order);
	}
	public void setItemsIntoOrder(Order order) {
		insert("Order.setItemsIntoOrder", order);
	}
	@SuppressWarnings("unchecked")
	public List<Order> getOrderList(Order order) {
		return selectList("Order.getOrderList", order);
	}
	public void updateOrderStatus(Order order) {
		update("Order.updateOrderStatus", order);
	}
	@SuppressWarnings("unchecked")
	public List<Order> getOrderByUser(User user) {
		return selectList("Order.getOrderByUser", user);
	}
	public void deleteOrder(Order order) {
		delete("Order.deleteOrder", order);
	}
	public void updateDelAndPrc(Order order) {
		update("Order.updateDelAndPrc", order);
	}
	public void updateEachOrder(Order order) {
		update("Order.updateEachOrder", order);
	}
	public Order getOrderPrice(int orderNumber) {
		return (Order) selectOne("Order.getOrderPrice", orderNumber);
	}
	public void updateOrderByPay(Order order) {
		update("Order.updateOrderByPay", order);
	}
	public List<Order> getOrderForSeller(User user) {
		return selectList("Order.getOrderForSeller", user);
	}
	public void updateOrdered(Order order) {
		update("Order.updateOrdered", order);
	}

}
