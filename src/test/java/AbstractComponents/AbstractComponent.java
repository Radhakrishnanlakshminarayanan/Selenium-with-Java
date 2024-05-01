package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	@FindBy (css="[routerlink*=cart]")
	WebElement cartButton;
	
	@FindBy (css="[routerlink*=myorders]")
	WebElement orderList;
	
	public CartPage goToCart()
	{
		cartButton.click();
		CartPage cart = new CartPage(driver);
		return cart;
	}
	
	public OrdersPage goToOrders()
	{
		orderList.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
	}


	
	public void getWaitTimeReady(By findBy)
	{
	
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
	w.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void getWaitTimeDisappear(WebElement elementOnThePage)
	{

	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
	w.until(ExpectedConditions.invisibilityOf(elementOnThePage));

	}
	
	public void getWaitTimeAppear(WebElement elementOnThePage)
	{
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
	w.until(ExpectedConditions.visibilityOf(elementOnThePage));

	}
	
	public void elementToBeClickable(By elementNeeded)
	
	{
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
	w.until(ExpectedConditions.elementToBeClickable(elementNeeded));

	}
	
}
