package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EY_iFlightNeo_LoginPage {
	
	public static utilities.Common_Library comm = new utilities.Common_Library();
	private static WebElement element = null;
	private static WebDriverWait wait;
	
	public static WebElement txtbx_UserName(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@id='user_id']"));
        return element;
	}
	
	 public static WebElement txtbx_Password(WebDriver driver){
	        element = driver.findElement(By.xpath("//input[@id='user_password']"));
	        return element;
	    }
	
	 public static WebElement btn_LogIn(WebDriver driver){
	        element = driver.findElement(By.xpath("//input[@name='submit']"));
	        return element;
	    }
	
	 public static WebElement dateText(WebDriver driver){
		 	wait = new WebDriverWait(driver,40);
		 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Oct')]")));
	        element = driver.findElement(By.xpath("//span[contains(text(),'Oct')]"));
	        return element;
	    }
	
	 public static WebElement pleaseWait_spinner(WebDriver driver){
			wait=new WebDriverWait(driver,40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Please Wait')]")));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_aircraft']")));
	        element = driver.findElement(By.xpath("//span[contains(text(),'Please Wait')]"));
	        return element;
		}
	
	/*****************************************
	 * Launch the Google Chrome Web Browser and iFlightNeo application URL
	 * @param driver
	 * @param url
	 * @return
	 *****************************************/
	public static WebDriver launchApplication(WebDriver driver, String url)
	{
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
    	// Options
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--headless");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	driver.get(url);
    	return driver;

	}

	/*****************************************
	 * Login into the iFlgihtNeo Application
	 * @param driver
	 * @param userName
	 * @param pwd
	 ******************************************/
    public static void login_Applicaiton(WebDriver driver, String userName, String pwd) 
    {
    	WebDriverWait wait = new WebDriverWait(driver, 40);
    	wait.until(ExpectedConditions.visibilityOf(txtbx_UserName(driver)));
    	comm.performAction(txtbx_UserName(driver), "SET", userName, "Entered UsernName");
    	comm.performAction(txtbx_Password(driver), "SET", pwd, "Entered Password");
    	comm.performAction(btn_LogIn(driver), "CLICK", "", "Click on Login button");
    	dateText(driver);
    }
    
    
}
