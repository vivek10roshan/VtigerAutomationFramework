package Practice_Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript_002 {

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
		driver.findElement(By.name("accountname")).sendKeys("CodeBit1");
		
		//Step 4: Save and verify
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		String OrganizationName = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(OrganizationName.contains("CodeBit1")) {
			System.out.println(OrganizationName+ "-------Successfully created");
		}else {
			System.out.println(OrganizationName+ "-------------Failed");
		}
		
		
		//Step 6 : Logout from Application
		driver.findElement(By.xpath("//img[@src = 'themes/softed/images/user.PNG']")).click();
		WebElement LogOut = driver.findElement(By.linkText("Sign Out"));
		Actions action = new Actions(driver);
		action.moveToElement(LogOut).perform();
				
				
		//Close the Browser
		driver.quit();
	}

}
