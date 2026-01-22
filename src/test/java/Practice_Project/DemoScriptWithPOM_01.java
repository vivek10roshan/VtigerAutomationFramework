package Practice_Project;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import element_Repository.ContactInfoPage;
import element_Repository.ContactsPage;
import element_Repository.CreateContactPage;
import element_Repository.HomePage;
import element_Repository.Loginpage;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithPOM_01 {
	public static void main(String[] args) throws IOException {
		//To Read Data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//To read data from utility  file
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To read data from excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
		
		//Test Script 
		//Step 1: Launch browser
		WebDriver driver = null;
	    if (BROWSER.equalsIgnoreCase("chrome")) {
	        driver = new ChromeDriver();
	    } else if (BROWSER.equalsIgnoreCase("edge")) {
	        driver = new EdgeDriver();
	    } else if (BROWSER.equalsIgnoreCase("firefox")) {
	        driver = new FirefoxDriver();
	    } else {
	        System.out.println("Invalid browser name");
	    }
	    wutil.toMaximize(driver);
	    wutil.waitForElement(driver);
	    driver.get(URL);
	    
	    //Step 2: Login to application with valid credentials
	    Loginpage lp = new Loginpage(driver);
	    lp.getUsernameTextfield().sendKeys(USERNAME);
	    lp.getPasswordTextfield().sendKeys(PASSWORD);
	    lp.getLoginButton().click();
	    
	    //Step 3: Navigate to Contact link
	    HomePage hp = new HomePage(driver);
	    hp.getContactLink().click();
	    		
	    
	    ContactsPage cp = new ContactsPage(driver);
	    cp.getCreateContactButton().click();
	    
	    //Step 5: create contact with mandatory field
	    CreateContactPage ccp = new CreateContactPage(driver);
	    ccp.getLastNameTextfield().sendKeys(LASTNAME);
	    
	    //Step 6: save and verify
	    ccp.getSaveButton().click();
	    ContactInfoPage cip = new ContactInfoPage(driver);
	    String lastname = cip.getContactHeaderInfo().getText();
	    if(lastname.contains(LASTNAME)) {
	    	System.out.println(lastname + "----------Passed");
	    }else {
	    	System.out.println(lastname + "---------- failed");
	    }
	    
	    
	    //Logout from Application
		
	    wutil.toMouseToHover(driver, hp.getAdministratorLink());
		hp.getLogout();
		
		//Close the Browser
		driver.quit();
		  
	}
	

}
