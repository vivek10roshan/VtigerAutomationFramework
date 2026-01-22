package organization_Test;

import java.io.IOException;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;
import element_Repository.CreateOrganizationPage;
import element_Repository.HomePage;
import element_Repository.OrganizationInfo;
import element_Repository.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;

public class ToCreateOrganizationWithIndustry extends BaseClass {
	@Test
		public void toCreateOrganaization_003() throws IOException {
			HomePage hp = new HomePage(driver);
			hp.getOrganizationLink().click();
			OrganizationPage op = new OrganizationPage(driver);
			op.getCreateOrganizationButton().click();
			ExcelFileUtility eutil = new ExcelFileUtility();
			String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("organization", 1, 2);
		  	Random r = new Random();
		  	int randomNumber = r.nextInt(1000);
			CreateOrganizationPage cop = new CreateOrganizationPage(driver);
			cop.getOrganizationNameTextfield().sendKeys(ORGANIZATIONNAME+randomNumber);
			cop.getIndustryDropdown().sendKeys("Chemicals");
			cop.getSaveButton().click();
			OrganizationInfo oi = new OrganizationInfo(driver);
			String accountname = oi.getOrganizationInfo().getText();
			Assert.assertTrue(accountname.contains(ORGANIZATIONNAME+randomNumber));	
		}
}

