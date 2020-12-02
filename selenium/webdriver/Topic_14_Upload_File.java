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

public class Topic_14_Upload_File {
	WebDriver driver;
	WebDriverWait explicitWait;
	String LocalFolder = System.getProperty("user.dir");
	String Step1Image ="1.jpg";
	String Step2Image ="2.jpg";
	String Step3Image ="3.jpg";
	
	String Step1Path = LocalFolder + "\\uploadFile\\" + Step1Image;
	String Step2Path = LocalFolder + "\\uploadFile\\" + Step2Image;
	String Step3Path = LocalFolder + "\\uploadFile\\" + Step3Image;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_SendKeys() {
		//Work for all browser/OSx
		//Không thao tác với Open File Dialog
		//1 file hoặc n file/1 time
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//upload file element
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		//1 file/time
		uploadFile.sendKeys(Step1Path);
		sleepInSecond(3);
		
		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(Step2Path);
		sleepInSecond(3);
		
		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(Step3Path);
		sleepInSecond(3);
		
		//Verify loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and 'text()= " + Step1Image + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and 'text()= " + Step2Image + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and 'text()= " + Step3Image + "']")).isDisplayed());
		
		//click start upload
		List<WebElement> startButton = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(1);
		}
		
		//Verify uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + Step1Image + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + Step2Image + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + Step3Image + "']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Auto_IT() {
		//Work for window
		//work for browser: mỗi browser phải có script khác nhau
		//phải thao tác với file dialog
		//1 file hoặc n file/1 time 
		//driver.get("");
	}
	
	@Test
	public void TC_03_Java_Robot() {
		//Java class: run on all OSx
		//work for window
		//phải thao tác với file dialog
		//1 file hoặc n file/1 time
		//driver.get("");
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
