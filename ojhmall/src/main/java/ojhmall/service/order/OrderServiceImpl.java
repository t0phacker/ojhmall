package ojhmall.service.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ojhmall.dao.order.OrderDAO;
import ojhmall.vo.Cart;
import ojhmall.vo.Order;
import ojhmall.vo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "orderDAO")
	private OrderDAO orderDAO;

	// 주문서 DB에 추가
	@Override
	public void insertOrder(Order order) throws Exception {
		orderDAO.insertOrder(order);
	}

	// 주문서 번호 생성 후 추출
	@Override
	public Order exportOrderNumber(Order order) throws Exception {
		return orderDAO.exportOrderNumber(order);
	}

	// 주문서 번호를 입력하여 주문 정보 입력
	@Override
	public void insertOrderInfo(Order order) throws Exception {
		orderDAO.insertOrderInfo(order);
	}

	// 상품 정보 주문서에 입력
	@Override
	public Order getPrdInfo(Order order) throws Exception {
		Order preOrderInfo = order;
		Order tempOrder = orderDAO.getPrdInfo(order); // 주문번호로 상품정보 추출
		tempOrder.setOrderNumber(preOrderInfo.getOrderNumber());
		tempOrder.setUserNumber(preOrderInfo.getUserNumber());
		tempOrder.setPrice(preOrderInfo.getPrice());
		tempOrder.setCount(preOrderInfo.getCount());
		tempOrder.setDeliveryFee(this.calDelivery(tempOrder.getPrice()));
		return tempOrder;
	}

	// 단일 상품 배송비 계산, 3만원 이하 주문시 배송비 3,000원, 이상 무료
	@Override
	public int calDelivery(int price) throws Exception {
		if (price < 30000) {
			return 3000;
		}
		return 0;
	}

	// 장바구니에서 전체상품 주문시 중복 아이템 병합 후 주문서 생성
	@Override
	public void setItemsIntoOrder(Order order, List<Cart> cartList)
			throws Exception {
		// 장바구니에서 중복 아이템 병합
		for (int item = 0; item < cartList.size() - 1; item++) {
			for (int compared = item+1; compared < cartList.size(); compared++) {
				boolean isSameItem = (cartList.get(item).getPrdNumber() == cartList.get(compared).getPrdNumber());
				if (isSameItem) {
					cartList.get(item).setPrdAmount(cartList.get(item).getPrdAmount()
									+ cartList.get(compared).getPrdAmount());
					cartList.get(item).setCartPrice(cartList.get(item).getCartPrice()
									+ cartList.get(compared).getCartPrice());
					cartList.remove(compared);
				}
			}
		}
		// 주문서에 상품정보 추가
		for (int i = 0; i < cartList.size(); i++) {
			int tempNumber = order.getOrderNumber();
			order.setPrdNumber(cartList.get(i).getPrdNumber());
			order = orderDAO.getPrdInfo(order);
			order.setOrderNumber(tempNumber);
			order.setCount(cartList.get(i).getPrdAmount());
			order.setPrice(cartList.get(i).getCartPrice());
			orderDAO.setItemsIntoOrder(order);
		}
	}
	// 총배송비 계산
	@Override
	public int calTotalPrice(List<Order> orderList) throws Exception {
		int totalPrice = 0;
		for (int t = 0; t < orderList.size(); t++) {
			totalPrice += orderList.get(t).getPrice();
		}
		return totalPrice;
	}
	// 주문리스트 불러오기
	@Override
	public List<Order> getOrderList(Order order) throws Exception {
		return orderDAO.getOrderList(order);
	}
	// 판매자별 주문금액 합산 후 배송비 계산
	@Override
	public List<Order> calTotalDel(List<Order> orderList) throws Exception {
		for (int item = 0; item < orderList.size(); item++) {
			int tempPrice = orderList.get(item).getPrice();
			int tempSeller = orderList.get(item).getUser_userNumber();
			for (int seller = 0; seller < orderList.size(); seller++) {
				if (tempSeller == orderList.get(seller).getUser_userNumber()
						&& item != seller) {
					tempPrice += orderList.get(seller).getPrice();
				}
			}
			if (tempPrice >= 30000) {
				for (int seller = 0; seller < orderList.size(); seller++) {
					if (orderList.get(seller).getUser_userNumber() == tempSeller) {
						orderList.get(seller).setDeliveryFee(0);
					}
				}
			}
			else {
				for (int seller = 0; seller < orderList.size(); seller++) {
					if (orderList.get(seller).getUser_userNumber() == tempSeller) {
						orderList.get(seller).setDeliveryFee(3000);
					}
				}
			}
		}
		return orderList;
	}
	// 장바구니 번호, 상품 수량, 주문 가격 개별 정리 후 orderList에 삽입
	@Override
	public void trimOrderData(String prdNumber, String prdAmount,
			String cartPrice, Order order, List<Cart> cartList) throws Exception {
		
		String[] trimmedPrdNumber = prdNumber.split("@");
		String[] trimmedPrdAmount = prdAmount.split("@");
		String[] trimmedCartPrice = cartPrice.split("@");
		
		List<Order> orderList = new ArrayList<Order>();
		for (int cart = 0; cart < trimmedPrdNumber.length; cart++) {
			Order tempOrder = new Order();
			tempOrder.setPrdNumber(Integer.parseInt(trimmedPrdNumber[cart]));
			tempOrder.setCount(Integer.parseInt(trimmedPrdAmount[cart]));
			tempOrder.setPrice(Integer.parseInt(trimmedCartPrice[cart]));
			orderList.add(tempOrder);
		}
		// 중복 상품 병합
		for (int item = 0; item < orderList.size() - 1; item++) {
			for (int compared = item+1; compared < orderList.size(); compared++) {
				if (orderList.get(item).getPrdNumber() == orderList.get(compared).getPrdNumber()) {
					orderList.get(item).setCount(orderList.get(item).getCount() + orderList.get(compared).getCount());
					orderList.get(item).setPrice(orderList.get(item).getPrice() + orderList.get(compared).getPrice());
					orderList.remove(compared);
				}
			}
		}
		// 주문별 상품 상세 정보 추가
		for (int i = 0; i < orderList.size(); i++) {
			int tempNumber = order.getOrderNumber();
			order.setPrdNumber(orderList.get(i).getPrdNumber());
			order = orderDAO.getPrdInfo(order);
			order.setOrderNumber(tempNumber);
			order.setCount(orderList.get(i).getCount());
			order.setPrice(orderList.get(i).getPrice());
			orderDAO.setItemsIntoOrder(order);
		}
	}
	// 장바구니에서 단일상품 즉시 주문
	@Override
	public Order orderOneCart(Order order, Cart cart) throws Exception {
		Order tempOrder = order;
		order.setPrdNumber(cart.getPrdNumber());
		order = orderDAO.getPrdInfo(order);
		order.setCount(cart.getPrdAmount());
		order.setPrice(cart.getPrdAmount() * order.getPrice());
		order.setOrderNumber(tempOrder.getOrderNumber());
		order.setDeliveryFee(this.calDelivery(order.getPrice()));
		orderDAO.insertOrderInfo(order);
		return order;
	}
	// 주문상태 변경(최초 주문서 생성시 1 -> 주문서에서 무통장 입금 결제시 2, 결제 대기상태)
	@Override
	public void updateOrderStatus(Order order) throws Exception {
		orderDAO.updateOrderStatus(order);
	}
	// 장바구니에서 전체상품 주문시 장바구니 번호를 @을 구분 값으로 병합
	@Override
	public String mergeCartNum(List<Cart> cartList) throws Exception {
		String totalCartNum = "init";
		for (int i = 0; i < cartList.size(); i++) {
			if (totalCartNum == "init") {
				totalCartNum = "";
			}
			else {
				totalCartNum += "@";
			}
			totalCartNum += Integer.toString(cartList.get(i).getCartNumber());
		}
		return totalCartNum;
	}
	// 주문서 삭제시 회원번호로 상품정보 추출
	@Override
	public List<Order> getOrderByUser(User user) throws Exception {
		return orderDAO.getOrderByUser(user);
	}
	// 주문서 삭제
	@Override
	public void deleteOrder(Order order) throws Exception {
		orderDAO.deleteOrder(order);
	}
	// 주문서 배송비 업데이트
	@Override
	public void updateDelAndPrc(List<Order> orderList) throws Exception {
		int tempDelivery = 0;
		int tempPrice = 0;
		Order order = orderList.get(0);
		for (int i = 0; i < orderList.size(); i++) {
			order = orderList.get(i);
			tempDelivery += orderList.get(i).getDeliveryFee();
			tempPrice += orderList.get(i).getPrice();
			order.setDeliveryFee(orderList.get(i).getDeliveryFee());
			orderDAO.updateEachOrder(order);
		}
		order.setTotalDelivery(tempDelivery);
		order.setTotalPrice(tempPrice);
		orderDAO.updateDelAndPrc(order);
	}
	// 결제시 결제창에 주문 가격정보 출력
	@Override
	public Order getOrderPrice(int orderNumber) throws Exception {
		return orderDAO.getOrderPrice(orderNumber);
	}
	// 관리자가 결제정보 확인시 주문상태 변경(3 : 결제완료)
	@Override
	public void updateOrderByPay(List<Order> orderList) throws Exception {
		orderDAO.updateOrderByPay(orderList.get(0));
	}
	// 판매자의 주문정보 불러오기
	@Override
	public List<Order> getOrderForSeller(User user) throws Exception {
		return orderDAO.getOrderForSeller(user);
	}
	// 판매자가 관리자로부터 결제금액 송금 받은 후 주문상태 변경(4 : 배송중)
	@Override
	public void updateOrdered(Order order) throws Exception {
		orderDAO.updateOrdered(order);
	}
}
