package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Common_Library {
public static Html_Report_Library htmlLib = new Html_Report_Library();
	
	/*********************************************************************************************
	 * Method Name		: performAction
	 * Parameter Used	: WebElement element, String action, String data, String logicalName
	 * Author			: Rohit Prajapati
	 * Creation Date	: 10/08/2020
	 * Description		: Method to perform action on said object and log report 
	 * ********************************************************************************************/
	public void performAction(WebElement element, String action, String data, String logicalName)
	{
		try{
			switch(action.toUpperCase()) {
			case "SELECT":
				Select selObject = new Select(element);
				selObject.selectByVisibleText(data);
				htmlLib.logReport("Perform Select on " + logicalName, "Select '" + data + "' is Successful", "PASS", true);
				break;
			case "SET":
				element.clear();
				if(logicalName.toUpperCase().contains("PASSWORD")) {
					element.sendKeys(data);
				}
				else {
					element.sendKeys(data);
				}
				htmlLib.logReport("Perform Set on " + logicalName, "Set '" + data + "' is Successful", "PASS", true);
				break;
			default:
				if(data.equalsIgnoreCase("ON") && !element.isSelected()){
					element.click();
				}
				else if(data.equalsIgnoreCase("OFF") && element.isSelected()) {
					element.click();
				}
				else {
					element.click();
				}
				htmlLib.logReport("Perform Click on " + logicalName, "Click on Element Successful", "PASS", true);
			}
		}
		catch(NoSuchElementException ex){
			htmlLib.logReport("Perform Action on " + logicalName, "Element Does not Exist", "FAIL", true);
		}
		catch(Exception e){
			htmlLib.logReport("Perform Action on " + logicalName, "Exception Occured. \r\n"+ e.getMessage(), "FATAL", false);
			e.printStackTrace();
		}
	}
	
	/*********************************************************************************************
	 * Method Name		: scrollToWebElement
	 * Parameter Used	: WebDriver driver, WebElement element
	 * Author			: Rohit Prajapati
	 * Creation Date	: 05/06/2015
	 * Description		: Method to scroll page to element supplied
	 * ********************************************************************************************/
	public void scrollToWebElement(WebDriver driver, WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/*********************************************************************************************
	 * Method Name		: readTextFile
	 * Parameter Used	: String fileLocation, int lineNumber
	 * Author			: Rohit Prajapati
	 * Creation Date	: 05/06/2015
	 * Description		: Method to read data from Text File
	 * ********************************************************************************************/
	public String readTextFile(String fileLocation, int lineNumber)
	{
		String currentLine = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(new File(fileLocation))))
		{
			int line = 1;
			while((currentLine = br.readLine())!= null)
			{
				if(line == lineNumber){
					System.out.println(currentLine);
					return currentLine.substring(0, currentLine.length()).trim();
				}
				line++;
			}			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return currentLine; 		
	}
	
	/*********************************************************************************************
	 * Method Name		: writeTextFile
	 * Parameter Used	: String filePath, String content, boolean appendValue, boolean addNewLine
	 * Author			: Rohit Prajapati
	 * Creation Date	: 05/06/2015
	 * Description		: Method to write data into Text file
	 * ********************************************************************************************/
	public void writeTextFile(String filePath, String content, boolean appendValue, boolean addNewLine)
	{
		try{	
			File file = new File(filePath);
	
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), appendValue);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			if(addNewLine){
				bw.newLine();
			}
			bw.close();
	
			System.out.println("Done");
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*********************************************************************************************
	 * Method Name		: readEntireTextFile
	 * Parameter Used	: String fileLocation
	 * Author			: Rohit Prajapati
	 * Creation Date	: 05/06/2015
	 * Description		: Read Entire Text File Content
	 * ********************************************************************************************/
	public String readEntireTextFile(String fileLocation)
	{
		String currentLine = "";
		try(BufferedReader br = new BufferedReader(new FileReader(new File(fileLocation)))) {
			StringBuilder sb = new StringBuilder();
			currentLine = br.readLine();
			while(currentLine!= null) {
				sb.append(currentLine);
				currentLine = br.readLine();
			}
			currentLine = sb.toString();
			br.close();
		} catch(FileNotFoundException e){
			htmlLib.logReport("Verify File Exist", "File NOT Found at : " + fileLocation, "FAIL", false);
		} catch(IOException e){
			e.printStackTrace();
		}
		return currentLine;
	}
	/*********************************************************************************************
	 * Method Name		: readExcelFile
	 * Parameter Used	: String filePath, String sheetName, String testName, String ColumnName
	 * Author			: Rohit Prajapati
	 * Creation Date	: 05/06/2015
	 * Description		: Method to read data from Text File
	 * ********************************************************************************************/@SuppressWarnings({ "rawtypes" })
	public String readExcelFile(String filePath, String sheetName, String testName, String columnName)
	{
		try{
			FileInputStream inFile = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(inFile);
			XSSFSheet worksheet = workbook.getSheet(sheetName);
			int column = 0;
			
			// Get Column No
			XSSFRow headerRow =  worksheet.getRow(0);
	        java.util.Iterator headerIterator = headerRow.cellIterator();
	        int cn = 0;
	        while (headerIterator.hasNext()) {
	            XSSFCell headerColumn = (XSSFCell)headerIterator.next();
	            if(headerColumn != null && headerColumn.getStringCellValue().equalsIgnoreCase(columnName)){
	            	column = cn;
	            	break;
	            }
	            cn++;
	        }	        
	        // Get Row
	        java.util.Iterator rows = worksheet.rowIterator();
	        short columnNo = 1;
	        String cellValue = "";
	        while (rows.hasNext()) 
	        {
	            XSSFRow row = (XSSFRow) rows.next();
	            XSSFCell col = row.getCell(columnNo);  
	            
	            if(col != null && col.getStringCellValue().equalsIgnoreCase(testName)){		    	   
	            	inFile = new FileInputStream(new File(filePath)); 
		            workbook = new XSSFWorkbook(inFile);  
		            XSSFCell cell = null; 
		            
		            cell = worksheet.getRow(row.getRowNum()).getCell(column);  
		            System.out.println("The Value is :  " +cell.getStringCellValue());
		              
		            inFile.close();
		            cellValue = cell.getStringCellValue();
		            break;
	            }
	        }			
			return cellValue;
			
		} catch (FileNotFoundException e) {
		    System.out.println("File Does Not Exists At " + filePath);
		    return "";
		} catch (IOException e) {
		    e.printStackTrace();
		    return "";
		} catch (NullPointerException e) {
			System.out.println("Cell Value is Empty in File at " + filePath);
			return "";
		}
	}
	
	/*********************************************************************************************
	 * Method Name		: writeIntoExcelFile
	 * Parameter Used	: String ExcelPath, String sheetName, String TestCaseName, String Data, String ColumnName
	 * Author			: Rohit Prajapati
	 * Creation Date	: 05/06/2015
	 * Description		: Method to write data into Excel File
	 * ********************************************************************************************/@SuppressWarnings({ "rawtypes", "static-access" })
	public void writeIntoExcelFile(String ExcelPath, String SheetName, String TestCaseName, String Data, String ColumnName)
	{  
	    try{
	    	FileInputStream fileIn = new FileInputStream(ExcelPath);
		    XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
		    int ColumnNo = 0;
		    XSSFSheet sheet = workbook.getSheet(SheetName);
	        
	        // To get the Header Column Number
			XSSFRow headerRow =  sheet.getRow(0);
	        java.util.Iterator headerIterator = headerRow.cellIterator();
	        int cn = 0;
	        while (headerIterator.hasNext()) {
	        	XSSFCell headerColumn = (XSSFCell)headerIterator.next();
	            if(headerColumn != null && headerColumn.getStringCellValue().equalsIgnoreCase(ColumnName)){
	            	System.out.println(ColumnName + " Found! at Cell number " + cn);
	            	ColumnNo = cn;
	            	break;
	            }
	            cn++;
	        }
	        
	        java.util.Iterator rows = sheet.rowIterator();
	        short column = 1;
	         
	        while (rows.hasNext()) 
	        {
	        	XSSFRow row = (XSSFRow) rows.next();
	        	XSSFCell cell = row.getCell(column);  
	            
	            if(cell != null && cell.getStringCellValue().equalsIgnoreCase(TestCaseName)){
	            	System.out.println(TestCaseName+" Found! at Row number "+ row.getRowNum());
		    	   
	            	FileInputStream fsIP = new FileInputStream(new File(ExcelPath)); 
	            	XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		            wb.setMissingCellPolicy(row.CREATE_NULL_AS_BLANK);
		            XSSFSheet worksheet = wb.getSheet(SheetName); 
		            
		            cell = worksheet.getRow(row.getRowNum()).getCell(ColumnNo);
		            System.out.println("This was the value :  " + cell.getStringCellValue());
		            cell.setCellType(cell.CELL_TYPE_BLANK);
		              
		            cell.setCellValue(Data);   
		            fsIP.close();
		             
		            FileOutputStream output_file = new FileOutputStream(new File(ExcelPath)); 
		              
		            wb.write(output_file); 
		              
		            output_file.close();  
		            break;
		       }
	        }
	        fileIn.close();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*********************************************************************************************
	* Method Name		: dateCalendarEntry
	* Parameter Used	: int dayCount,int monthCount,int yearCount
	* Author			: Rohit Prajapati
	* Creation Date		: 16/06/2020
	* Description		: Method to return date in dd-MMM-yyyy format
	*********************************************************************************************/
	 public void writeToNewWordDoc(String filePath, ArrayList<String> datatoWrite)
	 {
		 try {
			 XWPFDocument document = new XWPFDocument();
			 XWPFParagraph paragraph = document.createParagraph();
			 XWPFRun run = paragraph.createRun();
			 for (int dataToWriteIndex = 0; dataToWriteIndex <= datatoWrite.size() - 1; dataToWriteIndex++) {
				 if (((String)datatoWrite.get(dataToWriteIndex)).contains(".jpg")) {
					 FileInputStream pic = new FileInputStream((String)datatoWrite.get(dataToWriteIndex));
					 run.addPicture(pic, 5, (String)datatoWrite.get(dataToWriteIndex), Units.toEMU(500.0D), Units.toEMU(300.0D));
					 run.addCarriageReturn();
					 run.addBreak();
					 run.addCarriageReturn();
				 }
				 else {
					 run.setText((String)datatoWrite.get(dataToWriteIndex));
					 run.addCarriageReturn();
				 }
			 }
			 FileOutputStream out = new FileOutputStream(new File(filePath));
			 document.write(out);
//	      	 document.close();
			 out.close();
		 }
		 catch (Exception localException) {}
	 }
	
	/*********************************************************************************************
	* Method Name		: dateCalendarEntry
	* Parameter Used	: int dayCount,int monthCount,int yearCount
	* Author			: Rohit Prajapati
	* Creation Date		: 16/06/2020
	* Description		: Method to return date in dd-MMM-yyyy format
	*********************************************************************************************/
	public String dateCalendarEntry(int Daycount,int monthcount,int yearcount)
	{
		String str_Date;
		String Str_Month;
		String Str_Year;
		String returnDate = null;
		Calendar ExecutionDate = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");
		ExecutionDate.add(Calendar.DATE,Daycount);
		ExecutionDate.add(Calendar.MONTH,monthcount);
		ExecutionDate.add(Calendar.YEAR,yearcount);
		str_Date = simpleDate.format(ExecutionDate.getTime()).split("-")[0];
		Str_Month = simpleDate.format(ExecutionDate.getTime()).split("-")[1];
		Str_Year = simpleDate.format(ExecutionDate.getTime()).split("-")[2];
		returnDate = str_Date+"-"+Str_Month+"-"+Str_Year ;
		System.out.println("Data : " + str_Date);
		return returnDate;
	}

	
	/*********************************************************************************************
	* Method Name		: getRandomNumberBetwRange
	* Parameter Used	: long aStart, long aEnd
	* Author			: Rohit Prajapati
	* Creation Date		: 16/06/2020
	* Description		: Method to return a random number between the range supplied
	*********************************************************************************************/
	public int getRandomNumberBetwRange(long aStart, long aEnd)
	{
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		Random aRandom = new Random();
		//get the range, casting to long to avoid overflow problems
		long range = aEnd - aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long)(range * aRandom.nextDouble());
		int randomNumber =  (int)(fraction + aStart);    
	//	System.out.println("Random Number: " + randomNumber);
		return randomNumber;
	}
	
	/*********************************************************************************************
	* Method Name		: getRandomString
	* Parameter Used	: int stringLength
	* Author			: Rohit Prajapati
	* Creation Date		: 16/06/2020
	* Description		: Method to return a random string of required length 
	*********************************************************************************************/
	public String getRandomString(int stringLength)
	{   
		final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    StringBuffer randStr = new StringBuffer();
	    
	    for(int counter=0; counter < stringLength; counter++){
	        int number = getRandomNumberBetwRange(0, 51);
	        char ch = CHAR_LIST.charAt(number);
	        randStr.append(ch);
	    }
	    System.out.println("Random String: " + randStr.toString());
	    return randStr.toString();
	}
	
	/*********************************************************************************************
	* Method Name		: getRandomNumberFixedLen
	* Parameter Used	: int integerLength
	* Author			: Rohit Prajapati
	* Creation Date		: 16/06/2020
	* Description		: Method to return a random number of required fixed length 
	*********************************************************************************************/
	public String getRandomNumberFixedLen(int integerLength)
	{   
		final String CHAR_LIST = "0123456789";
	    StringBuffer randStr = new StringBuffer();
	    
	    for(int counter=0; counter < integerLength; counter++){
	        int number = getRandomNumberBetwRange(0, 9);
	        char ch = CHAR_LIST.charAt(number);
	        randStr.append(ch);
	    }
	    System.out.println("Random Number: " + randStr.toString());
	    return randStr.toString();
	}
	
	/**
	 * deleteFolder
	 * Method to complete delete directory folder given
	 * @param file
	 */
	public static void deleteFolder(File file)
	{
		for (File subFile : file.listFiles()) {
			if(subFile.isDirectory()) {
				deleteFolder(subFile);
			} else {
				subFile.delete();
			}
		}
		file.delete();
	}
	
	/*********************************************************************************************
	 * Method Name		: copyFile
	 * Parameter Used	: String sourceFilePath, String destinationFolder
	 * Author			: Rohit Prajapati
	 * Creation Date	: 23/12/2016
	 * Description		: Method to copy File from source to destination
	 * ********************************************************************************************/@SuppressWarnings("resource")
	public void copyFile(String sourceFilePath, String destinationFolder)
	{
		try{
			File source = new File(sourceFilePath);
			String fileName = new File(sourceFilePath).getName();
			File destination = new File(destinationFolder + "//" + fileName);
			
			FileChannel sourceChannel = new FileInputStream(source).getChannel();
			FileChannel destChannel = new FileOutputStream(destination).getChannel();
	    	
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
			sourceChannel.close();
			destChannel.close();
			System.out.println("File Copy Successful");
		} catch (FileNotFoundException e) {
			htmlLib.logReport("Copy File from Source to Destination", "File NOT Present at - " + sourceFilePath, "FAIL", false);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void clickFlightGantt(WebDriver driver, String Image_Path, String Click_Type) throws Exception  {
		
		Screen scn = new Screen();
		
		Pattern clickFligt = new Pattern(Image_Path);
		
		scn.wait(clickFligt,9000);
		
		if(Click_Type.contentEquals("rightClick")) 
		{
		
			//scn.hover(clickFligt);
			//List<WebElement> elements = driver.findElements(By.xpath("//*[text()='"+ "645 AUH" +"']"));
			//System.out.println(elements.size());
			scn.rightClick();
			//scn.click(clickFligt.targetOffset(-100, -20));
			//scn.drag(clickFligt.targetOffset(-100, -20));
			
		}
		
		else{
			scn.doubleClick();
			//scn.type(Key.CTRL+scn.click());
			
		}
		
	}
	
	/*********************************************************************************************
	 * Description	:Take the page view to start from zero hour in Gantt Screen
	 * @param		: driver
	 * @throws		: Exception
	 * ********************************************************************************************/
	public void scrollToHourZero(WebDriver driver) throws Exception  
	{
		String Image_Path =System.getProperty("user.dir")+ "\\TestData\\ScrollToHour0.png";
		Screen scn = new Screen();
		int scroll_cnt=5;
		 for(int i=0; i<scroll_cnt;i++) 
		 {
			 scn.click();
			 scn.type(Key.LEFT);		  
			 Pattern zeroHour = new Pattern(Image_Path);
			 Match m = scn.exists(zeroHour.exact());
			 if(m!=null) 
			 {
				 scn.hover(zeroHour);
				 break;
			 }
			 
		 }
		
	}

	@SuppressWarnings("unused")
	private static Match find(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/****************************************************************************
	 * Description: Method returns column index of provided column name
	 * @return Column index number of the said column in the mentioned web table
	 * **************************************************************************/
	public int getColumnIndex(WebDriver driver, String tableXPath, String columnName)
    {
        // No.of columns    	
    	List<WebElement> columns = driver.findElements(By.xpath(tableXPath + "/thead/tr/td"));
        System.out.println("No of cols are : " + columns.size());
        int colIndex = 0;
        for(int cl=1; cl<=columns.size(); cl++) {
        	if(driver.findElement(By.xpath(tableXPath + "/thead/tr/td[" + cl + "]")).getText().contains(columnName)) {
        		colIndex = cl;
        		break;
        	}
        }
        System.out.println(columnName+" found at column index - "+colIndex);
        return colIndex;
    }
	
	/*****************************************************************************************
	 * Description: Method returns row index of provided data in said column
	 * @return Row index number of the data to find in said column in the mentioned web table
	 * ***************************************************************************************/
	public int getRowIndex(WebDriver driver, String tableXPath, int columnIndex, String data2Find)
    {
        // No.of rows
		List<WebElement> rows = driver.findElements(By.xpath(tableXPath + "/tbody/tr/td[1]"));
        System.out.println("No of rows are : " + rows.size());
        int rowIndex = 0;
		for(int rw=1; rw<=rows.size(); rw++) {
    		if(driver.findElement(By.xpath(tableXPath + "/tbody/tr[" + rw + "]/td[" + columnIndex + "]")).getText().contentEquals(data2Find)) {
        		rowIndex = rw;
        		break;
        	}
		}
        System.out.println(data2Find+" found at row index - "+rowIndex);
        return rowIndex;
    }

}
