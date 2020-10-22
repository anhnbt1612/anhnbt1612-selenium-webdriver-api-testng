package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Firefox_Chrome_Edge {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	
	@Test
	public void TC_01_Run_on_Firefox() {
		driver = new FirefoxDriver();
		
		driver.get("http://google.com/");
		
		driver.quit();
	}
	
	@Test
	public void TC_02_Run_on_Chrome() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("http://google.com/");
		
		driver.quit();
	}
	
	@Test
	public void TC_01_Run_on_Edge_Chromium() {
		System.setProperty("webdriver.edge.driver", projectFolder + "\\browserDrivers\\msedgedriver.exe");
		
		driver = new EdgeDriver();
		
		driver.get("http://google.com/");
		
		driver.quit();
	}

}
