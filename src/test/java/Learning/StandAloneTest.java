package ChaitanyaLearning;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="dataToBeUsed", groups={"Purchase"})
//	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		
		

		ProductCatalogue pc = lg.pageLoginDetail(input.get("email"), input.get("password"));
		
		
		List<WebElement> products = pc.getProductsList();
		pc.productAddedToCart(input.get("product"));
		CartPage cart = pc.goToCart();
		
		
		Boolean match = cart.verifyProduct(input.get("product"));
		
		Assert.assertTrue(match);
		
		CheckoutPage chkout = cart.checkoutButtonActive();
		
		chkout.selectionOfCountry("India");
		
		ConfirmationPage finalPage = chkout.submitOrder();
		
		String confirmation = finalPage.finalTextMessage();
		
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
//		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		
		
//		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
//		List<WebElement>  cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
//		Boolean match = cartProducts.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		
		
		
//		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*='cartSection'] button:nth-child(1)")));
//		
//		driver.findElement(By.cssSelector("[class*='cartSection'] button:nth-child(1)")).click();
//	
//		driver.findElement(By.cssSelector("[class*='details__user'] input:nth-child(1)")).sendKeys("India");
//		
//		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='details__user'] input:nth-child(1)")));
//		
//		driver.findElement(By.cssSelector("button[class*='ta-item']:nth-child(3)")).click();
//		
//		driver.findElement(By.cssSelector(".action__submit")).click();
//		
//		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		
//		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();
	}
	
@Test (dependsOnMethods= {"submitOrder"})
public void submitOrderToGetOrders() throws IOException, InterruptedException {
		

		ProductCatalogue pc = lg.pageLoginDetail("john.reeves@gmail.com", "988507chaitU.");
		
		OrdersPage orderspage = pc.goToOrders();
		orderspage.verifyProduct(productName);
		Assert.assertTrue(orderspage.verifyProduct(productName));
	
}



@DataProvider
public Object[][] dataToBeUsed() throws IOException
{
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "john.reeves@gmail.com");
//	map.put("password", "988507chaitU.");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map.put("email", "john.reeves@gmail.com");
//	map.put("password", "988507chaitU.");
//	map.put("product", "ADIDAS ORIGINAL");
	
	List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Chaitanya//data//PurchaseOrder.json");
	
	return new Object[][] {{data.get(0)},{data.get(1)}};
}

}
