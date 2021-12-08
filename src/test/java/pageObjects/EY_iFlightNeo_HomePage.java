package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EY_iFlightNeo_HomePage {
	public utilities.Html_Report_Library htmlLib = new utilities.Html_Report_Library();
	public static utilities.Common_Library comm = new utilities.Common_Library();
	private static WebElement element = null;
	private static List<WebElement> element_list = null;
	private static WebDriverWait wait;
	
	public static WebElement airport_Option(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='nav']//li//a[contains(text(),'Airport')]")));
        element = driver.findElement(By.xpath("//ul[@id='nav']//li//a[contains(text(),'Airport')]"));
        return element;
	}
	
	public static WebElement aircraft_MainMenu(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_aircraft']")));
        element = driver.findElement(By.xpath("//a[@id='menu_aircraft']"));
        return element;
	}
	
	public static WebElement menu_aircraftGantt(WebDriver driver){
        element = driver.findElement(By.xpath("//ul[@id='nav']//li//ul//li//a[@id='menu_aircraftGantt']"));
        return element;
	}
	
	public static WebElement btn_DayZoom(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Day zoom']//a[@class='tool_icon icon18']")));
        element = driver.findElement(By.xpath("//li[@title='Day zoom']//a[@class='tool_icon icon18']"));
        return element;
	}
	
	public static WebElement find_Flight(WebDriver driver){
		wait =new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='Find Flight']")));
        element = driver.findElement(By.xpath("//li[@title='Find Flight']"));
        return element;
	}
	
	public static WebElement find_Flight_popUp(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='findFlight_dialog']")));
        element = driver.findElement(By.xpath("//div[@id='findFlight_dialog']"));
        return element;
	}
	
	public static WebElement flight_No(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@id='IATAflightIdLabel_W1_fltNo']"));
        return element;
	}
	
	public static WebElement FF_DateField(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@id='W1_flightDateLabel_findFlightWidget']"));
        return element;
	}
	
	public static WebElement FF_DepFieldClick(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@id='s2id_departureAirportLabel']//a//span[contains(text(),'Dep')]"));
        return element;
	}
	
	public static WebElement FF_EnterDEP(WebDriver driver){
        element = driver.findElement(By.xpath("//body/div[@id='select2-drop']/div//input"));
        return element;
	}
	
	public static WebElement FF_DEPARRSearchResult(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='select2-result-label']"));
        return element;
	}
	
	public static WebElement FF_ArrFieldClick(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@id='s2id_arrivalAirportLabel']"));
        return element;
	}
	
	public static WebElement FF_EnterArr(WebDriver driver){
        element = driver.findElement(By.xpath("//body/div[@id='select2-drop']/div//input"));
        return element;
	}
	
	public static WebElement FF_search(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@ng-click='findFlight()']"));
        return element;
	}
	
	public static WebElement FF_Escape(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@aria-describedby='findFlight_dialog']"));
        return element;
	}
	
	public static WebElement goto_CurrentDate(WebDriver driver){
	
        element = driver.findElement(By.xpath("//li[@title='Goto current date']"));
        return element;
	}
	
	public static WebElement rightClick_Menu(WebDriver driver){
        element = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']"));
        return element;
	}
	
	public static WebElement Delete_Flight(WebDriver driver){
        element = driver.findElement(By.xpath("//ul[@class='context-menu-list']//li//span[contains(text(),'Delete Flight')]"));
        return element;
	}
	
	public static WebElement confirm_DeleteFlightPopup(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Do you want to delete the selected Flight?')]")));
        element = driver.findElement(By.xpath("//div[contains(text(),'Do you want to delete the selected Flight?')]"));
        return element;
	}

	public static WebElement Yes_DeleteFlight(WebDriver driver){
        element = driver.findElement(By.xpath("//span[contains(text(),'YES')]"));
        return element;
	}	
	
	public static  List<WebElement> rightClick_dropdown(WebDriver driver){
        element_list = driver.findElements(By.xpath("//ul[@class='context-menu-list context-menu-root']//li//span"));
        return element_list;
	}	
	
	public static WebElement panRectangle(WebDriver driver){
		wait=new WebDriverWait(driver,30);
        element = driver.findElement(By.xpath("//li[@title='Pan/Rectangle Select']"));
        return element;
	}	
	
	public static WebElement container_swapList(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ui-id-81']")));
        element = driver.findElement(By.xpath("//span[@id='ui-id-81']"));
        return element;
	}
	/****************************************************************************************************
	 * Description	: When swap option is selected sometimes it gives only one window to publish changes
	 * @param 		: driver
	 * @return		: element
	 ****************************************************************************************************/
	public static WebElement btn_ForcePublish(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click='forcePublish()']")));
        element = driver.findElement(By.xpath("//button[@ng-click='forcePublish()']"));
        return element;
	}	
	
	/******************************************************************************************************************
	 * Description	: When swap option is selected sometimes it ask for change reasons and then publish button displays
	 * @param 		: driver
	 * @return		: element
	 ******************************************************************************************************************/
	public static WebElement btn_ForcePublish1(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Publish')]")));
        element = driver.findElement(By.xpath("//button[contains(text(),'Publish')]"));
        return element;
	}	
	public static WebElement poup_changeReason(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ui-id-7']")));
        element = driver.findElement(By.xpath("//span[@id='ui-id-7']"));
        return element;
	}
	public static WebElement window_FlightDetails(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='ui-id-49']")));
        element = driver.findElement(By.xpath("//div[@aria-labelledby='ui-id-49']"));
        return element;
	}
	public static WebElement btn_CloseFlightDetailsWindow(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='ui-id-49']//button[@title='Close']")));
        element = driver.findElement(By.xpath("//div[@aria-labelledby='ui-id-49']//button[@title='Close']"));
        return element;
	}
	
	public static WebElement msg_toolTipOfFlight(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='W1-tooltip']")));
        element = driver.findElement(By.xpath("//div[@aria-labelledby='ui-id-49']//button[@title='Close']"));
        return element;
	}
	
	public static WebElement msg_deleteFlight(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='sp-msg-box']//span[contains(text(),'Flight(s) deleted successfully.')]")));
        element = driver.findElement(By.xpath("//ul[@class='sp-msg-box']//span[contains(text(),'Flight(s) deleted successfully.')]"));
        return element;
	}
	
	public static WebElement dropDown_reason(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='select2-drop']/div//input")));
        element = driver.findElement(By.xpath("//body/div[@id='select2-drop']/div//input"));
        return element;
	}
	
	public static WebElement dropDown_resultOfReason(WebDriver driver){
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select2-result-label']")));
        element = driver.findElement(By.xpath("//div[@class='select2-result-label']"));
        return element;
	}
	
	/*****************************************************************
	 * Description	:	Opens the GanttScreen in iFlightNeo application
	 * @param		:	driver
	 *****************************************************************/
	public static void select_Gantt(WebDriver driver) 
	{
		
		airport_Option(driver);
		comm.performAction(aircraft_MainMenu(driver), "CLICK", "", "select Aircraft Option from top menu");
		List<WebElement> nav_dropdown=driver.findElements(By.xpath("//ul[@id='nav']//li//ul//li//a"));
		for(int i=0;i<nav_dropdown.size();i++)
		{
			WebElement element =nav_dropdown.get(i);
			
			String subHeader=element.getAttribute("innerHTML");
				
			if(subHeader.contains("Gantt")) 
			{
				comm.performAction(menu_aircraftGantt(driver), "CLICK", "", "select Gantt Option");
			}
		}
		comm.performAction(btn_DayZoom(driver), "CLICK", "", "Click on DayZoom button");
	}
	
	
	/************************************************************************************
	 * Description	:	Finds the flight in the Gantt Screen of iFlightNeo Application
	 * @param 		:	driver
	 * @param 		:	flightNo
	 * @param 		:	Date
	 * @param 		:	DepCode
	 * @param 		:	ArrCode
	 * @throws 		:	Exception
	 ***********************************************************************************/
	public static void Find_flight(WebDriver driver, String flightNo, String Date, String DepCode, String ArrCode) throws Exception
	{
		
		comm.performAction(find_Flight(driver), "CLICK", "", "Click on Find Flight Option");
		Actions action =new Actions(driver);
		action.moveToElement(find_Flight_popUp(driver));
		comm.performAction(flight_No(driver), "SET", flightNo, "Enter Flight Number");
		comm.performAction(FF_DateField(driver), "SET", Date, "Enterting Flight Date");
		comm.performAction(FF_DepFieldClick(driver), "CLICK", "", "Click on Dep Field");
		comm.performAction(FF_EnterDEP(driver), "SET", DepCode, "Enter DepCode");
		comm.performAction(FF_DEPARRSearchResult(driver), "CLICK", "", "Selecting DepCode from result displayed");
		comm.performAction(FF_ArrFieldClick(driver), "CLICK", "", "Click on Arr field");
		comm.performAction(FF_EnterArr(driver), "SET", ArrCode, "Entering Arrival Code");
		comm.performAction(FF_DEPARRSearchResult(driver), "CLICK", "", "Selecting ArrCode from result displayed");
		comm.performAction(FF_search(driver), "CLICK", "", "Click on search");
		FF_Escape(driver).sendKeys(Keys.ESCAPE);
		Thread.sleep(3000);
		comm.performAction(goto_CurrentDate(driver), "CLICK", "", "click on current date");
	}
	
	/*******************************************************************************
	 * Description	: Delete the flight from Gantt Screen of iFlightNeo Application
	 * @param 		: driver
	 * @param 		: select
	 * @throws 		: Exception
	 *******************************************************************************/
	public static void DeleteFlight(WebDriver driver, String select) throws Exception 
	{
		Actions action1 =new Actions(driver);
		action1.moveToElement(rightClick_Menu(driver));
		List<WebElement> rightClick_drpdown = rightClick_dropdown(driver);
		for(int j=0;j<rightClick_drpdown.size();j++)
		{
			WebElement data =rightClick_drpdown.get(j);
			
			if(data.getAttribute("innerHTML").contains(select))
			{
				data.click();
				break;
			}
			
		}
		comm.performAction(Delete_Flight(driver), "CLICK", "", "Selecting Option Delete flight");
		confirm_DeleteFlightPopup(driver);
		comm.performAction(Yes_DeleteFlight(driver), "CLICK", "", "Clicking Yes to confirming Delete Option");
	}
	
	/*************************************************************************************************************************
	 * Description	: Get the Aircraft Registration of a flight by opening Flight details window and returns the string value
	 * @param 		: driver
	 * @return		: readAircraftReg
	 * @throws 		: Exception
	 *************************************************************************************************************************/
	public static String readAircraftReg(WebDriver driver) throws Exception
	{
		//Waiting for flight details to open
		 wait=new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='ui-id-49']")));
		 WebElement element2 = driver.findElement(By.xpath("//div[@aria-labelledby='ui-id-49']"));
		 //htmlLib.logReport("flight Details Screen", "Flight Details Screen Open success", "Pass", true);
		 Actions action = new Actions(driver);
		 action.moveToElement(element2).build().perform();

		 // To enable AircraftRegistrationfield text box
		 JavascriptExecutor javascript = (JavascriptExecutor) driver;
		 String toenable = "document.getElementsByName('flightLegDetailsWidgetModel_aircraftReg')[0].removeAttribute('disabled');";
		 javascript.executeScript(toenable);
		 Thread.sleep(3000);
		 //Storing the Aircraft registration value of first flight in a string variable
		 String AircraftRegistration_before = driver.findElement(By.name("flightLegDetailsWidgetModel_aircraftReg")).getAttribute("value");
		 System.out.println("Aircraft Registration :" + AircraftRegistration_before);
		 driver.findElement(By.xpath("//div[@aria-labelledby='ui-id-49']")).sendKeys(Keys.ESCAPE);
		 return AircraftRegistration_before;
		 
	}
	
	/********************************************************************************************************
	 * Description	: Select Swap Selected option after right click on flight and perform the swap operation
	 * @param 		: driver
	 * @throws 		: Exception
	 ********************************************************************************************************/
	@SuppressWarnings("unused")
	public static void swapFlight(WebDriver driver) throws Exception
	{
		 //After selecting multiple flights mouse right click for swap option
		 List<WebElement>List=driver.findElements(By.xpath("//ul[@class='context-menu-list context-menu-root']//li//span"));
		  for(int j=0;j<List.size();j++) 
		  { 
			  WebElement data =List.get(j);
			  if(data.getAttribute("innerHTML").contains("Swap Selected")) 
			  { 
				  data.click();
			  	  break; 
			  }
		  
		  }
		 //Confirming the Flight swap Operation
		 container_swapList(driver);
		 Actions action = new Actions(driver);
		 action.moveToElement(container_swapList(driver)).build().perform();
		 comm.performAction(btn_ForcePublish(driver), "Click", "", "Click on force Publish button");
		 Thread.sleep(2000);
		 
		 try 
		 {
			 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ui-id-7']")));
			 //WebElement ChangeReason_popup = driver.findElement(By.xpath("//span[@id='ui-id-7']"));
			 action.moveToElement(poup_changeReason(driver)).build().perform();
			 List<WebElement> list_reason= driver.findElements(By.xpath("//td[@ng-init='lowerIndex = $index']//div//a"));
			 for(int i=0; i<list_reason.size();i++) 
			 {
				  
				 WebElement reason_value= list_reason.get(0);
				 reason_value.click();
				 comm.performAction(dropDown_reason(driver), "SET", "ATC", "setting reason as ATC");
				 comm.performAction(dropDown_resultOfReason(driver), "Click", "", "Selecting ATC as reason code from list");
				  
				 reason_value=list_reason.get(4);
				 reason_value.click();
				 comm.performAction(dropDown_reason(driver), "SET", "ATC", "setting reason as ATC");
				 comm.performAction(dropDown_resultOfReason(driver), "Click", "", "Selecting ATC as reason code from list");
				 
				 reason_value=list_reason.get(8);
				 reason_value.click();
				 comm.performAction(dropDown_reason(driver), "SET", "ATC", "setting reason as ATC");
				 comm.performAction(dropDown_resultOfReason(driver), "Click", "", "Selecting ATC as reason code from list");
				 
				 reason_value=list_reason.get(12);
				 reason_value.click();
				 comm.performAction(dropDown_reason(driver), "SET", "ATC", "setting reason as ATC");
				 comm.performAction(dropDown_resultOfReason(driver), "Click", "", "Selecting ATC as reason code from list");
				 
				  break; 
				  }
				  
			 comm.performAction(btn_ForcePublish1(driver), "click", "", "Swap flight confirmed");
				  
			 Thread.sleep(4000);
		 } 
		 catch (Exception e) 
		 {
			System.out.println("No Change reason Pop-up appeared, so proceed with only publish option");
			
		 }
	}
}
