package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(alwaysRun=true)
	@Parameters({"browser","os"})
	public void setUp(String browser, String os) throws IOException, URISyntaxException
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch (browser.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver();break;
		case "edge": driver=new EdgeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		default: System.out.println("Invalid broser name");return;
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("url"));
	}
	
		if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		    DesiredCapabilities cap=new DesiredCapabilities();	
			
		    if (os.equalsIgnoreCase("windows"))
		    {
		    	cap.setPlatform(Platform.WIN10);	
		    }
		    
		    switch (browser.toLowerCase())
		    {
		    case "chrome": cap.setBrowserName("chrome");break;
		    default:System.out.println("Invalid browser");return;
		    }
    driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
    driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(p.getProperty("url"));	
	}	
	}
	
	public String captureScreen(String tname)
	{
    String timestamp=new SimpleDateFormat("dd-MM-YYYY_HH.mm.ss").format(new Date());
	String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"-"+timestamp+".png";
	TakesScreenshot ts=(TakesScreenshot)driver;
	File sourceFile=ts.getScreenshotAs(OutputType.FILE);
	File targetFile=new File(screenshotPath);
	sourceFile.renameTo(targetFile);
	
	return screenshotPath;
	}
	
	
	
	
    @AfterClass(alwaysRun=true) 
    public void tearDown()
    {
    	driver.quit();
    }


}
