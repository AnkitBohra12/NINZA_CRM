package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {
	
	WebDriver driver;
	
	public CreateCampaignPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
//	AutoHealing
//	@FindAll({@FindBy(id="name"), @FindBy(name = "campaignName")})
	@FindBy(name = "campaignName")
	private WebElement campaignNameTF;
	
	@FindBy(name = "targetSize")
	private WebElement targetSizeTF;
	
	@FindBy(name = "campaignStatus")
	private WebElement campaignStatusTF;
	
	@FindBy(name = "expectedCloseDate")
	private WebElement expectedCloseDateTf;
	
	@FindBy(xpath = "//button[text()='Create Campaign']")
	private WebElement createCampaignBtn;

	public WebElement getCampaignNameTF() {
		return campaignNameTF;
	}

	public WebElement getTargetSizeTF() {
		return targetSizeTF;
	}

	public WebElement getCampaignStatusTF() {
		return campaignStatusTF;
	}

	public WebElement getExpectedCloseDateTf() {
		return expectedCloseDateTf;
	}

	public WebElement getCreateCampaignBtn() {
		return createCampaignBtn;
	}
	
}
