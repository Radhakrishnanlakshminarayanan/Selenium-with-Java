package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lg;
	
	public WebDriver initializeDriver() throws IOException {
		
	
	
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Resources\\GlobalData.properties");
	prop.load(fis);
	
	String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
	
//	String browserName = prop.getProperty("browser");
	
	if(browserName.contains("chrome")) {
		
		ChromeOptions options = new ChromeOptions();
		if(browserName.contains("headless"))
		{
			options.addArguments("headless");
		}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		
	
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		//firefox
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(filepath),
				StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException 
	{
		driver = initializeDriver();
		lg = new LandingPage(driver);
		lg.pageURLAccess();
		return lg;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws InterruptedException 
	{	
		Thread.sleep(5000);
		driver.close();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
}
