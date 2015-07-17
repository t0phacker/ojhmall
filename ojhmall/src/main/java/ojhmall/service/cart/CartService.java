package ojhmall.service.cart;

import java.util.List;

import ojhmall.vo.Cart;

public interface CartService {

	void addCart(Cart cart) throws Exception;

	List<Cart> loadCartList(Cart cart) throws Exception;
	
	int calTotalCartPrice(List<Cart> cartList) throws Exception;

	int calTotalDeliveryFee(List<Cart> cartList) throws Exception;

	void removeCart(int cartNumber) throws Exception;

	Cart selectCart(int cartNumber) throws Exception;

	void updateCart(Cart cart) throws Exception;

}
