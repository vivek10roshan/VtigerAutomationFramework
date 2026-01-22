package Practice_Project;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithGenericUtility_004 {

	public static void main(String[] args) throws IOException {
		//To read data from property file 
		PropertyFileUtility  putil = new PropertyFileUtility();
		ExcelFileUtility    eutil = new ExcelFileUtility();
		WebDriverUtility    wutil = new WebDriverUtility();
		
		//To Read data from Utility(PropertyFileUtility / commomData.proprties) File
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To Read data from Excel File
		
		String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("organization", 1, 2);
		
		//Test Script 
		//Step 1 : Launch Browser
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
		
		//Step 2 : Login to Application with valid credentials
		wutil.toMaximize(driver);
		wutil.waitForElement(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(ORGANIZATIONNAME);
		
		//Step 4: select energy in the industry dropDown
		WebElement industrydropDown = driver.findElement(By.xpath("//select[@name= 'industry']"));
		Select select = new Select(industrydropDown);
		select.selectByValue("Energy");
		
		//Step 5: Select customer in the type dropDown
		WebElement typedropDown = driver.findElement(By.xpath("//select[@name= 'accounttype']"));
		Select select1 = new Select(typedropDown);
		select1.selectByValue("Customer");
		
		//Save and verify
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		String OrganizationName = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(OrganizationName.contains(ORGANIZATIONNAME)) {
			System.out.println(OrganizationName+ "-------Successfully created");
		}else {
			System.out.println(OrganizationName+ "-------------Failed");
		}
		
		
		//Logout from Application
		driver.findElement(By.xpath("//img[@src = 'themes/softed/images/user.PNG']")).click();
		WebElement LogOut = driver.findElement(By.linkText("Sign Out"));
		Actions action = new Actions(driver);
		action.moveToElement(LogOut).perform();
	
				
				
		//Close the Browser
		driver.quit();

		

	}

}
