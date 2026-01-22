package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	/**
	 * This method is used to maximize the browser provided driver
	 * 
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to maximize the browser provided driver
	 * 
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method will wait until the element gets loaded in webpage (Implicit
	 * wait)
	 * 
	 * @param driver
	 */
	public void waitForElement(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	/**
	 * This method will wait until the element becomes Clickable provided driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void elementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to handle dropDown using Index value
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle dropDown using value
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) { // Method overloading
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle dropDown using visibleText
	 * 
	 * @param element
	 * @param text
	 */
	public void toHandleDropdown(String text, WebElement element) { // Method overloading
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * 
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void tohandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * 
	 * @param driver
	 */
	public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick().perform();
		
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick().perform();
		
	}

	/**
	 *
	 * @param driver
	 * @param element
	 */
	public void toMouseToHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		
	}

	/**
	 *  This method is used to Handle drag and drop method from src to target
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}
	
	/**
	 *  This method is used to Handle pop up by accept
	 * @param driver
	 */
	public void toHandleAlertPopUpByAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to Handle pop up by dismiss
	 * @param driver
	 */
	public void toHandleAlertPopUpByDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method is used to Handle alert popup and accept it
	 * @param driver
	 * @return
	 */
	public String toCaptureMessageInAlertPopUpAndAccept(WebDriver driver) {
		Alert alertPopup = driver.switchTo().alert();
		String alertMessage = alertPopup.getText();
		return alertMessage;
	}
	
	/**
	 * This method is used to perform scroll action provided driver
	 * @param driver
	 */
	public void toScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(0,500)");
	
	}
	
	/**
	 * This method is used to take screenshot provided driver and screenshot name 
	 * @param driver
	 * @param screenshotname
	 * @throws IOException
	 */
	public void toTakeScreenshot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
	    File temp = ts.getScreenshotAs(OutputType.FILE);
	    File src = new File("./errorShots/" + screenshotname + ".png");
	    FileHandler.copy(temp, src);
	}
	
	
	/**
	 * This method is used to 
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwitchWindow(WebDriver driver, String partialTitle) {
		//Step 1 : capture all Ids
		Set<String> allIds = driver.getWindowHandles();
		//Step 2 : Transfer to every window and capture title
		for(String id : allIds) {
			String title = driver.switchTo().window(id).getTitle();
			//Step 3: compare title captured with partialTitle given
			if(title.contains(partialTitle)) {
				break;
			}
				
		}
	}
			
	
}
