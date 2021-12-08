package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CollectTestData {

	static String testDataDir = System.getProperty("user.dir")+"\\TestData\\TestData_iNeo.xlsx";
	public static Html_Report_Library htmlLib = new Html_Report_Library();
	public static HashMap<String, String> testData = new HashMap<String, String>();
	public static String URL ="";
	public static String UserName = "";
	public static String Password = "";
	public static String flight_Date = "";
	public static String dep_Code = "";
	public static String arr_Code = "";
	public static int flight_Number =0;
	public static String startDate = "";
	public static String endtDate = "";
	public static String type = "";
	
	public static void main(String scriptName) {
		HashMap<String, String> data = new HashMap<String, String>();
		data = getTestData(scriptName);		
		URL = data.get("iNeo_URL");
		UserName = data.get("User_Id");
		Password = data.get("User_Pwd");
		flight_Number = Integer.parseInt(data.get("Flight_Number"));
		flight_Date = data.get("Date");
		dep_Code = data.get("Origin");
		arr_Code = data.get("Destination");
		startDate =data.get("StartDate");
		endtDate =data.get("EndDate");
		type =data.get("Type");
		System.out.println(data);
	}
	
	@SuppressWarnings("rawtypes")
	public static HashMap<String, String> getTestData(String scriptName){
		if(testData.get("ScriptName") == null) {
			try{
				FileInputStream inFile = new FileInputStream(new File(testDataDir));
				XSSFWorkbook workbook = new XSSFWorkbook(inFile);
				XSSFSheet worksheet = workbook.getSheet("TestData");			
				// Get Column No
				XSSFRow headerRow =  worksheet.getRow(0);
		        Iterator headerIterator = headerRow.cellIterator();	        
		        // Get Row
		        Iterator rows = worksheet.rowIterator();
		        short columnNo = 1;
		        while (rows.hasNext()) {
		            XSSFRow row = (XSSFRow) rows.next();
		            XSSFCell col = row.getCell(columnNo);
		            int colCount = row.getLastCellNum();
		            if(col != null && col.getStringCellValue().equalsIgnoreCase(scriptName)){
		            	inFile = new FileInputStream(new File(testDataDir)); 
			            workbook = new XSSFWorkbook(inFile); 
			            XSSFCell cellValue = null; 
			            while (columnNo<=colCount) {
			            	XSSFCell headerColumn = (XSSFCell)headerIterator.next();
				            if(headerColumn != null && !headerColumn.getStringCellValue().contentEquals("") && columnNo>=2) {
				            	cellValue = worksheet.getRow(row.getRowNum()).getCell(columnNo-1);
				            	testData.put(headerColumn.getStringCellValue(), cellValue.getStringCellValue());
				            }
				            columnNo++;
				        }
			            inFile.close();
			            break;
		            }
		        }
			} catch (FileNotFoundException e) {
			    System.out.println("File Does Not Exists At " + testDataDir);
			} catch (IOException e) {
			    e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("Cell Value is Empty in File at " + testDataDir);
			}
		}
		return testData;
		
	}
	
	
	public HashMap<String, String> getData(){
		HashMap<String, String> testData = new HashMap<String, String>();
		String currentLine = "";
		try(BufferedReader br = new BufferedReader(new FileReader(new File(testDataDir)))) {
			currentLine = br.readLine();
			while(currentLine!= null) {
				testData.put(currentLine.split(":=")[0], currentLine.split(":=")[1]);
				currentLine = br.readLine();
			}
			br.close();
		} catch(FileNotFoundException e){
			htmlLib.logReport("Verify File Exist", "File NOT Found at : " + testDataDir, "FAIL", false);
		} catch(IOException e){
			e.printStackTrace();
		}
		return testData;
	}

	
	
}
