package javaForTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Xpath_CSS {
	
	WebDriver driver;
	
	@Test
	public void TC_01() {
		
		driver = new FirefoxDriver();
	
		driver.get("http://www.facebook.com/");
	
		//Id
		driver.findElement(By.id("m_login_email")).sendKeys("rickyta1612@gmail.com");
	
		//Class
		driver.findElement(By.className("_featuredLogin__formContainer")).isDisplayed();
	
		//Name
		driver.findElement(By.name("pass")).sendKeys("123456");
	
		//TagName
		int linkNumber = driver.findElements(By.tagName("a")).size();
		System.out.println("Link number = " + linkNumber);
	
		//Link text
		driver.findElement(By.linkText("English (UK)")).click();
		
		//Partial link text
		driver.findElement(By.partialLinkText("Vi")).click();
	
		//Css
		driver.findElement(By.cssSelector("#email")).sendKeys("rickyta1612@gmail.com");
		driver.findElement(By.cssSelector("input[id='email']")).clear();
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("anhnbt@poki.vn");
	
		//Xpath
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='pass']")).clear();
	
		//Xpath text
		driver.findElement(By.xpath("//a[text()='English (UK)']")).click();
	}
	
}
