package producttest;

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
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.CreateProductPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.ProductPage;

@Listeners(genericutilities.ListenerImplementation.class)
public class CreateProductTest extends BaseClass {
	
	@Test
	public void CreateProductWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException{
		
//		Read test script from Excel
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Add Product", 1, 3);
		String QUANTITY = eLib.readDataFromExcelFile("Add Product", 1, 4);
		String PRICEPERUNIT = eLib.readDataFromExcelFile("Add Product", 1, 5);
		String SELECTCATEGORY = eLib.readDataFromExcelFile("Add Product", 1, 6);
		String SELECTVENDOR = eLib.readDataFromExcelFile("Add Product", 1, 7);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcelFile("Add Product", 1, 8);
		
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
		Assert.assertTrue(toastMsg.getText().contains(TOAST_MSG_VERIFICATION));
		homePage.getCloseToastMsg().click();
	}

}
