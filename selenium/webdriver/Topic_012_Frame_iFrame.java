package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_012_Frame_iFrame {
	WebDriver driver;
	WebDriverWait explicitWait;
	String LocalFolder = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test(enabled=false)
	public void TC_01_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("abcxyz");
		driver.findElement(By.xpath("//table[@class='lForm']//img[@alt='continue']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		
	}
	
	@Test
	public void TC_02_iFrame() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fb-page fb_iframe_widget']//iframe[@title='fb:page Facebook Social Plugin']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Automation FC']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText(), "1,937 likes");
		
		driver.switchTo().defaultContent();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
