package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	
WebDriver driver;


@FindBy (css="tr td:nth-child(3)")
List<WebElement> ListFromOrders;


	public OrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public Boolean verifyProduct(String productName)
	{
		Boolean match = ListFromOrders.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
