package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runners.AllTests;
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

public class Topic_07_Custom_Dropdown {
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

	@Test
	public void TC_01_jQuery() {
	driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	
	selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "4");
	sleepInSecond(2);
	
	selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
	sleepInSecond(2);
	
	selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "8");
	sleepInSecond(2);
	}
	
	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		int allItemNumber = allItems.size();
		
		//Tradition for
		for(int i=0; i < allItemNumber; i++) {
			String actualValueItem = allItems.get(i).getText();
			
			if(actualValueItem.equals(expectedValueItem)) {
				allItems.get(i).click();
				break;
				
			}
		}
		
		//for each
//		for(WebElement item : allItems) {
//			if(item.getText().equals(expectedValueItem)) {
//				item.click();
//				break;
//			}
//		}
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
