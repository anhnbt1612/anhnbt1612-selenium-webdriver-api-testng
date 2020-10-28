package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Locator {
	WebDriver driver;
	Random rand;
	String emailAddress, firstName, lastName, password;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/");
		emailAddress = "ricky" + rand.nextInt(999999) + "@poki.vn";
		firstName = "Anh";
		lastName = "Nguyen";
		password = "ducvuong@#";
	}
	
	

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
	
	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123@2324234.12");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_Login_Invalid_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Login_Incorrect_Email_Or_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	
	@Test
	public void TC_05_Registed_To_System() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='buttons-set']//*[text()='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("middlename")).sendKeys("Ricky");
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//div[@class='buttons-set']//button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(firstName));
		Assert.assertTrue(contactInformation.contains("Ricky"));
		Assert.assertTrue(contactInformation.contains(lastName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src$='logo.png']")).isDisplayed());
	}
	
	@Test
	public void TC_06_Login_Valid_Email_And_Incorrect_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='account-login']//*[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_07_Login_Valid_Email_And_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.name("login[password]")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + firstName + " Ricky " + lastName + "!");
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
