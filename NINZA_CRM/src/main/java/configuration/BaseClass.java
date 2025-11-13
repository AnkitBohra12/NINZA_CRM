package configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class BaseClass {
//	comment
	public WebDriver driver = null;
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public ExcelFileUtility eLib = new ExcelFileUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public static WebDriver sdriver = null;
	
	@BeforeSuite
		public void beforeSuite() {
		System.out.println("Establish the Data connection");
	}

	@BeforeTest(groups = {"smoke","regression"})
	 	public void beforeTest() {
		System.out.println("Pre-Conditions for parallel executions");
	}
	
//	@Parameters("BROWSER")
	@BeforeClass(groups = {"smoke","regression"})
		public void beforeClass() throws IOException {
//		String BROWSER = browser;
		String BROWSER = pLib.readDataFromPropertiesFile("Browser");
		
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(settings);
		} 
//		else if(BROWSER.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver();
//		} 
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if(BROWSER.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		
		sdriver=driver;
		
		driver.manage().window().maximize();
		wLib.implicitWait(driver);	
	}
  
	@BeforeMethod(groups = {"smoke","regression"})
		public void beforeMethod() throws IOException {
		System.out.println("Login");
		String URL = pLib.readDataFromPropertiesFile("URL");
		String USERNAME = pLib.readDataFromPropertiesFile("Username");
		String PASSWORD = pLib.readDataFromPropertiesFile("Password");
		
//		String URL = System.getProperty("url");
//		String USERNAME = System.getProperty("username");
//		String PASSWORD = System.getProperty("password");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(URL, USERNAME, PASSWORD);
	}

 	@AfterMethod
 		public void afterMethod() {
 		System.out.println("Logout");
 		HomePage homePage = new HomePage(driver);
 		homePage.logout();
 	}

 	@AfterClass
 		public void afterClass() {
 		System.out.println("Close the browser");
 		driver.quit();
 	}

 	@AfterTest
 		public void afterTest() {
 		System.out.println("Post-Conditions for parallel executions");
 	}

 	@AfterSuite
 		public void afterSuite() {
 		System.out.println("Close the DataBase connection");
 	}
}
