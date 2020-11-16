package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	String LocalFolder = System.getProperty("user.dir");
	Actions action;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
	}

	@Test(enabled=false)
	public void TC_01_Hover_Mouse() {
		driver.get("https://tiki.vn/");
		sleepInSecond(5);
		driver.findElement(By.xpath("//button[text()='bỏ qua']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
		
		WebElement shortCutAccount = driver.findElement(By.xpath("//div[@data-view-id='header_user_shortcut_account']"));
		action.moveToElement(shortCutAccount).perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
		
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");	
	}
	
	@Test(enabled=false)
	public void TC_02_Click_and_Hold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println(allNumber.size());
		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(10)).release().perform();
		
		List<WebElement> allNumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
		System.out.println("Number Selected = " + allNumberSelected.size());
		Assert.assertEquals(allNumberSelected.size(), 9);
		
	}
	
	@Test(enabled=false)
	public void TC_02_1_Click_and_Hold_Random_Select() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println(allNumber.size());
		action.keyDown(Keys.CONTROL).perform();
		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(5)).click(allNumber.get(11)).perform();;
		action.keyUp(Keys.CONTROL).perform();
		List<WebElement> allNumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
		System.out.println("Number Random Selected" + allNumberSelected.size());
		Assert.assertEquals(allNumberSelected.size(), 4);
	}
	
	@Test
	public void TC_03_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
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
