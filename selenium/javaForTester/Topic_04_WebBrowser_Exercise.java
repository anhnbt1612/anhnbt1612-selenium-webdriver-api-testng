package javaForTester;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_Exercise {
	WebDriver driver;
	String LocalFolder = System.getProperty("user.dir");

  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://live.demoguru99.com/");
	  
  }
	  
  
  @Test
  public void TC_01_Verify_URL() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//*[@id='login-form']//*[text()='Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  	  
  }
  
  @Test
  public void TC_02_Verify_Title() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  driver.findElement(By.xpath("//*[@id='login-form']//*[text()='Create an Account']")).click();
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	  
  }
  
  @Test
  public void TC_03_Navigate_function() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//*[@id='login-form']//*[text()='Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  driver.navigate().back();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.navigate().forward();
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	  
  }
  
  @Test
  public void TC_04_Get_Page_Source_Code() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  String LoginPage = driver.getPageSource();
	  Assert.assertTrue(LoginPage.contains("Login or Create an Account"));
	  driver.findElement(By.xpath("//*[@id='login-form']//*[text()='Create an Account']")).click();
	  String RegisterPage = driver.getPageSource();
	  Assert.assertTrue(RegisterPage.contains("Create an Account"));
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
