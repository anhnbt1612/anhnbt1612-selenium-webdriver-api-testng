package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	
	//Global Variable
	WebDriver driver;
	String LocalFolder = System.getProperty("user.dir");
	String email, userID, password, loginPageUrl;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		email = "rickyta1612@gmail.com";
		loginPageUrl = driver.getCurrentUrl();
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		
		//Local Variable
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}
	
	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	}
	
	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(By.name("name")).sendKeys("Anhnbt");
		driver.findElement(By.name("dob")).sendKeys("16/12/1992");
		driver.findElement(By.name("addr")).sendKeys("CT1 Ngo Thi Nham");
		driver.findElement(By.name("city")).sendKeys("Ha Dong");
		driver.findElement(By.name("state")).sendKeys("Ha Noi");
		driver.findElement(By.name("pinno")).sendKeys("123456");
		driver.findElement(By.name("telephonno")).sendKeys("848191820");
		driver.findElement(By.name("emailid")).sendKeys("rickyta1612@gmail.com;");
		driver.findElement(By.name("password")).sendKeys("456789"); 
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer Registered Successfully!!!");
		
	}
	
	@Test
	public void TC_04_Edit_Customer() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
