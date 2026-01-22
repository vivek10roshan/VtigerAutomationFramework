package element_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//Constructor
		public HomePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(linkText = "Contacts")
		private WebElement contactLink;
		
		public WebElement getContactLink() {
			return contactLink;
		}

		public void setContactLink(WebElement contactLink) {
			this.contactLink = contactLink;
		}

		public WebElement getOrganizationLink() {
			return organizationLink;
		}

		public void setOrganizationLink(WebElement organizationLink) {
			this.organizationLink = organizationLink;
		}

		public WebElement getAdministratorLink() {
			return administratorLink;
		}

		public void setAdministratorLink(WebElement administratorLink) {
			this.administratorLink = administratorLink;
		}
		
		public WebElement getLogout() {
			return logout;
		}

		public void setLogout(WebElement logout) {
			this.logout = logout;
		}

		@FindBy(linkText = "Organizations")
		private WebElement organizationLink;
		
		@FindBy(xpath = "//img[@src = 'themes/softed/images/user.PNG']")
		private WebElement administratorLink;
		
		@FindBy(xpath = "//a[text()='Sign Out']")
		private WebElement logout;
		

}
