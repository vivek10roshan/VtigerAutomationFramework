package element_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	//Constructor
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		@FindBy(xpath ="//input[@name='account_name']/following-sibling::img[@alt='Select']")
		private WebElement contactsOrganization;
		
		public WebElement getContactsOrganization() {
			return contactsOrganization;
		}

		public void setContactsOrganization(WebElement contactsOrganization) {
			this.contactsOrganization = contactsOrganization;
		}
		@FindBy(name="lastname")
		private WebElement lastNameTextfield;
		
		@FindBy(name="button")
		private WebElement saveButton;

		public WebElement getLastNameTextfield() {
			return lastNameTextfield;
		}

		public void setLastNameTextfield(WebElement lastNameTextfield) {
			this.lastNameTextfield = lastNameTextfield;
		}

		public WebElement getSaveButton() {
			return saveButton;
		}

		public void setSaveButton(WebElement saveButton) {
			this.saveButton = saveButton;
		}
	
}
