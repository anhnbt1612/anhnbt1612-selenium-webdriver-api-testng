package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox_practive {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String LocalFolder = System.getProperty("user.dir");
	By loginButton = By.xpath("//button[@class='fhs-btn-login']");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test(enabled=false)
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		Assert.assertFalse(isElementEnabled(loginButton));
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
		sleepInSecond(3);
		Assert.assertTrue(isElementEnabled(loginButton));
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		sleepInSecond(1);
		removeDisabledAttributeByJS(loginButton);
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("(//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert'])[1]")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("(//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert'])[1]")).getText(), "Thông tin này không thể để trống");
		
	}
	
	@Test(enabled=false)
	public void TC_02_Default_Checkbox_Or_Radio_Button() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(2);
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		sleepInSecond(1);
		unCheckToCheckbox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		sleepInSecond(2);
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))));
	}
	
	@Test
	public void TC_03_Custom_Checkbox_Or_Radio_Button() {
		driver.get("https://material.angular.io/components/radio/examples");
		clickToCustomCheckboxOrRadio(driver.findElement(By.xpath("//input[@value='Summer']")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//input[@value='Summer']"))));
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		sleepInSecond(2);
		clickToCustomCheckboxOrRadio(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']//input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']//input"))));
		clickToCustomCheckboxOrRadio(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']//input")));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']//input"))));
		
		clickToCustomCheckboxOrRadio(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-2']//input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-2']//input"))));
		clickToCustomCheckboxOrRadio(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-2']//input")));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-2']//input"))));

		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element is Enabled");
			return true;
		}
		else {
			System.out.println("Element is Disabled");
			return false;
		}
	}
	
	public boolean isElementSelected(WebElement element) {
		if(element.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
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
	
	public void clickToCustomCheckboxOrRadio(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
