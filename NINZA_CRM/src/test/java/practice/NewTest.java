package practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import objectrepository.LoginPage;

public class NewTest {
	
	@Test
	public void createCampaignWithMendatoryFieldsTest() {
	
		Reporter.log("createCampaignWithMendatoryFieldsTest",true);
	}
	
	@Test
	public void invoke() {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.loginToApp("http://49.249.28.218:8098/", username, password);
		driver.quit();
	}

}
