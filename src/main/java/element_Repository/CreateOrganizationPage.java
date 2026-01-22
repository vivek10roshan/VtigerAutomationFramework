package element_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
	//Constructor
		public CreateOrganizationPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
			@FindBy(xpath = "//input[@name='accountname']")
			private WebElement OrganizationNameTextfield;
			
			@FindBy(xpath = "//img[@src=\"themes/softed/images/select.gif\"]")
			private WebElement MemberOfTextField;
			

			public WebElement getSaveButton() {
				return SaveButton;
			}
			
			@FindBy(xpath = "//input[@title='Save [Alt+S]']")
			private WebElement SaveButton;

			@FindBy(xpath = "//select[@name='industry']")
			private WebElement IndustryDropdown;
			
			@FindBy(xpath  = "//select[@name='accounttype']")
			private WebElement TypeDropdown;
			
			public WebElement getTypeDropdown() {
				return TypeDropdown;
			}

			public void setTypeDropdown(WebElement typeDropdown) {
				TypeDropdown = typeDropdown;
			}

			public WebElement getIndustryDropdown() {
				return IndustryDropdown;
			}

			public void setIndustryDropdown(WebElement industryDropdown) {
				IndustryDropdown = industryDropdown;
			}

		
			
			
			public WebElement getOrganizationNameTextfield() {
				return OrganizationNameTextfield;
			}

			public void setOrganizationNameTextfield(WebElement organizationNameTextfield) {
				OrganizationNameTextfield = organizationNameTextfield;
			}

			public WebElement getMemberOfTextField() {
				return MemberOfTextField;
			}

			public void setMemberOfTextField(WebElement memberOfTextField) {
				MemberOfTextField = memberOfTextField;
			}
			
}
