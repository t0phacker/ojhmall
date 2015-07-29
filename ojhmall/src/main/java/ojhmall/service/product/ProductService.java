package ojhmall.service.product;

import java.util.List;

import ojhmall.vo.Product;
import ojhmall.vo.User;

public interface ProductService {

	Product showPrd(Product prd) throws Exception;

	List<Product> schPrd(Product prd) throws Exception;

	List<Product> initPrd(int initPrdCount) throws Exception;

	List<Product> loadSellerPrd(User user) throws Exception;

	void addPrd(Product prd) throws Exception;

	Product getPrdInfo(Product prd) throws Exception;

	void updatePrdInfo(Product prd) throws Exception;

	List<Product> getPrdList(int prdStatus) throws Exception;

	void updatePrdStatus(Product prd) throws Exception;

}
