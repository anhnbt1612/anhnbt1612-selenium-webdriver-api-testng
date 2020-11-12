package webdriver;

import java.awt.Checkbox;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	String LocalFolder = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	By loginButton = By.cssSelector(".fhs-btn-login");
	By loginUsername = By.cssSelector("#login_username");
	By loginPassword = By.cssSelector("#login_password");
	By checkboxElement = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
	By radioElement = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
	By checked_1 = By.xpath("//span[contains(text(), 'Checked')]/preceding-sibling::div//input");
	By checked_2 = By.xpath("//span[contains(text(), 'Indeterminate')]/preceding-sibling::div//input");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled=false)
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		//Disabled
		Assert.assertFalse(isElementEnabled(loginButton));
		
		driver.findElement(loginUsername).sendKeys("abc@gmail.com");
		driver.findElement(loginPassword).sendKeys("123456");
		
		sleepInTime(2);
		//Enabled
		Assert.assertTrue(isElementEnabled(loginButton));
		
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		removeDisabledAttributeByJS(loginButton);
		sleepInTime(5);
		
		//Enabled
		Assert.assertTrue(isElementEnabled(loginButton));
		driver.findElement(loginButton).click();
		sleepInTime(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}
	
	@Test
	public void TC_02_Checkbox_Radio_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		clickToElementByJS(driver.findElement(By.xpath("//input[@value='Summer']")));
		sleepInTime(2);
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//input[@value='Summer']"))));
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		clickToElementByJS(driver.findElement(checked_1));
		sleepInTime(2);
		clickToElementByJS(driver.findElement(checked_2));
		sleepInTime(2);
		Assert.assertTrue(isElementSelected(driver.findElement(checked_1)));
		Assert.assertTrue(isElementSelected(driver.findElement(checked_2)));
	}
	
	@Test(enabled=false)
	public void TC_02_2_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInTime(2);
		
		checkToCheckboxOrRadio(driver.findElement(checkboxElement));
		Assert.assertTrue(isElementSelected(driver.findElement(checkboxElement)));
		
		unCheckToCheckbox(driver.findElement(checkboxElement));
		Assert.assertFalse(isElementSelected(driver.findElement(checkboxElement)));

		
	}
	
	@Test(enabled=false)
	public void TC_02_3_Checkbox_Select_all() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		//Select all
		for (WebElement element : checkboxes) {
			checkToCheckboxOrRadio(element);
			sleepInTime(1);
		}
		
		//Verify selected
		for (WebElement element : checkboxes) {
			Assert.assertTrue(isElementSelected(element));
		}
		
		sleepInTime(5);
		
		//Deselect all
		for (WebElement element : checkboxes) {
			unCheckToCheckbox(element);
			sleepInTime(1);
		}
		
		//Verify Deselected
		for (WebElement element : checkboxes) {
			Assert.assertFalse(isElementSelected(element));
		}
	}	
	
	@Test(enabled=false)
	public void TC_03_Radio_Button_Default() {
		driver.get("http://demos.telerik.com/kendo-ui/radiobutton/index");
		checkToCheckboxOrRadio(driver.findElement(radioElement));
		Assert.assertTrue(isElementSelected(driver.findElement(radioElement)));
		unCheckToCheckbox(driver.findElement(radioElement));
		Assert.assertTrue(isElementSelected(driver.findElement(radioElement)));

	}
	
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		}
		else {
			System.out.println("Element is disabled");
			return false;
		}
	}
	
	public void sleepInTime(long inTimeSecond) {
		try {
			Thread.sleep(inTimeSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
		
	}
	
	public void clickToElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
		
	}
	
	public boolean isElementSelected(WebElement element) {
		if(element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		}
		else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	public void checkToCheckboxOrRadio(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToCheckbox(WebElement element) {
		if(element.isSelected()) {
			element.click();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
