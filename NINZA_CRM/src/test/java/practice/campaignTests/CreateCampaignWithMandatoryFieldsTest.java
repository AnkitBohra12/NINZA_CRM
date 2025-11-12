package practice.campaignTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import genericutilities.ExcelFileUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithMandatoryFieldsTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		PropertyFileUtility pLib = new PropertyFileUtility();	
		String browserName = pLib.readDataFromPropertiesFile("Browser");
		String URL = pLib.readDataFromPropertiesFile("URL");
		String USERNAME = pLib.readDataFromPropertiesFile("Username");
		String PASSWORD = pLib.readDataFromPropertiesFile("Password");
		
//		Read test script from Excel
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaign", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaign", 1, 3);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcelFile("Campaign", 1, 4);
		
		
//		for implicit wait
		WebDriverUtility wLib = new WebDriverUtility();
		
//		for notification pop-up
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		WebDriver driver = null;
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(settings);
		} else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		wLib.implicitWait(driver);	
		
//		getting from POM file of loginPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(URL, USERNAME, PASSWORD);
		
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
		if (toastMsg.getText().contains(TOAST_MSG_VERIFICATION))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		homePage.getCloseToastMsg().click();

// 		Logout
		homePage.logout();

		
//		close the browser
		driver.manage().window().minimize();
		driver.quit();
		
	}

}
