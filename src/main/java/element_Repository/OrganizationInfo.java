package element_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfo {
	
	//Constructor
			public OrganizationInfo(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}
			
			@FindBy(xpath = "//span[@class = 'dvHeaderText']")
			private WebElement OrganizationInfo;
			
		public WebElement getOrganizationInfo() {
				return OrganizationInfo;
			}

			
}
