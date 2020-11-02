package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebDriver_Element_Commands_Part_2 {
	WebDriver driver;
	String LocalFolder = System.getProperty("user.dir");
	By emailTextboxBy = By.id("mail");
	By educationTextAreaBy = By.id("edu");
	By ageUnder18Radio = By.id("under_18");
	By jobRole01Dropdown = By.id("job1");
	By javaLanguageCheckboxBy = By.id("java");
	
	By passwordTextboxBy = By.id("password");
	By disabledRadioBy = By.id("radio-disabled");
	By jobRole03Dropdown = By.id("job3");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", LocalFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Check_Element_Displayed() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		//Email_Textbox
		if(driver.findElement(emailTextboxBy).isDisplayed()) {
			System.out.println("Element is Displayed");
			driver.findElement(emailTextboxBy).sendKeys("Automation Testing");
		}
		else {
			System.out.println("Element is not Displayed");
		}
		
		Thread.sleep(3000);
		
		//Education TextArea
		if(driver.findElement(educationTextAreaBy).isDisplayed()) {
			System.out.println("Element is Displayed");
			driver.findElement(educationTextAreaBy).sendKeys("Automation Testing");
		}
		else {
			System.out.println("Element is not Displayed");
		}
		
		Thread.sleep(3000);
		
		//Age (Under 18) Radio button
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			System.out.println("Element is Displayed");
			driver.findElement(ageUnder18Radio).click();
		}
		else {
			System.out.println("Element is not Displayed");
		}
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_01_Element_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		
		if(isElementDisplayed(emailTextboxBy)) {
			driver.findElement(emailTextboxBy).sendKeys("Automation Test");
		}
		
		if(isElementDisplayed(educationTextAreaBy)) {
			driver.findElement(educationTextAreaBy).sendKeys("Automation Test");
		}
		
		if(isElementDisplayed(ageUnder18Radio)) {
			driver.findElement(ageUnder18Radio).click();
		}
	}
	
	@Test
	public void TC_02_Element_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		Assert.assertTrue(isElementEnable(emailTextboxBy));
		Assert.assertTrue(isElementEnable(educationTextAreaBy));
		Assert.assertTrue(isElementEnable(ageUnder18Radio));
		Assert.assertTrue(isElementEnable(jobRole01Dropdown));
		
		Assert.assertFalse(isElementEnable(passwordTextboxBy));
		Assert.assertFalse(isElementEnable(disabledRadioBy));
		Assert.assertFalse(isElementEnable(jobRole03Dropdown));
		
	}
	
	@Test
	public void TC_03_Element_Selected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		
		Assert.assertFalse(isElementSelected(ageUnder18Radio));
		Assert.assertFalse(isElementSelected(javaLanguageCheckboxBy));
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaLanguageCheckboxBy).click();
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertTrue(isElementSelected(javaLanguageCheckboxBy));
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaLanguageCheckboxBy).click();
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertFalse(isElementSelected(javaLanguageCheckboxBy));
	}
	
	@Test
	public void TC_04_Validate_Regiser_Form() throws InterruptedException{
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("rickyta1612@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Anhnbt");

		Assert.assertFalse(isElementEnable(By.id("create-account")));
		
		//Lowercase
		driver.findElement(By.id("new_password")).sendKeys("auto");
		Thread.sleep(3000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		
		//Special Char
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto@#");
		Thread.sleep(3000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		
		//Upper Case
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Auto@#");
		Thread.sleep(3000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		
		//8 Char
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation@#");
		Thread.sleep(3000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		
		//Number
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation123");
		Thread.sleep(3000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		
		//Full
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation@#123");
		Thread.sleep(3000);
		Assert.assertTrue(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		
	}
	
	//Common function (Call function)
	public boolean isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println("Element is Displayed");
			return true;
		}
		else {
			System.out.println("Element is not Displayed");
			return false;
		}	
	}
	
	public boolean isElementEnable(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element is Enable");
			return true;
		}
		else {
			System.out.println("Element is Disable");
			return false;
		}	
	}
	
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println("Element is Selected");
			return true;
		}
		else {
			System.out.println("Element is not Selected");
			return false;
		}	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
