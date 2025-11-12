package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
//	this will give the current address of the WE
	public LoginPage(WebDriver driver) {
		this.driver = driver;  // initalize the non-static memeber into the constructor
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "username") 
	private WebElement usernameTF;
	
	@FindBy(id = "inputPassword") 
	private WebElement passwordTF;
	
	@FindBy(xpath = "//button[text()='Sign In']") 
	private WebElement signInBtn;
	
	public WebElement getUsernameTF() {
		return usernameTF;
	}
	public WebElement getPasswordTF() {
		return passwordTF;
	}
	public WebElement getSignInBtn() {
		return signInBtn;
	}
	
//	reuseability of login page so we can use it in all the testcases
	public void loginToApp(String url, String username, String password) {
		driver.get(url);
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		signInBtn.click();
	}
	
}
