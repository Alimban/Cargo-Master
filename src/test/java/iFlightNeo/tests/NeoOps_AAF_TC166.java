package iFlightNeo.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.EY_iFlightNeo_HomePage_Messages;
import pageObjects.EY_iFlightNeo_LoginPage;
import utilities.CollectTestData;

public class NeoOps_AAF_TC166 {
	
	public utilities.Html_Report_Library htmlLib = new utilities.Html_Report_Library();
	public utilities.Common_Library comm = new utilities.Common_Library();
	String[] lists = this.getClass().getName().split("\\.");
	String tcName = lists[lists.length - 1];
	static WebDriver driver;
	String emailID="EYVChhaperia@etihadppe.ae";
	
	@BeforeTest
	void setUp() {
		htmlLib.configureReport(driver, tcName);
		CollectTestData.main(tcName);
	}

	@Test
	public void NeoOps_AAF_TC166_sendReport() throws Exception
	{
		//Collecting data from Excel and FlightImages Path
		 String sit_URL=CollectTestData.URL;
		 String sit_Username =CollectTestData.UserName;
		 String sit_Password =CollectTestData.Password;
		 String startDate =CollectTestData.startDate;
		 String endDate =CollectTestData.endtDate;
		 String type =CollectTestData.type;
		 
		 //Launching the driver, application and Login
		 driver = EY_iFlightNeo_LoginPage.launchApplication(driver, sit_URL);
		 driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 EY_iFlightNeo_LoginPage.login_Applicaiton(driver, sit_Username, sit_Password);
		 driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		 //Filling data and getting results for Email Notification Form
		 EY_iFlightNeo_HomePage_Messages.SchedulePublication_emailNotificaitonForm(driver, startDate, endDate, type);
		 htmlLib.logReport("Email Preview to be sent", "Email Preview verify sucess", "Pass", true);
		 //Reading content from the Email
		 EY_iFlightNeo_HomePage_Messages.emailPreviewVerify(driver);
		 
		 //Sending Email to the concerned mail ID's
		 EY_iFlightNeo_HomePage_Messages.To_textBox(driver).click();
		 EY_iFlightNeo_HomePage_Messages.To_textBox(driver).sendKeys(emailID);
		 EY_iFlightNeo_HomePage_Messages.sendNowbutton(driver).click();
		 Thread.sleep(3000);
	}
	
	public void NeoOps_AAF_TC166_emailAttachmentDownload()
	{
		
	}
	
	
	@AfterTest
	public void closeTest() {
		htmlLib.closeReport(tcName);
	}
}
