package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	Random rand;
	WebDriver driver;
	String LocalFolder = System.getProperty("user.dir");
	Select select;
	String firstName, lastName, password, email, companyName, date, month, year;
	By genderMaleBy = By.id("gender-male");
	By firstNameBy = By.id("FirstName");
	By lastNameBy = By.id("LastName");
	By emailBy = By.id("Email");
	By companyBy = By.id("Company");
	By dateBy = By.name("DateOfBirthDay");
	By monthBy = By.name("DateOfBirthMonth");
	By yearBy = By.name("DateOfBirthYear");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.nopcommerce.com/");
		firstName = "Anh";
		lastName = "Nguyen";
		password = "123456";
		email = generateEmail();
		companyName = "poki";
		date = "16";
		month = "December";
		year = "1992";
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(genderMaleBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);

		select = new Select(driver.findElement(dateBy));
		// Verify "Date of Birth" is not Multiple Dropdown
		Assert.assertFalse(select.isMultiple());
		// select theo thu tu sap xep (ko dung)
		// select.selectByIndex(10);
		// select theo value (it dung)
		// select.selectByValue("15");
		// select theo visibleText
		select.selectByVisibleText(date);
		// Verify item select
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		// Verify all item in Dropdown = ?
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(monthBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);

		select = new Select(driver.findElement(yearBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(companyBy).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		Thread.sleep(3000);
		
		driver.findElement(By.className("ico-account")).click();
		
		Assert.assertTrue(driver.findElement(genderMaleBy).isSelected());
		
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), companyName);
		
		select = new Select(driver.findElement(dateBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		select = new Select(driver.findElement(monthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(yearBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

	}
	
	public String generateEmail() {
		Random rand = new Random();
		return "anhnguyen" + rand.nextInt(9999) + "@poki.vn";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
