package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
		
//		Create a java representation object of Phyiscal file
		FileInputStream fis = new FileInputStream("C:\\Users\\Ankit\\Downloads\\Advance selenium\\CommonData.properties");
		
//		store the data in the object
		Properties pobj = new Properties();
		
//		load the data
		pobj.load(fis);
		
		String browserName = pobj.getProperty("Browser");
		String url = pobj.getProperty("URL");
		String username = pobj.getProperty("Username");
		String password = pobj.getProperty("Password");
		
		System.out.println("browserName = " + browserName);
		System.out.println(url);
		System.out.println(username);	
		System.out.println(password);
		
	}

}
