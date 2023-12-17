package Selenium_ecomm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser_Launch {
	
	public WebDriver driver;

	@BeforeMethod
	 public void launchBrowser() throws InterruptedException {
		
	
		WebDriverManager.chromiumdriver().setup();
		driver = new ChromeDriver();
		
		
		//driver.get method to get URL
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		
		
		
		String s= driver.getTitle();
		System.out.println(s);
		
	}
	
	//This test method declares that its data should be supplied by the Data Provider
	//named "test1"
			
	@Test(dataProvider = "test1")
	public void verifyData1(String name, String email) throws InterruptedException {
		
	 driver.findElement(By.xpath("//input[@type='text' and @id='name']")).sendKeys(name);
	 Thread.sleep(2000);
	 driver.findElement(By.id("email")).sendKeys(email);
	 Thread.sleep(2000);
	}
	
	
	@DataProvider(name = "test1")
	public Object[][] createData1() {
	 return new Object[][] {
	   { "Cedric", "ankitdj888@gmail.com"},
	   { "Anne", "Test1234@gmail.com"},
	 };
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}	
}