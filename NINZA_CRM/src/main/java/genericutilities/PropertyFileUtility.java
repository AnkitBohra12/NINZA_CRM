package genericutilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	
	public String readDataFromPropertiesFile(String key) throws IOException {
	FileInputStream fis = new FileInputStream("C:\\Users\\Ankit\\Downloads\\Advance selenium\\CommonData.properties");
	Properties prop = new Properties();
	prop.load(fis);
	String value = prop.getProperty(key);
	return value;
	}
	
//	Create a java representation object of Phyiscal file
//	FileInputStream fis = new FileInputStream("C:\\Users\\Ankit\\Downloads\\Advance selenium\\CommonData.properties");
//	
//	store the data in the object
//	Properties pobj = new Properties();
//	
//	load the data
//	pobj.load(fis);

}
