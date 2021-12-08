package iFlightNeo.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import pageObjects.EY_iFlightNeo_LoginPage;

public class SampleTest {
	public WebDriver driver;
	
	@Test
	public void work() {
		
		String url = "https://iflightneoopsppe.etihad.ae/iflight/web/loginpage";
		String Username = "Autotester";
		String Password = "Etihad@123";
		
		/*
		 * System. setProperty("webdriver.gecko.driver",
		 * "C:\\Program Files\\Java\\geckodriver.exe"); driver = new FirefoxDriver();
		 * driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 * driver.manage().window().maximize(); driver.get(url);
		 */
		
		driver = EY_iFlightNeo_LoginPage.launchApplication(driver, url);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		EY_iFlightNeo_LoginPage.login_Applicaiton(driver, Username, Password);
	}
}
