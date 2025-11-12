package campaigntest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
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
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

@Listeners(genericutilities.ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass{

	@Test(groups = {"smoke","regression"})
	public void createCampaignWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException {		
//		Read test script from Excel
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 1, 3);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcelFile("Campaign", 1, 4);
		
//		Create Campaign
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();	
				
// 		Verification
		HomePage homePage = new HomePage(driver);
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		homePage.getCloseToastMsg().click();
		Assert.assertTrue(toastMsg.getText().contains(TOAST_MSG_VERIFICATION));
	}
	
	@Test
	public void createCampaignWithStatusTest() throws IOException {
// 		Read test script data from excel
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 4, 2);
		String STATUS = eLib.readDataFromExcelFile("Campaign", 4, 3);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 4, 4);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcelFile("Campaign", 4, 5);

// 		Create Campaign
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getCampaignStatusTF().sendKeys(STATUS);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

// 		Verification
		HomePage homePage = new HomePage(driver);
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		Assert.assertTrue(toastMsg.getText().contains(TOAST_MSG_VERIFICATION));
		homePage.getCloseToastMsg().click();
	}
	
	@Test
	public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {
// 		Read test script data from excel
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 7, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 7, 3);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcelFile("Campaign", 7, 4);

// 		Create Campaign
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getExpectedCloseDateTf().sendKeys(jLib.getRequiredDate(50));
		createCampaignPage.getCreateCampaignBtn().click();

// 		Verification
		HomePage homePage = new HomePage(driver);
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		Assert.assertTrue(toastMsg.getText().contains(TOAST_MSG_VERIFICATION));
		homePage.getCloseToastMsg().click();
	}
	
}
