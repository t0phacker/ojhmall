package ojhmall.service.cart;

import java.util.List;

import javax.annotation.Resource;

import ojhmall.dao.cart.CartDAO;
import ojhmall.vo.Cart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "cartDAO")
	private CartDAO cartDAO;
	// 장바구니 추가
	@Override
	public void addCart(Cart cart) throws Exception {
		cartDAO.addCart(cart);
	}
	// 장바구니 불러오기
	@Override
	public List<Cart> loadCartList(Cart cart) throws Exception {
		List<Cart> cartList = cartDAO.loadCartList(cart);
		int cartCount = 1;
		for (int i = 0; i < cartList.size(); i++) {
        	if (cartList.get(i).getCartPrice() < 30000) {
        		cartList.get(i).setDeliveryFee(3000);
        	}
        }
		for (int a = 0; a < cartList.size()-1; a++) {
			if (cartList.get(a).getUser_userNumber() == cartList.get(a+1).getUser_userNumber())
				cartCount++;
		}
		int[] deliveryBill = new int[cartCount];
		deliveryBill[0] = 0;
		int positionCheck = 0;
		for (int b = 0; b < cartCount; b++) {
			deliveryBill[b] = cartList.get(positionCheck).getCartPrice();
			for (int c = positionCheck; c < cartList.size()-1; c++) {
				if (cartList.get(c).getUser_userNumber() == cartList.get(c+1).getUser_userNumber())
					{
					deliveryBill[b] += cartList.get(c+1).getCartPrice();
					}
				else
					positionCheck = c+1;
			}
		}
		for (int d = 0; d < cartCount; d++) {
			if (deliveryBill[d] >= 30000) {
				for (int e = d; e < cartList.size()-1; e++){
					if (cartList.get(e).getUser_userNumber() == cartList.get(e+1).getUser_userNumber()) {
						cartList.get(e).setDeliveryFee(0);
						cartList.get(e+1).setDeliveryFee(0);
					}
				}
			}
		}
		return cartList;
	}
	// 장바구니 총 가격
	public int calTotalCartPrice(List<Cart> cartList) throws Exception {
		int totalCartPrice = 0;
		for (int i = 0; i < cartList.size(); i++)
			totalCartPrice += cartList.get(i).getCartPrice();
		return totalCartPrice;
	}
	@Override
	public int calTotalDeliveryFee(List<Cart> cartList) throws Exception {
		int totalDeliveryFee = 0;
		for (int i = 0; i < cartList.size(); i++)
			totalDeliveryFee += cartList.get(i).getDeliveryFee();
		return totalDeliveryFee;
	}
	@Override
	public void removeCart(int cartNumber) throws Exception {
		cartDAO.removeCart(cartNumber);
	}
}
