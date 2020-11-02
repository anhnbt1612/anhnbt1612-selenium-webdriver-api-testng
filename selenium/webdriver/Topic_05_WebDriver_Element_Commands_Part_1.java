package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebDriver_Element_Commands_Part_1 {
	WebDriver driver;
	WebElement element;
	List <WebElement> elements;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://facebook.com");
	}

	@Test
	public void TC_01_Web_Element_Command() {
		//Textbox/TextArea/DropDown
		//Xoa du lieu
		element.clear();
		//Nhap du lieu
		element.sendKeys("qdfgfdhgh");
		//Cho phep tim element (1)
		element = driver.findElement(By.xpath("//input[@class='value']"));
		//Cho phep tim nhieu element
		elements = driver.findElements(By.xpath(""));
		//lay attribute
		WebElement searchTextbox = driver.findElement(By.xpath("//input[@id='search']"));
		searchTextbox.getAttribute("placeholder");
		//GUI: font/size/color/location/position/...
		WebElement Subcribe_Btn = driver.findElement(By.xpath("//button[@title='Subcribe']"));
		Subcribe_Btn.getCssValue("background");
		Subcribe_Btn.getCssValue("font-family");
		Subcribe_Btn.getCssValue("font-size");
		Subcribe_Btn.getCssValue("font-align");
		//report HTML: ReportNG/Extend Report/Allure Report
		//element.getScreenshotAs("");
		
		//lay tagname
		WebElement Tagname = driver.findElement(By.cssSelector("#newsletter"));
		String SubcribeTagname = Tagname.getTagName(); //input
		driver.findElement(By.xpath("//" + SubcribeTagname + "[@id='search']"));
		
		//lauy text ko thuoc attribute nao
		element.getAttribute("");
		//Kiem tra xem element nay là hien thi hoac khong
		//Hien thi: TRUE
		//khong hiên thi: FALSE
		element.isDisplayed();
		Assert.assertTrue(element.isDisplayed());
		
		//kiem tra element co bi disable hay ko
		//enable: TRUE
		//disable: FALSE
		element.isEnabled();
		Assert.assertFalse(element.isEnabled());
		
		//kiem tra element duoc chon hay chua: check box/ radio button
		//dropdown (item) => thu vien rieng = class rieng = Select (Selenium)
		Assert.assertTrue(element.isSelected());
		
		//Chi tac dung trong the form
		//element.Submit();
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
