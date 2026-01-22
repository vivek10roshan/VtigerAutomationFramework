package Practice_Project;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithGenericUtility_005 {

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
		
		String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
		
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
		//Step 3 : Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4: click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 5 : Create contact with mandatory field
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step 6 : Select the organization from Organization lookup image
		// Store parent window
		String parent = driver.getWindowHandle();

		// Open lookup popUp
		driver.findElement(By.xpath("//img[contains(@onclick,'return window')]")).click();
		// Switch to child window
		for (String win : driver.getWindowHandles()) {
		    if (!win.equals(parent)) {
		        driver.switchTo().window(win);
		        break;
		    }
		}

		// Click organization in poPup
		driver.findElement(By.linkText("CodeBit")).click();

		// Switch back to parent
		driver.switchTo().window(parent);

		
		
		//Step 7 : Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(lastname.contains(LASTNAME)) {
			System.out.println(lastname+ "----Passed");
		}else {
			System.out.println(lastname+ "---------failed");
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
