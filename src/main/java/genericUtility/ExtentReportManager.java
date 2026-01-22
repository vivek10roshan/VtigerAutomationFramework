package genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;  //UI of the report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test;    //creating test case entries in the report and update status of the test method 
	
	public void onStart(ITestContext context) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report"); //title of the report
		sparkReporter.config().setReportName("Functional Testing");   //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment Name", "QA");
		extent.setSystemInfo("Tester Name ", "Vivek Roshan");
		extent.setSystemInfo(" os", "Window 11");
		extent.setSystemInfo("Browser Name", "Chrome");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());  //create a new entry in the report
		test.log(Status.PASS, "Test Case PASSED is:" +result.getName());  //update status p/f/s
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());  //create a new entry in the report
		test.log(Status.FAIL, "Test Case FAILED is:" +result.getName());  
		test.log(Status.FAIL, "Test Case FAILED cause is:" +result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());  //create a new entry in the report
		test.log(Status.SKIP, "Test Case SKIPPED is:" +result.getName());  
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
