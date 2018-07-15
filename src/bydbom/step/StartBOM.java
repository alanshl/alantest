package bydbom.step;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class StartBOM {
	public static void main(String[] args)
	{
		String path="C:\\software\\chromedriver_win32\\chromedriver.exe";
		try {
			  ChromeDriverService service = new ChromeDriverService.Builder()
					  .usingDriverExecutable(new File("C:\\software\\chromedriver_win32\\chromedriver.exe"))
					  .usingAnyFreePort()
					  .build();
			  service.start();
			  
			  WebDriver driver=new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
			  
			  driver.get("http://192.168.1.61:8080/static/index.html#");
			  driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			
		}

	}
	
}
