package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG
{
	
public static ExtentReports getReportObject()
{
	String path = System.getProperty("users.dir") + "//reports//index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("QA Automation Results");
	reporter.config().setDocumentTitle("Test results");
	
	ExtentReports extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Chaitanya");
	return extent;
	
}
}
