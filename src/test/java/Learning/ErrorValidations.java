package ChaitanyaLearning;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import PageObjects.CartPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidations extends BaseTest {

	@Test (groups= {"Error Handling"})
	public void submitOrder() throws IOException, InterruptedException  {

//	String productName = "ZARA COAT 3";

	lg.pageLoginDetail("john@gmail.com", "988chaitU.");
	Assert.assertEquals("Incorrect email or password.", lg.errorMessage());
	
	}


@Test(retryAnalyzer=Retry.class)
public void submitOrderForCart() throws IOException, InterruptedException {
	
	
	String productName = "ZARA COAT 3";

	ProductCatalogue pc = lg.pageLoginDetail("rocky@gmail.com", "9885itU.");
	
	
	List<WebElement> products = pc.getProductsList();
	pc.productAddedToCart(productName);
	CartPage cart = pc.goToCart();
	
	Boolean match = cart.verifyProduct("ZaraCoat");
	
	Assert.assertFalse(match);
	
}
}
	