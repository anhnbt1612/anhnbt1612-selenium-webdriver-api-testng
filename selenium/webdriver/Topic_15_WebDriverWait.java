package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_WebDriverWait {
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
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

	}

	@Test
	public void TC_01_Element_Visible() {
		//Điều kiện để 1 element display/visible:
		// - element có trên UI (bắt buộc)
		// - element có trong DOM (bắt buộc)
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='advice-required-entry-email']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());

	}
	
	@Test
	public void TC_02_Element_Invisible_In_DOM() {
		//Điều kiện để 1 element undisplay/ invisible
		// - element không có trên UI (bắt buộc)
		// - element có trong DOM (case 1)
		
		driver.navigate().refresh();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));

		
	}
	
	@Test
	public void TC_02_Element_Invisible_Not_In_DOM() {
		//Điều kiện để 1 element undisplay/ invisible
		// - element không có trên UI (bắt buộc)
		// - element không có trong DOM (case 2)
		driver.navigate().refresh();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
		
		
	}
	
	@Test
	public void TC_03_Element_Presence() {
		//Điều kiện để 1 element presence
		// - element không có trên UI (Case 1)
		// - element có trên UI (Case 2)
 		// - element có trong DOM (bắt buộc)
		driver.navigate().refresh();
		
		//case 1
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		//case 2
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='footer']//a[text()='My Account']")));

	}
	
	@Test
	public void TC_04_Element_Staleness() {
		//Điều kiến để 1 element staleness
		// - element không có trên UI (bắt buộc)
		// - element không có trong DOM (bắt buộc)
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
		
		driver.navigate().refresh();
		
		explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
		
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
