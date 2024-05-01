package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy (css=".mb-3")
	List<WebElement> products;
	
	@FindBy (css=".ng-animating")
	WebElement spinner;
	
	
	@FindBy (css=".cartSection h3")
	List<WebElement> cartProducts;
	
	By productsWhole = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	
	
	public List<WebElement> getProductsList()
	{
		getWaitTimeReady(productsWhole);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductsList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
		
	}
	
	public void productAddedToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		getWaitTimeReady(toastMessage);
		getWaitTimeDisappear(spinner);
	
	}
	
}
