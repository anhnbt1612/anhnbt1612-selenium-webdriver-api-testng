package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
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
	public void TC_01_Fixed_Popup() {
		driver.get("https://zingpoll.com/");
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal-content']//form[@id='loginForm']")).isDisplayed());
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());

	}
	
	@Test(enabled=false)
	public void TC_02_Random_Popup_in_DOM() {
		driver.get("https://blog.testproject.io/");
		if(driver.findElement(By.xpath("//div[@id='mailch-bg']")).isDisplayed()){
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(2);	
		}
		
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("#search-2 .glass")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#primary .page-title")).getText().contains("Selenium"));
		List<WebElement> postTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		int numberOfTitle = postTitle.size();
		System.out.println(numberOfTitle);
		for (WebElement title : postTitle) {
			Assert.assertTrue(title.getText().contains("Selenium"));
		}
	}
	
	@Test
	public void TC_03_Ramdom_Popup_not_in_DOM() {
		driver.get("https://bizbooks.vn/");
		sleepInSecond(5);
		
		//override để tìm element nhanh hơn
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> randomPopup = driver.findElements(By.xpath("//div[@class='mepuzz-bn-modal']//div[@class='modal-info']"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		if(randomPopup.size() > 0 && randomPopup.get(0).isDisplayed()) {
			driver.findElement(By.xpath("//button[@class='mepuzz-btn-close']")).click();
			sleepInSecond(2);
			System.out.println("Có Popup");
		}
		
		driver.findElement(By.xpath("//img[@alt='Doanh nhân và Doanh nghiệp']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://bizbooks.vn/danh-muc/tu-sach-doanh-nhan/doanh-nhan-doanh-nghiep/");
		System.out.println("không popup");
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
