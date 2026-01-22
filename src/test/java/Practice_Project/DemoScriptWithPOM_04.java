package Practice_Project;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import element_Repository.CreateOrganizationPage;
import element_Repository.HomePage;
import element_Repository.Loginpage;
import element_Repository.OrganizationInfo;
import element_Repository.OrganizationPage;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithPOM_04 {

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
		String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("organization", 1, 2);

		
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
	    
	    //Step 3: Navigate to organization link
	    HomePage hp = new HomePage(driver);
	    hp.getOrganizationLink().click();
	    
	    OrganizationPage op = new OrganizationPage(driver);
	    op.getCreateOrganizationButton().click();
	    
	    //Step 4: Create organization with mandatory field
	  //Generate Random number 
	  	Random r = new Random();
	  	int randomNumber = r.nextInt(1000);
	    CreateOrganizationPage cop = new CreateOrganizationPage(driver);
	    cop.getOrganizationNameTextfield().sendKeys(ORGANIZATIONNAME+randomNumber);
	    
	    //Select Chemical in industry dropDown
	   
	    wutil.toHandleDropdown(cop.getIndustryDropdown(), "Energy");  
	    
	    wutil.toHandleDropdown(cop.getTypeDropdown(), "Customer");
	    
	    
	    
	    //Step 5: Save and verify
	    cop.getSaveButton().click();
	   
	    OrganizationInfo oi = new OrganizationInfo(driver);
		String OrganizationName = oi.getOrganizationInfo().getText();
		
		if(OrganizationName.contains(ORGANIZATIONNAME+randomNumber)) {
			System.out.println(OrganizationName+ "-------Successfully created");
		}else {
			System.out.println(OrganizationName+ "-------------Failed");
		}
		
		//Step 6: Logout from Application
	    
		
	    wutil.toMouseToHover(driver, hp.getAdministratorLink());
		hp.getLogout();
		
		//Close the Browser
		driver.quit();

	}

}
