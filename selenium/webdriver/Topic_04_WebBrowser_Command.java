package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_Command {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
	}

	@Test
	public void TC_01_WebBrowser() {
		//Tắt tab trình duyệt tại nơi đang chạy test
		driver.close(); //**
		
		//Tắt hết tất cả trình duyệt
		driver.quit(); //**
		
		//Mở ra URL
		driver.get("http://"); //**
		
		//Lấy ra URL của page hiện tại
		driver.getCurrentUrl(); //**
			//// VD:
			String LoginPageUrl = driver.getCurrentUrl();
			////Verify kết quả trả về
			Assert.assertEquals(LoginPageUrl, "http://");
		
		//Lấy ra HTML của code hiện tại
		driver.getPageSource();
	
		//Lấy ra Title của page hiện tại
		driver.getTitle(); //**
		Assert.assertEquals(driver.getTitle(), "Title!!!");
		
		//Lấy ra cái GUID của tab hiện tại
		driver.getWindowHandle(); //**
		
		//Lấy ra cái GUID của all tab đang có
		driver.getWindowHandle(); //**
		
		//Hàm driver nhưng lại aply cho element driver.manage
		//Chờ cho element được load ra thành công trong vòng xx giây (or khoảng thời gian)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //**
		
		//Chờ cho page được load thành công trong vòng xx giây (or khoảng thời gian)
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		//JS Excutor
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.MILLISECONDS);
		
		//Chạy trình duyệt full màn hình
		driver.manage().window().fullscreen();
		
		//Chạy trình duyệt màn hình lớn (không full)
		driver.manage().window().maximize(); //**
		
		//Test Responsive. Muốn sử dụng phải add Dimension vào
		//driver.manage().window().setSize("");
		
		//Back lại page trước
		driver.navigate().back();
		
		//Forward tới page sau
		driver.navigate().forward();
		
		//Refresh trang
		driver.navigate().refresh();
		
		//Mở url
		driver.navigate().to("http://");
		
		// Alert/ Iframe (Frame)/ Window (tab) 
		driver.switchTo().alert(); //**
		
		driver.switchTo().frame(""); //**
		
		driver.switchTo().window("");  //**
		
		
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
