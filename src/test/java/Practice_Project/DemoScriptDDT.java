package Practice_Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptDDT {

    public static void main(String[] args) throws IOException {

        // Read data from property file
        FileInputStream pfis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties prop = new Properties();
        prop.load(pfis);

        String BROWSER = prop.getProperty("browser");
        String URL = prop.getProperty("url");
        String USERNAME = prop.getProperty("username");
        String PASSWORD = prop.getProperty("password");

        // Read data from Excel file
        FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\testData.xlsx");
        Workbook wb = WorkbookFactory.create(efis);
        String LASTNAME = wb.getSheet("contacts").getRow(1).getCell(2).getStringCellValue();
        //System.out.println(LASTNAME);

        // Step 1: Launch browser
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

        //Step 2: Pass the data from prop(common data)
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(URL);
        driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4: click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 5 : Create contact with mandatory field
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				
		//Step 6 : Save and verify
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
