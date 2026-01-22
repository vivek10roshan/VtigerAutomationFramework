package contact_Test;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import element_Repository.ContactInfoPage;
import element_Repository.ContactsPage;
import element_Repository.CreateContactPage;
import element_Repository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;

public class ToCreateContactWithOrgTest extends BaseClass {
	@Test
	public void toCreateContact_001() throws IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactButton().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastNameTextfield().sendKeys(LASTNAME);	
		WebDriverUtility wutil = new WebDriverUtility();
		ccp.getContactsOrganization().click();
		String ORGNAME = eutil.toReadDataFromExcelFile("organization", 1, 2);
		ccp.getContactsOrganization().click();
		wutil.toSwitchWindow(driver, "Accounts");
		driver.findElement(By.linkText(ORGNAME)).click();
		wutil.toSwitchWindow(driver, "Contacts");
		ccp.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getContactHeaderInfo().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));	

	}
}
	
