package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_2 {
	WebDriver driver;
	WebDriverWait explicitWait;
	String LocalFolder = System.getProperty("user.dir");
	Actions action;
	Alert alert;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test(enabled=false)
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		//Verify Quit not contains (visible/ hover status)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')" 
		+ "and not(contains(@class,'context-menu-visible')) and not(contains(@class,'context-menu-hover'))]")).isDisplayed());
		//Hover to quit
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')" 
		+ "and not(contains(@class,'context-menu-visible')) and not(contains(@class,'context-menu-hover'))]"))).perform();
		//Verify Quit contains (visible/ hover status)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')" 
		+ "and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]")).isDisplayed());
		//Click to Quit
		driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')" 
		+ "and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]")).click();
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void TC_02_Drag_and_Drop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		sleepInSecond(1);
		
		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		scrollByJS(targetCircle);
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSecond(5);
		Assert.assertEquals(targetCircle.getText(), "You did great!");
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void scrollByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
