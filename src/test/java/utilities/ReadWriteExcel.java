package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadWriteExcel {
	
	static File file = null;
	static FileInputStream fis = null;
	static XSSFWorkbook wbk = null;
	static XSSFSheet sheet = null;
	static String seah = null;
	static XSSFRow Row = null;
	static FileOutputStream outputStream = null;
	static XSSFCell cell = null;
	
	
	public XSSFCell getData(String excelPath, int sheetNumber, int row, int col) throws Exception 
	{
			file = new File(excelPath);
		
			fis = new FileInputStream(file);
		
			wbk = new XSSFWorkbook(fis);
		
			sheet = wbk.getSheetAt(sheetNumber);
			
			Row= sheet.getRow(row);
			
			cell=Row.getCell(col);
			
			return cell;
	}
	
	}
