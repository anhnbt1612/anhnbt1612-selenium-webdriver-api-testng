package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	String LocalFolder = System.getProperty("user.dir");
	Alert alert;
	By resultText = By.xpath("//p[@id='result']");
	String username = "admin";
	String password = "admin";
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test(enabled=false)
	public void TC_01_Alert_Acept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
	}
	
	@Test(enabled=false)
	public void TC_02_Alert_Confirm() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		sleepInSecond(2);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		sleepInSecond(2);
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Ok");
	}
	
	@Test(enabled=false)
	public void TC_03_Alert_Prompt() {
		String loginValue = "Ricky";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		sleepInSecond(2);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(loginValue);
		sleepInSecond(5);
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + loginValue);
	}
	
	@Test(enabled=false)
	public void TC_04_Authentication_Alert() {
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(basicAuthenLink);
		driver.get(getAuthenticationUrl(basicAuthenLink, username, password));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}	
	
	public String getAuthenticationUrl(String url, String userName, String pass) {
		String urlValue[] = url.split("//");
		url = urlValue[0] + "//" + userName + ":" + pass + "@" + urlValue[1];
		return url;
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
