package ojhmall.service.order;

import java.util.List;

import ojhmall.vo.Cart;
import ojhmall.vo.Order;
import ojhmall.vo.User;

public interface OrderService {

	void insertOrder(Order order) throws Exception;

	Order exportOrderNumber(Order order) throws Exception;

	void insertOrderInfo(Order order) throws Exception;

	Order getPrdInfo(Order order) throws Exception;
	
	int calDelivery(int price) throws Exception;

	void setItemsIntoOrder(Order order, List<Cart> cartList) throws Exception;

	int calTotalPrice(List<Order> orderList) throws Exception;

	List<Order> getOrderList(Order order) throws Exception;

	List<Order> calTotalDel(List<Order> orderList) throws Exception;

	void trimOrderData(String prdNumber, String prdAmount,
			String cartPrice, Order order, List<Cart> cartList) throws Exception;

	Order orderOneCart(Order order, Cart cart) throws Exception;

	void updateOrderStatus(Order order) throws Exception;

	String mergeCartNum(List<Cart> cartList) throws Exception;

	List<Order> getOrderByUser(User user) throws Exception;

	void deleteOrder(Order order) throws Exception;

	void updateDelAndPrc(List<Order> orderList) throws Exception;

	Order getOrderPrice(int orderNumber) throws Exception;

	void updateOrderByPay(List<Order> orderList) throws Exception;

	List<Order> getOrderForSeller(User user) throws Exception;

	void updateOrdered(Order order) throws Exception;

}
