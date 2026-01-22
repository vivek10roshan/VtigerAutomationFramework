package Practice_Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptWithDDT_Org {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
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
        String ORGANIZATIONNAME = wb.getSheet("organization").getRow(1).getCell(2).getStringCellValue();
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
		
		//Step 3: Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		
		
		//Generate Random number 
		Random r = new Random();
		int randomNumber = r.nextInt(1000);
        driver.findElement(By.name("accountname")).sendKeys(ORGANIZATIONNAME+randomNumber);
		
		//Save and verify
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		String OrganizationName = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(OrganizationName.contains(ORGANIZATIONNAME+randomNumber)) {
			System.out.println(OrganizationName+ "-------Successfully created");
		}else {
			System.out.println(OrganizationName+ "-------------Failed");
		}
		
		
		//Logout from Application
		
		WebElement LogOut = driver.findElement(By.xpath("//img[@src = 'themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(LogOut).perform();
				
				
		//Close the Browser
		Thread.sleep(3000);
		driver.quit();
		
		
		
		
		
		
		
		
		

	}

}
