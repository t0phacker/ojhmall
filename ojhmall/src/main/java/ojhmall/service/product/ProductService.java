package ojhmall.service.product;

import java.util.List;

import ojhmall.vo.Product;
import ojhmall.vo.User;

public interface ProductService {

	Product showPrdInfo(Product prd) throws Exception;

	List<Product> showPrdSchRes(Product prd) throws Exception;

	List<Product> initPrdList(int initPrdCount) throws Exception;

	List<Product> showSellerPrdList(User user) throws Exception;

	void addPrd(Product prd) throws Exception;

	Product showPrdInfoForUpdate(Product prd) throws Exception;

	void updatePrdInfo(Product prd) throws Exception;

	List<Product> getPrdListToAdmin(int prdStatus) throws Exception;

	void updatePrdStatus(Product prd) throws Exception;

}
