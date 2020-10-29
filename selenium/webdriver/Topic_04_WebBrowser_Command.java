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
	
		driver.close(); //**
		
		driver.quit(); //**
		
		driver.get("http://"); //**
		
		driver.getCurrentUrl(); //**

			String LoginPageUrl = driver.getCurrentUrl();

			Assert.assertEquals(LoginPageUrl, "http://");
		
		driver.getPageSource();
	
		driver.getTitle(); //**
		Assert.assertEquals(driver.getTitle(), "Title!!!");
		
		driver.getWindowHandle(); //**
		
		driver.getWindowHandles(); //**
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //**
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.MILLISECONDS);
		
		driver.manage().window().fullscreen();
		
		driver.manage().window().maximize(); //**
		
		//driver.manage().window().setSize("");
		
		driver.navigate().back();
		
		driver.navigate().forward();
		
		driver.navigate().refresh();
		
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
