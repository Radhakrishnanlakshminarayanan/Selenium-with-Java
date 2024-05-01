package PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	driver.findElement(By.cssSelector("input[type='email']")).sendKeys("john.reeves@gmail.com");
	
	@FindBy (css="input[type='email']")
	WebElement userEmail;
	
	@FindBy (css="input[type='password']")
	WebElement passwordElement;
	
	@FindBy (id="login")
	WebElement submit;
	
	@FindBy (css="div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;

	
	public ProductCatalogue pageLoginDetail(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}
	
	//div[aria-label='Incorrect email or password.']
	
	
	public String errorMessage()
	{
		getWaitTimeAppear(errorMsg);
		return errorMsg.getText();
	}
	
	
	public void pageURLAccess()
	{

		driver.get("https://rahulshettyacademy.com/client");

	}
	
	

}
