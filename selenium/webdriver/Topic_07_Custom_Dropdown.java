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

	@Test(enabled=false)
	public void TC_01_jQuery_CustomDropdown() {
	driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	
	selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "4");
	sleepInSecond(2);
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='4']")).isDisplayed());

	
	selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
	sleepInSecond(2);
	Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']")).getText(), "15");

	
	selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "8");
	sleepInSecond(2);
	Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']")).getText(), "8");

	}
	
	@Test(enabled=false)
	public void TC_02_vueJS_Editable_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Belgium");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Belgium']")).isDisplayed());
		
	}
	
	@Test
	public void Multiple_Custom_Select_Dropdown() {
		driver.get("");
		
	}
	
	public void selectItemInEditableDropdown(String parentXpath, String allItemXpath, String expectedValue) {
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValue);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		List<WebElement> allItems = driver.findElements(By.xpath(parentXpath));
		int allItemNumber = allItems.size();
		for(int i = 0; i < allItemNumber; i++) {
			String actualValue = allItems.get(i).getText();
			if(actualValue.equals(expectedValue)) {
				allItems.get(i).click();
				break;
			}
		}
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
