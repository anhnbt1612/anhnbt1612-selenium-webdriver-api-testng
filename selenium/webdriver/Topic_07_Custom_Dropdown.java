package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runners.AllTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import netscape.javascript.JSException;

public class Topic_07_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String LocalFolder = System.getProperty("user.dir");
	String[] firstMonths = {"March", "April", "December"};
	String[] secondMonths = {"March", "April", "December", "July", "August"};
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
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
	public void TC_03_Multiple_Custom_Select_Dropdown() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		SelectMultiItemDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", firstMonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(firstMonths));
		
		driver.navigate().refresh();
		
		SelectMultiItemDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", secondMonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(secondMonths));
		
		
	}
	
	
	public void SelectMultiItemDropdown(String parentXpath, String childXpath, String [] expectedValue) {
		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
		
		for(WebElement childElement : allItems) {
			//"January", "April", "July"
			for(String item: expectedValue) {
				if(childElement.getText().equals(item)) {
					//Click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected =driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item Selected = " + itemSelected.size());
					if(expectedValue.length == itemSelected.size()) {
						break;
					}
					
				}
			}
		}
	}
	
	public boolean areItemSelected(String[] itemSelectedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);
		
		//"March", "April", "December"
		if(numberItemSelected <= 3 && numberItemSelected > 0) {
			for(String item : itemSelectedText) {
				if(allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		}
		else {
			return driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]//span[text()='"+ numberItemSelected + " of 12 selected']")).isDisplayed();
		}
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
