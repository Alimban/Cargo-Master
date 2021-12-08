package iFlightNeo.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.EY_iFlightNeo_HomePage;
import pageObjects.EY_iFlightNeo_LoginPage;
import utilities.CollectTestData;
import utilities.Common_Library;

public class NeoOps_AAF_TC054{

    public utilities.Html_Report_Library htmlLib = new utilities.Html_Report_Library();
    public utilities.Common_Library comm = new utilities.Common_Library();
    String[] lists = this.getClass().getName().split("\\.");
    String tcName = lists[lists.length-1];
	static WebDriver driver;
	static WebDriverWait wait;
	
	 @BeforeTest
	    void setUp() {
	    	htmlLib.configureReport(driver, tcName);
	    	CollectTestData.main(tcName);
	    }
	
	@Test
	public void NeoOps_AAF_TC054_Test() throws Exception {
		
		//Collecting data from Excel and FlightImages Path
		 String sit_URL=CollectTestData.URL;
		 String sit_Username =CollectTestData.UserName;
		 String sit_Password =CollectTestData.Password;
		 String Date =CollectTestData.flight_Date;
		 int flighNo =CollectTestData.flight_Number;
		 String departureAirport =CollectTestData.dep_Code;
		 String arrivalAirport =CollectTestData.arr_Code;
		 String Image_Path ="C:\\Users\\MohammedRafiShaik\\eclipse-workspace\\iFlight_Neo\\TestData\\FlightEY645.png";
		 String expectedMessage = "Flight(s) deleted successfully.";
		
		//Launching the driver, application and Login
		driver= EY_iFlightNeo_LoginPage.launchApplication(driver, sit_URL);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		EY_iFlightNeo_LoginPage.login_Applicaiton(driver, sit_Username, sit_Password);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);		
		//Opening the Gantt Screen and Finding the Flight
		EY_iFlightNeo_HomePage.select_Gantt(driver);
		EY_iFlightNeo_HomePage.Find_flight(driver, ""+flighNo, Date, departureAirport, arrivalAirport);
		htmlLib.logReport("Find flight Success", "flight Listed on Top", "Pass", true);
		//Implementing the Screen and Pattern using SikuliScript to select flight and right click in gantt screen
		Common_Library.clickFlightGantt(driver, Image_Path, "rightClick");
		htmlLib.logReport("Right Clicked on flight", "Right click flight success", "Pass", true);
		 //Deleting the flight 
		 EY_iFlightNeo_HomePage.DeleteFlight(driver,"Delete"); 
		 System.out.println("everything success"); 
		 //Verify the successfully deleted message is displayed on gantt screen post deletion of flight
		 String actualDeletemsg=EY_iFlightNeo_HomePage.msg_deleteFlight(driver).getAttribute("innerHTML");
		 Assert.assertEquals(actualDeletemsg, expectedMessage);
		 driver.quit();
		 
	}
	
	@AfterTest
	public void closeTest() {
		htmlLib.closeReport(tcName);
	}
	
}
