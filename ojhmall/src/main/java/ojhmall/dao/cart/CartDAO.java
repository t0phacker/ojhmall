package ojhmall.dao.cart;

import java.util.List;

import ojhmall.common.dao.AbstractDAO;
import ojhmall.vo.Cart;
import ojhmall.vo.Product;

import org.springframework.stereotype.Repository;

@Repository("cartDAO")
public class CartDAO extends AbstractDAO {
	// 장바구니 추가
	public void addCart(Cart cart) {
		insert("Cart.insertCart", cart);
	}
	// 장바구니 불러오기
	public List<Cart> loadCartList(Cart cart) {
		return (List<Cart>) selectList("Cart.loadCartList", cart);
	}
	public void removeCart(int cartNumber) throws Exception {
		delete("Cart.removeCart", cartNumber);
	}
}
