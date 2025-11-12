package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
//		Create a java representation object of physical gile
		FileInputStream fis = new FileInputStream("C:\\Users\\Ankit\\Downloads\\Advance selenium\\NINJA_CRM_E32.xlsx");

//		Open the excel in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
//		get control of the sheet
		Sheet sh = wb.getSheet("Campaign");
		
//		get the control of the row
		Row rw = sh.getRow(1);
		
//		get the control of the cell
		Cell cl = rw.getCell(2);
		
		System.out.println(cl.getStringCellValue());
		
		String targetSize = wb.getSheet("Campaign").getRow(1).getCell(3).getStringCellValue();
		System.out.println(targetSize);
		
//		close the excel
		wb.close();
		
	}

}
