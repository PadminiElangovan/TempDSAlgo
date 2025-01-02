package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import common.ConfigReader;
import common.LoggerLoad;

public class DriverConfig {
	static String URL = ConfigReader.getProperty("URL");
	protected static final  ThreadLocal<WebDriver> ThreadLocaldriver = new ThreadLocal<>();
	public  static void getdriver(String browser)
	{
		WebDriver driver=null;
		
			if (browser.equalsIgnoreCase("chrome"))
			{
				driver= new ChromeDriver();
			}
			else if (browser.equalsIgnoreCase("firefox"))
			{
				driver= new FirefoxDriver();
			}
			else if (browser.equalsIgnoreCase("edge"))
			{
				driver= new EdgeDriver();
			}
			else
			{
				driver= new ChromeDriver();
			}
		
		ThreadLocaldriver.set(driver);
		getDriverInstance().manage().window().maximize();
		getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		getDriverInstance().get(URL);
		LoggerLoad.info("DsAlgo Portal Url :" + URL );
	}
	
	public static  WebDriver getDriverInstance() {
		LoggerLoad.info("Driver Instance is created");
		return ThreadLocaldriver.get();
		
	}
	
	public static void quitdriver()
	{
		getDriverInstance().manage().deleteAllCookies();
		getDriverInstance().quit();
		ThreadLocaldriver.remove();


   }
}