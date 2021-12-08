package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EY_iFlightNeo_HomePage_Messages {
	public utilities.Html_Report_Library htmlLib = new utilities.Html_Report_Library();
	public static utilities.Common_Library comm = new utilities.Common_Library();
	private static WebElement element = null;
	private static WebDriverWait wait;
	
	
	public static WebElement MainMenu_Messages(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='menu_messages']")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_aircraft']")));
        element = driver.findElement(By.xpath("//a[@id='menu_messages']"));
        return element;
	}

	public static WebElement Opt_schedulePublication(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@id='menu_schedulepublication']"));
        return element;
	}
	
	public static WebElement txtbox_EmailStartDate(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='schedulepublicationWidgetModel_flightSchedulePublicationDTO_fromDate']")));
        element = driver.findElement(By.xpath("//input[@name='schedulepublicationWidgetModel_flightSchedulePublicationDTO_fromDate']"));
        return element;
	}
	
	public static WebElement txtbox_EmailEndDate(WebDriver driver){
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='schedulepublicationWidgetModel_flightSchedulePublicationDTO_toDate']")));
        element = driver.findElement(By.xpath("//input[@name='schedulepublicationWidgetModel_flightSchedulePublicationDTO_toDate']"));
        return element;
	}
	
	public static WebElement radiobtn_Type_DailyAllocation(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='dailyAllocationReport']")));
        element = driver.findElement(By.xpath("//input[@id='dailyAllocationReport']"));
        return element;
	}
	
	public static WebElement radiobtn_Type_ScheduleChanges(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='scheduleChangesReport']")));
        element = driver.findElement(By.xpath("//input[@id='scheduleChangesReport']"));
        return element;
	}
	
	public static WebElement btn_searchButton(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Search')]")));
        element = driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
        return element;
	}
	
	public static WebElement resultLines(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='schedulePublicationGrid_W1']//tr")));
        element = driver.findElement(By.xpath("//table[@id='schedulePublicationGrid_W1']//tr"));
        return element;
	}
	
	public static WebElement perviewSendbutton(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@ng-click='previewAndSend()']"));
        return element;
	}
	
	public static WebElement emailPreviewWindow(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-describedby='EmailPreview']")));
        element = driver.findElement(By.xpath("//div[@aria-describedby='EmailPreview']"));
        return element;
	}
	
	public static WebElement emailSubjectline(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='schedulepublicationWidgetModel_subject']")));
        element = driver.findElement(By.xpath("//input[@name='schedulepublicationWidgetModel_subject']"));
        return element;
	}
	
	public static WebElement headerEmailPreview(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Dear All')]")));
        element = driver.findElement(By.xpath("//span[contains(text(),'Dear All')]"));
        return element;
	}
	
	public static WebElement footerEmailPreview(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'ALL TIMES IN UTC')]")));
        element = driver.findElement(By.xpath("//span[contains(text(),'ALL TIMES IN UTC')]"));
        return element;
	}
	
	public static WebElement To_textBox(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model='newTag.text']")));
        element = driver.findElement(By.xpath("//input[@ng-model='newTag.text']"));
        return element;
	}
	
	public static WebElement sendNowbutton(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Send Now')]")));
        element = driver.findElement(By.xpath("//button[contains(text(),'Send Now')]"));
        return element;
	}
	
	public static void SchedulePublication_emailNotificaitonForm(WebDriver driver, String startDate, String endDate, String type)
	{
			comm.performAction(MainMenu_Messages(driver), "Click", "", "Click on Messages option in main menu");
			List<WebElement> nav_dropdown=driver.findElements(By.xpath("//ul[@id='nav']//li//ul//li//a"));
			for(int i=0;i<nav_dropdown.size();i++)
			{
				WebElement element =nav_dropdown.get(i);
				
				String subHeader=element.getAttribute("innerHTML");
					
				if(subHeader.contains("Schedule Publication")) 
				{
					comm.performAction(Opt_schedulePublication(driver), "Click", "", "Select the Schedule publication option in messages menu");
					break;
				}
			}
			comm.performAction(txtbox_EmailStartDate(driver), "SET", startDate, "entering start date");
			comm.performAction(txtbox_EmailEndDate(driver), "SET", endDate, "entering End date");
			if(type.contains("DailyAllocation"))
			{
				comm.performAction(radiobtn_Type_DailyAllocation(driver), "Click", "", "select Daily Allocation radio button");
			}
			if(type.contains("ScheduleChanges"))
			{
				comm.performAction(radiobtn_Type_ScheduleChanges(driver), "Click", "", "select Schedule Changes radio button");
			}
			comm.performAction(btn_searchButton(driver), "Click", "", "Search button clicked");
			resultLines(driver).isDisplayed();
			wait=new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(perviewSendbutton(driver))).click();
			emailPreviewWindow(driver).isDisplayed();
	}
	
	
	public static void emailPreviewVerify(WebDriver driver) 
	{
		String subject_line=emailSubjectline(driver).getText();
		System.out.println(subject_line);
		String header_Line=headerEmailPreview(driver).getAttribute("innerHTML");
		System.out.println(header_Line);
		String footer_Line=footerEmailPreview(driver).getAttribute("innerHTML");
		System.out.println(footer_Line);
		List<String> columnNames =new ArrayList<String>();
		List<WebElement> columns = driver.findElements(By.xpath("//tbody//tr[5]//td//p//span"));
		for(int k=0; k<columns.size();k++)
		{
			WebElement eachColumn =columns.get(k);
			String columnHeaders =eachColumn.getAttribute("innerHTML");
			columnNames.add(columnHeaders);
		}
		System.out.println(columnNames);
		
		
	}
	
}
