package Practice_Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScript_004 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Step 2: Login to application with valid credentials
		driver.get("http://localhost:8881/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("TCS");
		
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
		if(OrganizationName.contains("TCS")) {
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
