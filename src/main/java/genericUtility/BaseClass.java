package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import element_Repository.HomePage;
import element_Repository.Loginpage;

public class BaseClass {
	PropertyFileUtility putil = new PropertyFileUtility();
    public WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sDriver; //Listeners
	
	@BeforeSuite
	public void beforeSuiteConfig() throws IOException {
		System.out.println("----------------DataBase Connected-------------");
	}
		@BeforeClass     //Launch	
		public void beforeClassConfig() throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
	        driver = new ChromeDriver();
	    } else if (BROWSER.equalsIgnoreCase("edge")) {
	        driver = new EdgeDriver();
	    } else if (BROWSER.equalsIgnoreCase("firefox")) {
	        driver = new FirefoxDriver();
	    } else {
	        System.out.println("Invalid browser name");
	    }
		sDriver = driver; //Listener
		System.out.println("Browser got Launched");
		
		getWutil().toMaximize(driver);
		System.out.println("Browser got maximize");
		getWutil().waitForElement(driver);
		driver.get(URL);
		
	}
	@BeforeMethod
	public void beforeMethodConfig() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		Loginpage lp = new Loginpage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);;
		lp.getPasswordTextfield().sendKeys(PASSWORD);;
		lp.getLoginButton().click();
		System.out.println("Browser got loged in to vtiger");	
	}
	@AfterMethod
	public void afterMethod() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		getWutil().toMouseToHover(driver, hp.getAdministratorLink());
		Thread.sleep(3000);
		 hp.getLogout().click();
		System.out.println("Browser got loged logout from vtiger");
	}
	
	@AfterClass
	public void afterClassConfig() {
		driver.quit();
		System.out.println("Browser got close successfully");
	}
	
	@AfterSuite
	public void afterSuitConfig() {
		System.out.println("//////////////DataBase connection disconnected/////////");
	}
	public WebDriverUtility getWutil() {
		return wutil;
	}
	public void setWutil(WebDriverUtility wutil) {
		this.wutil = wutil;
	}
	
}
