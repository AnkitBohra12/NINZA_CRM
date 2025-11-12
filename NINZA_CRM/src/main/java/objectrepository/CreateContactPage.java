package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
	WebDriver driver;
	
	public CreateContactPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "organizationName")
	private WebElement oraganizationNameTF;
	
	@FindBy(name = "title")
	private WebElement titleTF;
	
	@FindBy(name = "contactName")
	private WebElement contactNameTF;
	
	@FindBy(name = "mobile")
	private WebElement mobileTF;
	
	@FindBy(xpath = "//*[name()='svg' and @data-icon='plus']")
	private WebElement plusBtn;
	
	@FindBy(xpath = "//button[text()='Create Contact']")
	private WebElement createContactbtn;
	

	public WebElement getCreateContactbtn() {
		return createContactbtn;
	}

	public WebElement getOraganizationNameTF() {
		return oraganizationNameTF;
	}

	public WebElement getTitleTF() {
		return titleTF;
	}

	public WebElement getContactNameTF() {
		return contactNameTF;
	}

	public WebElement getMobileTF() {
		return mobileTF;
	}

	public WebElement getPlusBtn() {
		return plusBtn;
	}
	

}
