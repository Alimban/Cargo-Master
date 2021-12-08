package iFlightNeo.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.EY_iFlightNeo_HomePage;
import pageObjects.EY_iFlightNeo_LoginPage;
import utilities.CollectTestData;

public class NeoOps_AAF_TC058 {
	public utilities.Html_Report_Library htmlLib = new utilities.Html_Report_Library();
	public utilities.Common_Library comm = new utilities.Common_Library();
	String[] lists = this.getClass().getName().split("\\.");
	String tcName = lists[lists.length - 1];
	public WebDriver driver;
	static Screen scn;
	@BeforeTest
	void setUp() {
		htmlLib.configureReport(driver, tcName);
		CollectTestData.main(tcName);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void NeoOps_AAF_TC059_Test2() throws Exception 
	 {
		 //Collecting data from Excel and FlightImages Path
		 String sit_URL=CollectTestData.URL;
		 String sit_Username =CollectTestData.UserName;
		 String sit_Password =CollectTestData.Password;
		 String Date =CollectTestData.flight_Date;
		 int flighNo =CollectTestData.flight_Number;
		 String departureAirport =CollectTestData.dep_Code;
		 String arrivalAirport =CollectTestData.arr_Code;
		 String EY564 = System.getProperty("user.dir")+ "\\TestData\\FlightEY564.png";
		 String EY565 = System.getProperty("user.dir")+ "\\TestData\\FlightEY565.png";
		 String EY565_blank = System.getProperty("user.dir")+ "\\TestData\\FlightEY565_blank.png";
		 String EY344 = System.getProperty("user.dir")+ "\\TestData\\FlightEY344.png";
		 String EY345 = System.getProperty("user.dir")+ "\\TestData\\FlightEY345.png";
		 String EY345_color = System.getProperty("user.dir")+ "\\TestData\\FlightEY345_color.png";
		 
		 //Launching the driver, application and Login
		 driver = EY_iFlightNeo_LoginPage.launchApplication(driver, sit_URL);
		 driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 EY_iFlightNeo_LoginPage.login_Applicaiton(driver, sit_Username, sit_Password);
		 htmlLib.logReport("Login Functionality is success", "Login sucess", "Pass", true);
		 //Opening the Gantt Screen and Finding the Flight
		 EY_iFlightNeo_HomePage.select_Gantt(driver);
		 EY_iFlightNeo_HomePage.Find_flight(driver, ""+flighNo, Date, departureAirport, arrivalAirport);
		 htmlLib.logReport("Gantt Screen Opened", "Gantt Screen Open success", "Pass", true);
		 //Implements the WebDriverWait and Action interface for further purpose
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 Actions action = new Actions(driver);
		//Implementing the Screen and Pattern using SikuliScript
		 scn = new Screen();
		 Pattern Flight1 = new Pattern(EY564);		 
		//Identifying the flight using images and performing double mouse click
		 scn.wait(Flight1, 9000);
		 scn.doubleClick(Flight1);
		 //gets aircraft registration of first flight before swap
		 String aircraftRegistrationBefore=EY_iFlightNeo_HomePage.readAircraftReg(driver);
		 comm.performAction(EY_iFlightNeo_HomePage.btn_CloseFlightDetailsWindow(driver), "click", "", "Closed the flight details");
		 comm.performAction(EY_iFlightNeo_HomePage.panRectangle(driver), "click", "", "Clicking on PanRectangle Option");		 
		 scn.wheel(Button.WHEEL_UP, 2);
		 try {
			 	Pattern Flight2 = new Pattern(EY565);
				scn.dragDrop(Flight1.targetOffset(-100, -20), Flight2.targetOffset(0, 10));
			  
			  } 
		 catch (FindFailed e) 
		 	{
			  
			 	Pattern Flight2_blank = new Pattern(EY565_blank);
			 	scn.dragDrop(Flight1.targetOffset(-100, -20), Flight2_blank.targetOffset(0,10)); 
			 }	 
		 //Scrolling down or up for next flight trip
		 int scroll_cnt=45;
		 for(int i=0; i<scroll_cnt;i++) 
		 {
		  scn.wheel(Button.WHEEL_DOWN, 3);		  
		  Pattern Flight3 = new Pattern(EY344);
		  Match m = scn.exists(Flight3.similar(0.92));
		  if(m!=null) 
		  { 
			  scn.keyDown(Key.CTRL);  
			  try 
			  {		
				  	// If flight image found with flight number
				  	Pattern Flight4 = new Pattern(EY345);
					scn.dragDrop(Flight3.targetOffset(-100, -20), Flight4.targetOffset(0, 10));
					scn.keyUp(Key.CTRL);
					Pattern Flight4_color = new Pattern(EY345_color);
					scn.rightClick(Flight4_color.similar(0.95));
			  } 
			  catch (FindFailed e) 
			  {
				  	// If flight image found without flight number
					Pattern Flight4 = new Pattern(EY345);
					scn.dragDrop(Flight3.targetOffset(-100, -20), Flight4.targetOffset(0, 10));
					scn.keyUp(Key.CTRL);
					Pattern Flight4_color_blank = new Pattern(EY345_color);
					scn.rightClick(Flight4_color_blank.similar(0.95));
				}
			  htmlLib.logReport("Selected multiple flights and right clicked", "Selected multiple flights and right clicked success", "Pass", true);
			  scn.keyUp(Key.CTRL);
			  break; 
		  }
		 }
		 EY_iFlightNeo_HomePage.swapFlight(driver);
		 EY_iFlightNeo_HomePage.Find_flight(driver, ""+flighNo, Date, departureAirport, arrivalAirport);
		 Flight1 = new Pattern(EY564);		 
		 //Identifying the flight using images and performing double mouse click
		 scn.wait(Flight1, 9000);
		 scn.doubleClick(Flight1);
		 //gets aircraft registration of first flight before swap
		 String aircraftRegistrationafter=EY_iFlightNeo_HomePage.readAircraftReg(driver);
		 //condition to check the flight swap and end the test case
		 boolean Swap_Sucess=aircraftRegistrationBefore.contentEquals(aircraftRegistrationafter);
		 if(!Swap_Sucess==true) {
			 htmlLib.logReport("Swap Flights Operation", "Swap flights success", "Pass", true);
		 }
		 driver.quit();
	 }
	
	@AfterTest
	public void closeTest() {
		htmlLib.closeReport(tcName);
	}
}
