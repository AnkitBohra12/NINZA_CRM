package practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class NewTest_02_priority_enabled {

	
	@Test(priority = -200, enabled = true)
	public void productCreation() {
		Reporter.log("productCreation",true);
	}
	
	@Test (dependsOnMethods = {"productCreation","updateProduct"})
	public void productDeletion() {
		System.out.println("deleteproduct");
	}
	
	@Test(dependsOnMethods = "productCreation")
	public void updateProduct() {
		Reporter.log("updateProduct",true);
	}
}
