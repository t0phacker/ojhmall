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
	public List<Cart> initCartDlvrFee(List<Cart> cartList) {
		List<Cart> cartDlvrFeeList = cartList;
		for (int i = 0; i < cartDlvrFeeList.size(); i++) {
			boolean isFreeDlvr = cartDlvrFeeList.get(i).getCartPrice() < cartDlvrFeeList.get(i).getFreeDlvrPrc();
        	if (isFreeDlvr) {
        		cartList.get(i).setDeliveryFee(cartDlvrFeeList.get(i).getBaseDlvrFee());
        	}
        	else {
        		cartList.get(i).setDeliveryFee(0);
        	}
        }
		return cartDlvrFeeList;
	}
	public int countSameSeller(List<Cart> cartList) {
		int cartCount = 1;
		for (int i = 0; i < cartList.size()-1; i++) {
			boolean isSameSeller = cartList.get(i).getUser_userNumber() == cartList.get(i+1).getUser_userNumber();
			if (isSameSeller) {
				cartCount++;
			}
		}
		return cartCount;
	}
	
	public int[] sumSameSellerDlvrFee(List<Cart> cartList, int sellerNums) {
		int[] dlvrBill = new int[sellerNums];
		dlvrBill[0] = 0;
		int positionCheck = 0;
		for (int seller = 0; seller < sellerNums; seller++) {
			dlvrBill[seller] = cartList.get(positionCheck).getCartPrice();
			for (int sameSeller = positionCheck; sameSeller < cartList.size()-1; sameSeller++) {
				boolean isSameSeller = cartList.get(sameSeller).getUser_userNumber() == cartList.get(sameSeller+1).getUser_userNumber();
				if (isSameSeller) {
					dlvrBill[seller] += cartList.get(sameSeller+1).getCartPrice();
				}
				else {
					positionCheck = sameSeller+1;
				}					
			}
		}
		return dlvrBill;
	}
	
	public List<Cart> calDlvrFeeBySameSeller(int[] dlvrBiil, List<Cart> cartList, int sellerNums) {
		List<Cart> cartListOrderedByDlvrFee = cartList;
		for (int sameSeller = 0; sameSeller < sellerNums; sameSeller++) {
			boolean isFreeDlvr = (dlvrBiil[sameSeller] >= cartList.get(sameSeller).getFreeDlvrPrc());
			if (isFreeDlvr) {
				for (int freeDlvrSeller = sameSeller; freeDlvrSeller < cartList.size()-1; freeDlvrSeller++){
					boolean isSameSeller = cartList.get(freeDlvrSeller).getUser_userNumber() == cartList.get(freeDlvrSeller+1).getUser_userNumber();
					if (isSameSeller) {
						cartList.get(freeDlvrSeller).setDeliveryFee(0);
						cartList.get(freeDlvrSeller+1).setDeliveryFee(0);
					}
				}
			}
		}
		return cartListOrderedByDlvrFee;
	}
	// 장바구니 불러오기 
	@Override
	public List<Cart> loadCartList(Cart cart) throws Exception {
		List<Cart> cartList = cartDAO.loadCartList(cart);
		
		List<Cart> initedCartList = initCartDlvrFee(cartList);
		int sellerNums = countSameSeller(initedCartList);		
		int[] dlvrBill = sumSameSellerDlvrFee(initedCartList, sellerNums);
		List<Cart> cartListWithDlvrFee = calDlvrFeeBySameSeller(dlvrBill, initedCartList, sellerNums);
		return cartListWithDlvrFee;
	}
	// 장바구니 총 가격
	public int calTotalCartPrice(List<Cart> cartList) throws Exception {
		int totalCartPrice = 0;
		for (int i = 0; i < cartList.size(); i++)
			totalCartPrice += cartList.get(i).getCartPrice();
		return totalCartPrice;
	}
	// 장바구니 총 배송비
	@Override
	public int calTotalDeliveryFee(List<Cart> cartList) throws Exception {
		int totalDeliveryFee = 0;
		for (int i = 0; i < cartList.size(); i++)
			totalDeliveryFee += cartList.get(i).getDeliveryFee();
		return totalDeliveryFee;
	}
	// 장바구니 삭제
	@Override
	public void removeCart(int cartNumber) throws Exception {
		cartDAO.removeCart(cartNumber);
	}
	// 장바구니 선택
	@Override
	public Cart selectCart(int cartNumber) throws Exception {
		return cartDAO.selectCart(cartNumber);
	}
	// 장바구니 업데이트
	@Override
	public void updateCart(Cart cart) throws Exception {
		cartDAO.updateCart(cart);
	}
	// 전체주문시 장바구니 전체 삭제
	@Override
	public void removeCartList(String cartNumber) throws Exception {
		// TODO Auto-generated method stub
		String[] trimmedCartNumber = cartNumber.split("@");
		for (int i = 0; i < trimmedCartNumber.length; i++) {
			cartDAO.removeCart(Integer.parseInt(trimmedCartNumber[i]));
		}
	}
}
