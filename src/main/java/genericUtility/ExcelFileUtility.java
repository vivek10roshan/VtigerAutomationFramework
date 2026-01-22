package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	
	/**
	 * 
	 * @param sheetname
	 * @param row
	 * @param cell
	 * @return
	 * @throws IOException
	 */
	
	public String toReadDataFromExcelFile(String sheetname,int row, int cell) throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		//Workbook wb = new WorkbookFactory.create(fis);
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
		return value;
		
	}
	

}
