package ojhmall.service.cart;

import java.util.List;

import ojhmall.vo.Cart;

public interface CartService {

	void addCart(Cart cart) throws Exception;

	List<Cart> loadCartList(Cart cart) throws Exception;
	
	int calTotalCartPrice(List<Cart> cartList) throws Exception;

}