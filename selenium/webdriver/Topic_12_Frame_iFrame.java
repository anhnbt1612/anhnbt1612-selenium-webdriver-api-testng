package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Frame_iFrame {
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
	public void TC_01_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("abcxyz");
		driver.findElement(By.xpath("//table[@class='lForm']//img[@alt='continue']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		
	}
	
	@Test(enabled=false)
	public void TC_02_iFrame() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fb-page fb_iframe_widget']//iframe[@title='fb:page Facebook Social Plugin']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Automation FC']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText(), "1,937 likes");
		
		driver.switchTo().defaultContent();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
	}
	
	@Test(enabled=false)
	public void TC_03_Only_2_Tab_Window() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		String parentWindowID = driver.getWindowHandle();
		System.out.println("Parent = " + parentWindowID);
		
		driver.findElement(By.xpath("//a[text()='ELEARNING']")).click();

		switchToWindowByI(parentWindowID);
		
		Assert.assertEquals(driver.getTitle(), "Automation FC");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.vn/");	
	}
	
	@Test
	public void TC_04_More_than_2_Tab_Window() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		//Click vào Elearning chuyển qua tab mới
		driver.findElement(By.xpath("//a[text()='ELEARNING']")).click();
		
		switchToWindowByTitle("Automation FC");
		sleepInSecond(5);
		Assert.assertEquals(driver.getTitle(), "Automation FC");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.vn/");
		driver.close();

		//Quay về trang Parent
		switchToWindowByTitle("[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.com/2020/02/18/training-online-automation-testing/");		

		//Switch vào iframe của FB
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fb-page fb_iframe_widget']//iframe[@title='fb:page Facebook Social Plugin']")));
		sleepInSecond(5);
		//Click vào automation FC text trong Fanpage (FB)
		driver.findElement(By.xpath("//a[@title='Automation FC']")).click();
		
		switchToWindowByTitle("Automation FC - Trang chủ | Facebook");
		sleepInSecond(5);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/automationfc/");
		driver.findElement(By.id("email")).sendKeys("rickyta1612@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("ducvuong@#2811");
		driver.findElement(By.xpath("//input[@type='submit']"));
		
		switchToWindowByTitle("[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.com/2020/02/18/training-online-automation-testing/");	

		
	}
	
	
	//chỉ đúng khi có duy nhất 2 tab
	public void switchToWindowByI(String parentID) {
		//Lấy ra tất cả ID trong tab
		Set<String> allWindows = driver.getWindowHandles();
		//Duyệt qua từng ID đã lấy được
		for (String windowID : allWindows) {
			//Nếu ID khác parent thì switch qua
			if(!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}
	
	//Switch bằng title (Đúng với tất cả trường hợp: 2/ >2 tab
	public void switchToWindowByTitle(String expectedTitle) {
		//Lấy ra tất cả ID của tab đang có
		Set<String> allWindows = driver.getWindowHandles();
		//Duyệt qua từng ID đã lấy được
		for (String windowID : allWindows) {
			//Switch vào từng ID trước
			driver.switchTo().window(windowID);
			//lấy ra title của tab đó
			String actualTitle = driver.getTitle();
			//Ktra nếu title của tab này mà bằng với title mình mong muốn thì break
			if(actualTitle.equals(expectedTitle)) {
				break;
			}
		}
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
