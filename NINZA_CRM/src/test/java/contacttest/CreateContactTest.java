package contacttest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import configuration.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.ContactsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.CreateContactPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.SelectCampaignPage;

@Listeners(genericutilities.ListenerImplementation.class)
public class CreateContactTest extends BaseClass {
	
	@Test
	public void createContactWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException {	
		
// 		Read test script data from excel
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Contacts", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Contacts", 1, 3);
		String ORGANIZATION = eLib.readDataFromExcelFile("Contacts", 1, 4);
		String TITLE = eLib.readDataFromExcelFile("Contacts", 1, 5);
		String CONTACT = eLib.readDataFromExcelFile("Contacts", 1, 6);
		String SELECT_CAMPAIGN_PAGE_TITLE = eLib.readDataFromExcelFile("Contacts", 1, 8);
		String CAMPAIGN_DD_VALUE = eLib.readDataFromExcelFile("Contacts", 1, 9);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcelFile("Contacts", 1, 10);
		
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		WebElement dateField = driver.findElement(By.name("expectedCloseDate"));
		dateField.sendKeys("07-12-2025");
		
//		Create campaign
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();
		HomePage homePage = new HomePage(driver);
		homePage.getCloseToastMsg().click();
		
//		Click on contacts
		homePage.getContactsLink().click();
				
//		Click on +create contact
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getAddCreateContactBtn().click();
		
//		Enter the manadatory fields
		CreateContactPage createContactPage = new CreateContactPage(driver);
		createContactPage.getOraganizationNameTF().sendKeys(ORGANIZATION);
		createContactPage.getTitleTF().sendKeys(TITLE);
		createContactPage.getContactNameTF().sendKeys(CONTACT);
		createContactPage.getMobileTF().sendKeys("9"+ jLib.generateNineDigitNumber());
				
//		Click on plus button
		createContactPage.getPlusBtn().click();
		
//		get parentId
		String parentId = driver.getWindowHandle();		
		wLib.switchDriverControlOnTitle(driver, SELECT_CAMPAIGN_PAGE_TITLE);
		
//		Select campaign name from drop down
		SelectCampaignPage selectCampaignPage = new SelectCampaignPage(driver);
		WebElement campaignDD = selectCampaignPage.getCampaignDD();
		wLib.select(campaignDD, CAMPAIGN_DD_VALUE);		
		
//		Enter Campaign name in search textfield
		selectCampaignPage.getSearchTF().sendKeys(CAMPAIGN_NAME);
		
//		Click on select button
		WebElement selectBtn = selectCampaignPage.getSelectBtn();	
		wLib.waitUntilElementToBeVisible(driver, selectBtn);
		selectBtn.click();
		
//		Switch the driver control to parent
		driver.switchTo().window(parentId);
		
//		Click on create contact 
		createContactPage.getCreateContactbtn().click();
				
//		Verification
		
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		Assert.assertTrue(toastMsg.getText().contains(TOAST_MSG_VERIFICATION));
		homePage.getCloseToastMsg().click();
	
	}

}
