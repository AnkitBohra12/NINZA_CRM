package practice.assertion;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssertions {
	
	@Test
	public void t1() {
		System.out.println("Start");
		
//		Hard assert
//		Assert.assertEquals("hdfc","hfdc");
		
//		soft Assert
//		SoftAssert soft = new SoftAssert();
//		soft.assertEquals("hdfc", "hfdc");
		
//		true assert
//		Assert.assertTrue("hdfc".equals("hfdc"),"Both are not equal");
//		
//		false assert
//		Assert.assertFalse("hdfc".equals("hfdc"));
//		System.out.println("End");	
		
//		Assert null
		String s = "kavya";
		System.out.println("Start");
		Assert.assertNotNull(s);
		System.out.println("End");

	}	
}
