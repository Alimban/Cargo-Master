package iFlightNeo.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.EY_iFlightNeo_HomePage;
import pageObjects.EY_iFlightNeo_LoginPage;
import utilities.CollectTestData;

public class NeoOps_AAF_TC057 {
	
	 public utilities.Html_Report_Library htmlLib = new utilities.Html_Report_Library();
	 public utilities.Common_Library comm = new utilities.Common_Library();
	 String[] lists = this.getClass().getName().split("\\.");
	 String tcName = lists[lists.length-1];
	 static WebDriver driver;
	 static Screen scn;
		
	@BeforeTest
	void setUp() {
		htmlLib.configureReport(driver, tcName);
		CollectTestData.main(tcName);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void NeoOps_AAF_TC057_Test() throws Exception
	{
		//Collecting data from Excel and FlightImages Path
		String sit_URL=CollectTestData.URL;
		String sit_Username =CollectTestData.UserName;
		String sit_Password =CollectTestData.Password;
		String Date =CollectTestData.flight_Date;
		int flighNo =CollectTestData.flight_Number;
		String departureAirport =CollectTestData.dep_Code;
		String arrivalAirport =CollectTestData.arr_Code;
		String Image_AircraftReg = System.getProperty("user.dir")+ "\\TestData\\AircraftRegstration_1.png";
		String firstFlight = System.getProperty("user.dir")+ "\\TestData\\FlightEY709.png";
		String secondFlight = System.getProperty("user.dir")+ "\\TestData\\FlightEY710.png";
		 
		//Launching the driver, application and Login
		driver= EY_iFlightNeo_LoginPage.launchApplication(driver, sit_URL);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		EY_iFlightNeo_LoginPage.login_Applicaiton(driver, sit_Username, sit_Password);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 
		//Opening the Gantt Screen and Finding the Flight
		EY_iFlightNeo_HomePage.select_Gantt(driver);
		EY_iFlightNeo_HomePage.Find_flight(driver, ""+flighNo, Date, departureAirport, arrivalAirport);
		
		//Implementing the Screen and Pattern using SikuliScript
		scn = new Screen();
		Pattern Flight1 = new Pattern(firstFlight);
		//Identifying the flight using images and performing double mouse click
		scn.wait(Flight1, 9000);
		scn.doubleClick(Flight1);
		//gets aircraft registration of first flight before swap
		String aircraftRegistrationBefore=EY_iFlightNeo_HomePage.readAircraftReg(driver); 
		scn.click(Flight1);
		scn.keyDown(Key.CTRL);
		Pattern Flight2 = new Pattern(secondFlight);
		scn.click(Flight2);
		scn.keyUp(Key.CTRL);
		Pattern aircraft_Registration = new Pattern(Image_AircraftReg);
		scn.dragDrop(Flight1.targetOffset(0, 0), aircraft_Registration.targetOffset(300, 0).similar(0.92));
		
		Actions action = new Actions(driver);
		action.moveToElement(EY_iFlightNeo_HomePage.poup_changeReason(driver)).build().perform();
		List<WebElement> list_reason= driver.findElements(By.xpath("//td[@ng-init='lowerIndex = $index']//div//a"));
		for(int i=0; i<list_reason.size();i++) 
		 {
			  
			 WebElement reason_value= list_reason.get(0);
			 reason_value.click();
			 comm.performAction(EY_iFlightNeo_HomePage.dropDown_reason(driver), "SET", "ATC", "setting reason as ATC");
			 comm.performAction(EY_iFlightNeo_HomePage.dropDown_resultOfReason(driver), "Click", "", "Selecting ATC as reason code from list");
			 reason_value=list_reason.get(4);
			 reason_value.click();
			 comm.performAction(EY_iFlightNeo_HomePage.dropDown_reason(driver), "SET", "ATC", "setting reason as ATC");
			 comm.performAction(EY_iFlightNeo_HomePage.dropDown_resultOfReason(driver), "Click", "", "Selecting ATC as reason code from list"); 
			 break;
		 }
		comm.performAction(EY_iFlightNeo_HomePage.btn_ForcePublish1(driver), "click", "", "Swap flight confirmed");
		EY_iFlightNeo_HomePage.Find_flight(driver, ""+flighNo, Date, departureAirport, arrivalAirport);
		Flight1 = new Pattern(firstFlight);		 
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
