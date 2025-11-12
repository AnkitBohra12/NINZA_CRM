package practice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutilities.ExcelFileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class WorkingWithDataProvider {
	
	@Test(dataProvider = "loginDetails")
	public void login(String username, String password) {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp("http://49.249.28.218:8098/", username, password);
	
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		driver.quit();
	}
	
	@DataProvider
	public Object[][] loginDetails() throws EncryptedDocumentException, IOException {
//		How to store a data
		
//		step1 - we have to create of obj of 2-dimenstional obj Array
		Object[][] objArr= new Object[5][2];
		
//		objArr[0][0]="Kavya";
//		objArr[0][1]="k@123";
//		objArr[1][0]="rmgyantra";
//		objArr[1][1]="rgy@9999";
//		objArr[2][0]="ankit";
//		objArr[2][1]="rgy@9999";
//		objArr[3][0]="amit";
//		objArr[3][1]="rgy@9999";
//		objArr[4][0]="sumit";
//		objArr[4][1]="rgy@9999";
		
		ExcelFileUtility eLib = new ExcelFileUtility();
		
		for(int i=1; i<=eLib.getRowCount("DataProvider"); i++) {
			objArr[i-1][0] = eLib.readDataFromExcelFile("DataProvider", i, 0);
			objArr[i-1][1] = eLib.readDataFromExcelFile("DataProvider", i, 1);
		}
		
		return objArr;
	}
}
