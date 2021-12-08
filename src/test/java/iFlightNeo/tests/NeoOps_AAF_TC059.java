package iFlightNeo.tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Button;
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

public class NeoOps_AAF_TC059 {
	public utilities.Html_Report_Library htmlLib = new utilities.Html_Report_Library();
	public utilities.Common_Library comm = new utilities.Common_Library();
	String[] lists = this.getClass().getName().split("\\.");
	String tcName = lists[lists.length - 1];
	static WebDriver driver;
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
		 String EY845 ="C:\\Users\\MohammedRafiShaik\\eclipse\\iFlight_Neo\\TestData\\FlightEY845.png";
		 String EY846 ="C:\\Users\\MohammedRafiShaik\\eclipse\\iFlight_Neo\\TestData\\FlightEY846.png";
		 String EY945 ="C:\\Users\\MohammedRafiShaik\\eclipse\\iFlight_Neo\\TestData\\FlightEY945.png";
		 String EY946 ="C:\\Users\\MohammedRafiShaik\\eclipse\\iFlight_Neo\\TestData\\FlightEY946.png";
		 
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
		 Pattern Flight1 = new Pattern(EY845);
		 Pattern Flight2 = new Pattern(EY846);
		 //Identifying the flight using images and performing double mouse click
		 scn.wait(Flight1, 9000);
		 scn.doubleClick(Flight1);
		//Gets Aircraft registration number before flight swap
		 String AircraftRegistration_before=EY_iFlightNeo_HomePage.readAircraftReg(driver);
		 //Selecting the second segment of first flight trip
		 scn.keyDown(Key.CTRL);
		 scn.click(Flight2.similar(0.95));
		 scn.keyUp(Key.CTRL);
		 //Scrolling down or up for next flight trip
		 int scroll_cnt=45;
		 for(int i=0; i<scroll_cnt;i++) 
		 {
		  scn.wheel(Button.WHEEL_DOWN, 3);
		  Pattern Flight3 = new Pattern(EY945);
		  Pattern Flight4 = new Pattern(EY946);
		  Match m = scn.exists(Flight3.similar(0.92));
		  if(m!=null) 
		  { 
			  scn.keyDown(Key.CTRL); 
			  scn.click(Flight3.similar(0.95));
			  scn.keyDown(Key.CTRL);
			  scn.rightClick(Flight4.similar(0.95));
			  htmlLib.logReport("Selected multiple flights and right clicked", "Selected multiple flights and right clicked success", "Pass", true);
			  break; 
		  }
		  
		  }
		 //confirm swap operation 
		 EY_iFlightNeo_HomePage.swapFlight(driver);
		 //Again finding the first trip flight to verify the aircraft registration change
		 EY_iFlightNeo_HomePage.Find_flight(driver, ""+flighNo, Date, departureAirport, arrivalAirport);
		 scn.wait(Flight1, 9000);
		 scn.doubleClick(Flight1);
		 //Gets Aircraft registration number after flight swap
		 String AircraftRegistration_after=EY_iFlightNeo_HomePage.readAircraftReg(driver);
		 //condition to check the flight swap and end the test case
		 boolean Swap_Sucess=AircraftRegistration_before.contentEquals(AircraftRegistration_after);
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