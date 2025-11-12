package practice.Day5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
//		place order and get the order ID
//		print --> It is difficult to check the order iD in the console
//		that why we need to write the order ID into Excel

//		getting control of the sheet
FileInputStream fis = new FileInputStream("C:\\Users\\Ankit\\Downloads\\Advance selenium\\NINJA_CRM_E32.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("practice");
		
//		Control of the row
		Row r = sh.getRow(1);
		
//		create cell
		Cell c = r.createCell(1, CellType.STRING);
		
//		Enter the cell value
		c.setCellValue("Iphone 15");
		
//		to save the data create the object of FileOutputStream
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Ankit\\Downloads\\Advance selenium\\NINJA_CRM_E32.xlsx");
		
//		it will save the data
		wb.write(fos);
		
		wb.close();
		
	}

}
