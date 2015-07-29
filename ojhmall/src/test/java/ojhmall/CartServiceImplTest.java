package ojhmall;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import ojhmall.service.cart.CartService;
import ojhmall.vo.Cart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/action-servlet.xml",
		"classpath:/config/spring/context-mapper.xml", "classpath:/config/spring/context-datasource.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CartServiceImplTest {
	@Resource(name = "cartService")
	private CartService cartService;
	
	@Test
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, 
	value="/config/mapper/cart/testAddCart_SQL.xml")
	//@DatabaseSetup(value = "/config/mapper/cart/testAddCart_SQL.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void selectCart() throws Exception {
		
		Cart cart = cartService.selectCart(5);
		
		assertThat(cart.getPrdAmount()).isEqualTo(3);
	}
	
	
	@Test
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value="EXPECTED_REMOVE_CART.xml")
	public void removeCart() throws Exception {
		cartService.removeCart(5);
	}
	
	
	private Cart createCart(int cartNumber, int prdAmount, int cartPrice, int newUser, int userNumber, int prdNumber) {
		Cart cart = new Cart();
		cart.setCartNumber(cartNumber);
		cart.setPrdNumber(prdNumber);
		cart.setPrdAmount(prdAmount);
		cart.setCartPrice(cartPrice);
		cart.setNewUser(newUser);
		cart.setUserNumber(userNumber);
		return cart;
		
	}
}
