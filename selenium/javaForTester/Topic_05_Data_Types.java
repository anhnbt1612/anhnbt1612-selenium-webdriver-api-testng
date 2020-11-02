package javaForTester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Topic_05_Data_Types {

	public static void main(String[] args) {
		// Kieu du lieu ki tu
		char a = 'A';
		
		// Kieu du lieu so nguyen
		byte first = -15;
		short second = 20202;
		int third = 300000000;
		long fourth = 1231165151;
		
		// Kieu du lieu so thuc
		float fifth = 3.14f;
		double sixth = 123.213d;
		
		// Kieu du lieu	boolean (true/false)
		boolean status = true;
		status = false;
		
		// Kieu du lieu chuoi (so/chu/symbol/...)
		String Folder = "abc xyz !@#@$@$#";
		
		// Kieu du lieu mang
		String[] addresses = {"HaNoi", "HCM", "Hue"};
		int[] prices = {1000, 2000, 3000, 40000};
	
		// Kieu du lieu Class
		Topic_05_Data_Types topic_05 = new Topic_05_Data_Types();
		
		// Kieu du lieu Object (JS Executor)
		
		// Kieu du lieu Collection (Array list/ HashMap)
			//CRUD: Create Read Update Delete
		List<String> address = new ArrayList<String>();
		address.add("HCM");
		address.add("Ha Noi");
		address.add("Hue");
		address.add("Hue");
			//ko chua du lieu trung nhau 
		Set<String> add = new HashSet<String>();
		add.add("HCM");
		add.add("HCM");
		add.add("HaNoi");
		add.add("Hue");
	}
	
	public void TC_01 () {
		WebDriver driver;
		//String HomePage = driver.findElement(By.xpath("//input[@id='email']"));
		//Homepage.clear();
		//HomePage.sendkeys("dsfsdfsdff");
		//HomePage.isDisplayed();
	}

}
