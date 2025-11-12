package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {
	
	WebDriver driver;
	public CreateProductPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindAll({@FindBy(name="productId"), @FindBy(xpath = "//input[@name='productId']")})
	private WebElement productIdTF;
	
	@FindBy(name = "productName")
	private WebElement productNameTF;
	
	@FindBy(name = "quantity")
	private WebElement quantityTF;
	
	@FindBy(name = "price")
	private WebElement priceTF;
	
	@FindBy(name = "productCategory")
	private WebElement productCategoryDD;
	
	@FindBy(name = "vendorId")
	private WebElement vendorIdDD;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement addBtn;
	
	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getProductIdTF() {
		return productIdTF;
	}

	public WebElement getProductNameTF() {
		return productNameTF;
	}

	public WebElement getQuantityTF() {
		return quantityTF;
	}

	public WebElement getPriceTF() {
		return priceTF;
	}

	public WebElement getProductCategoryDD() {
		return productCategoryDD;
	}

	public WebElement getVendorIdDD() {
		return vendorIdDD;
	}

}
