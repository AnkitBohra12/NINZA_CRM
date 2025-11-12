package practice.productTests;

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
import objectrepository.CreateProductPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.ProductPage;

public class CreateProductWithMandatoryFieldsTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		PropertyFileUtility pLib = new PropertyFileUtility();	
		String browserName = pLib.readDataFromPropertiesFile("Browser");
		String URL = pLib.readDataFromPropertiesFile("URL");
		String USERNAME = pLib.readDataFromPropertiesFile("Username");
		String PASSWORD = pLib.readDataFromPropertiesFile("Password");
		
//		Read test script from Excel
		ExcelFileUtility eLib = new ExcelFileUtility();
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Add Product", 1, 3);
		String QUANTITY = eLib.readDataFromExcelFile("Add Product", 1, 4);
		String PRICEPERUNIT = eLib.readDataFromExcelFile("Add Product", 1, 5);
		String SELECTCATEGORY = eLib.readDataFromExcelFile("Add Product", 1, 6);
		String SELECTVENDOR = eLib.readDataFromExcelFile("Add Product", 1, 7);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcelFile("Add Product", 1, 8);
		
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
		
//		create product 
		HomePage homePage = new HomePage(driver);
		homePage.getProductLink().click();
		
		ProductPage productPage = new ProductPage(driver);
		productPage.getAddCreateProductBtn().click();
		
		CreateProductPage createProductPage = new CreateProductPage(driver);
		createProductPage.getProductNameTF().sendKeys(PRODUCT_NAME);
		createProductPage.getQuantityTF().sendKeys(QUANTITY);
		createProductPage.getPriceTF().clear();
		createProductPage.getPriceTF().sendKeys(PRICEPERUNIT);
		wLib.select(SELECTCATEGORY, createProductPage.getProductCategoryDD());
		wLib.select(SELECTVENDOR, createProductPage.getVendorIdDD());
		createProductPage.getAddBtn().click();

		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		if (toastMsg.getText().contains(TOAST_MSG_VERIFICATION))
			System.out.println("Contact Created");
		else
			System.out.println("Contact Not Created");
		homePage.getCloseToastMsg().click();
		
		homePage.logout();
		
		driver.manage().window().minimize();
		driver.quit();

	}

}
