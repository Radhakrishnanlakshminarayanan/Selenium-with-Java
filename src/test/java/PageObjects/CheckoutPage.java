package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[class*='details__user'] input:nth-child(1)")
	WebElement selectCountry;
	
	@FindBy (css="button[class*='ta-item']:nth-child(3)")
	WebElement selectOption;
	
	@FindBy (css=".action__submit")
	WebElement placeOrder;
	
	
	
	By results = By.cssSelector("[class*='details__user'] input:nth-child(1)");
	
	
	
	//driver.findElement(By.cssSelector("[class*='cartSection'] button:nth-child(1)")).click();
	
	public void selectionOfCountry(String countryName)
	{
		elementToBeClickable(results);
		selectCountry.click();
		selectCountry.sendKeys(countryName);
		selectOption.click();
		getWaitTimeReady(results);	
	}
	
	public ConfirmationPage submitOrder()
	{

		placeOrder.click();
		ConfirmationPage finalPage = new ConfirmationPage(driver);
		return finalPage;

	}
}
