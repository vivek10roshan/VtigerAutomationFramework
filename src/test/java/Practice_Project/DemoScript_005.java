package Practice_Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript_005 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Step 2: Login to application with valid credentials
		driver.get("http://localhost:8881/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3 : Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4: click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 5 : Create contact with mandatory field
		driver.findElement(By.name("lastname")).sendKeys("ram rahim");
		
		//Step 6 : Select the organization from Organization lookup image
		// Store parent window
		String parent = driver.getWindowHandle();

		// Open lookup popup
		driver.findElement(By.xpath("//img[contains(@onclick,'return window')]")).click();
		// Switch to child window
		for (String win : driver.getWindowHandles()) {
		    if (!win.equals(parent)) {
		        driver.switchTo().window(win);
		        break;
		    }
		}

		// Click organization in popup
		driver.findElement(By.linkText("CodeBit")).click();

		// Switch back to parent
		driver.switchTo().window(parent);

		
		
		//Step 7 : Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(lastname.contains("ram rahim")) {
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
