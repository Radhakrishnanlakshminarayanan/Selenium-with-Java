package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	
WebDriver driver;


@FindBy (css=".cartSection h3")
List<WebElement> cartProducts;


@FindBy (css="[class*='cartSection'] button:nth-child(1)")
WebElement checkoutBox;


	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By results = By.cssSelector("[class*='details__user'] input:nth-child(1)");
	
	public Boolean verifyProduct(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage checkoutButtonActive()
	{
		checkoutBox.click();
		getWaitTimeReady(results);
		CheckoutPage chkout =  new CheckoutPage(driver);
		return chkout;
	}
	
}
