package Selenium_ecomm;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Browser_Launch {

	public static WebDriver driver;

	@BeforeMethod
	public void launchBrowser() throws InterruptedException {

		WebDriverManager.chromiumdriver().setup();
		driver = new ChromeDriver();

		// driver.get method to get URL
		driver.get("https://testautomationpractice.blogspot.com/");
		// driver.get("file:/C:/Users/Ankit%20Rana/Downloads/MortgageCalculatorPage%20(2)%20(1)/MortgageCalculatorPage%20(2).html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String s = driver.getTitle();
		System.out.println(s);

	}

	// This test method declares that its data should be supplied by the Data
	// Provider
	// named "test1"

	/*
	 * @Test(dataProvider = "test1") public void verifyData1(String name, String
	 * email) throws InterruptedException {
	 * 
	 * driver.findElement(By.xpath("//input[@type='text' and @id='name']")).sendKeys
	 * (name); Thread.sleep(2000);
	 * driver.findElement(By.id("email")).sendKeys(email); Thread.sleep(2000); }
	 * 
	 * 
	 * @DataProvider(name = "test1") public Object[][] createData1() { return new
	 * Object[][] { { "Cedric", "ankitdj888@gmail.com"}, { "Anne",
	 * "Test1234@gmail.com"}, }; }
	 */

	@Test(dataProvider = "dp", priority = 3, enabled = false)
	public void login(String data) throws InterruptedException {
		String users[] = data.split(",");

		driver.findElement(By.xpath("//input[@type='text' and @id='name']")).sendKeys(users[0]);
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys(users[1]);
		Thread.sleep(2000);
		driver.findElement(By.id("phone")).sendKeys(users[2]);
		Thread.sleep(2000);
	}

	@Test(priority = 2, enabled = false)
	public void testTitle() {

		String s = "Automation Testing Practice";

		String Title = driver.findElement(By.className("title")).getText();
		// System.out.println(Title);

		Assert.assertEquals(s, Title);

	}

	@Test(priority = 2, enabled = false)
	public void AestCheckboxSelection() throws Exception {

		Thread.sleep(3000);
		List<WebElement> checkBoxs = driver.findElements(By.cssSelector("input[type='checkbox']"));

		for (WebElement checkbox : checkBoxs) {
			checkbox.click();
			Assert.assertTrue("Checkbox is not selected", checkbox.isSelected());
		}

	}

	@Test(priority = 1)
	public void verifyDropdown() throws Exception {

		for (int i = 0; i < 3; i++) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		}

		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * 
		 * // Execute JavaScript to scroll down by 500 pixels
		 * js.executeScript("window.scrollBy(0,500)");
		 */

		/*
		 * Actions actions = new Actions(driver);
		 * 
		 * // Locate an element on the page (e.g., the body) and scroll down WebElement
		 * body = driver.findElement(By.tagName("body"));
		 * actions.moveToElement(body).click().sendKeys(" ").perform();
		 */

		Thread.sleep(3000);
		WebElement dropdownSelection = driver.findElement(By.id("country"));

		Select s = new Select(dropdownSelection);
		s.selectByIndex(1);

		WebElement selecteOption = s.getFirstSelectedOption();
		String actualDropValue = selecteOption.getText();

		String expectedValue = "Canada";

		Assert.assertEquals("Value is not correct", actualDropValue, expectedValue);
	}

	@Test
	public void mouseActivity() throws InterruptedException {
		
		WebElement elementButton = driver.findElement(By.xpath("//*[@id='HTML10']/div/button"));

        // Create an Actions object
        Actions actions = new Actions(driver);

        // Perform mouse hover
        actions.doubleClick(elementButton).perform();
        Thread.sleep(2000);
	}

	
	@Test
	public void sliderMethod() throws InterruptedException {
		
		for(int i=0;i<5;i++) {
		driver.findElement(By.tagName("Body")).sendKeys(Keys.DOWN);
		}
		
		Thread.sleep(5000);
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"slider\"]/span"));
		
		slideToPercentage(slider, 5);

        // Close the browser
        driver.quit();
    }

    // Method to slide the slider to a specific percentage
    private static void slideToPercentage(WebElement slider, int desiredPercentage) {
        // Get the width of the slider
        int sliderWidth = slider.getSize().getWidth();

        // Calculate the horizontal offset based on the percentage
        int xOffset = (int) (sliderWidth * desiredPercentage / 100.0);

        // Create an Actions object
        Actions actions = new Actions(driver);

        // Perform click and hold on the slider handle, move to the desired position, and release
        actions.clickAndHold(slider).moveByOffset(xOffset, 0).release().perform();
    }

	/*
	 * // Get the width of the slider int sliderWidth = slider.getSize().getWidth();
	 * 
	 * // Set the desired percentage to move the slider (adjust as needed) int
	 * desiredPercentage = 10;
	 * 
	 * // Calculate the horizontal offset based on the percentage int xOffset =
	 * (int) (sliderWidth * desiredPercentage / 100.0);
	 * 
	 * // Create an Actions object Actions actions = new Actions(driver);
	 * 
	 * Thread.sleep(2500);
	 * 
	 * // Perform click and hold on the slider handle, move to the desired position,
	 * and release actions.clickAndHold(slider).moveByOffset(xOffset,
	 * 0).release().perform();
	 */

	
    
    @Test
    public void inputResize() throws InterruptedException {
    	Thread.sleep(2000);
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollInTo(0,1000)", "");
    		Thread.sleep(5000);
    		
    	WebElement inputBox = driver.findElement(By.xpath("//*[@id='resizable']/div[3]"));
    

        // Create an Actions object
        Actions actions = new Actions(driver);

        // Perform click-and-hold on the bottom-right corner of the input box
        actions.clickAndHold(inputBox).moveByOffset(50, 50).release().perform();
    }
    
    
    
	@Test
	public void windowHandle() throws Exception {
		Thread.sleep(2000);
		WebElement button = driver.findElement(By.xpath("//*[@id=\"HTML4\"]/div[1]/button"));
		button.click();

		Thread.sleep(2000);
		// Handle the new window
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		// Iterate through all open windows
		for (String windowHandle : allWindowHandles) {
			if (!windowHandle.equals(mainWindowHandle)) {
				// Switch to the new window
				driver.switchTo().window(windowHandle);

				// Perform actions on the new window if needed

				// Close the new window
				driver.close();
			}
		}

		// Switch back to the main window
		driver.switchTo().window(mainWindowHandle);
	}
	
	@Test
	public void alertMethod() {
		
		WebElement triggerButton = driver.findElement(By.xpath("//*[@id=\"HTML9\"]/div[1]/button[1]"));
        triggerButton.click();

        // Switch to the alert
        Alert simpleAlert = driver.switchTo().alert();

        // Get the text of the alert
        String alertText = simpleAlert.getText();
        System.out.println("Alert Text: " + alertText);

        // Click the "OK" button to accept the alert 
        // Click the "OK" button to accept the alert
        //confirmationAlert.accept();
        // OR Click the "Cancel" button to dismiss the alert
        // confirmationAlert.dismiss();
        
        // Enter text into the prompt
       // promptAlert.sendKeys("YourInputText");

        simpleAlert.accept();
	}
	
	
	
	
	@Test(priority = 0)
	public void verifyWebTable() throws InterruptedException {

		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,750)", "");
		Thread.sleep(2000);

		int rowCount = driver.findElements(By.xpath(
				"/html[1]/body[1]/div[4]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr"))
				.size();
		System.out.println(rowCount);
	}

	@Test
	public void selectRadioButton() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='male']")).click();

	}

	/*
	 * @DataProvider(name="dp") public Object[] readJson() throws IOException,
	 * ParseException{ JSONParser jsonParser = new JSONParser(); FileReader reader =
	 * new
	 * FileReader("C:\\Users\\Ankit Rana\\eclipse-workspace\\Automation_Maven_Project\\TestData.json"
	 * );
	 * 
	 * 
	 * Object obj= jsonParser.parse(reader);
	 * 
	 * JSONObject loginFormJsonobj=(JSONObject) obj; JSONArray loginFormArray=
	 * (JSONArray) loginFormJsonobj.get("loginForms");
	 * 
	 * Object[] data = new String[loginFormArray.size()][1];
	 * 
	 * for (int i=0; i<loginFormArray.size(); i++) {
	 * 
	 * JSONObject users= (JSONObject) loginFormArray.get(i);
	 * 
	 * String namefield= (String) users.get("name"); String emailfield= (String)
	 * users.get("email");
	 * 
	 * data[i][0]= namefield+ "," + emailfield; }
	 * 
	 * return data;
	 * 
	 * }
	 */
	@DataProvider(name = "dp")
	public Object[][] readJson() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(
				"C:\\Users\\Ankit Rana\\eclipse-workspace\\Automation_Maven_Project\\TestData.json");

		Object obj = jsonParser.parse(reader);
		JSONObject loginFormJsonobj = (JSONObject) obj;
		JSONArray loginFormArray = (JSONArray) loginFormJsonobj.get("loginForm");

		Object[][] data = new Object[loginFormArray.size()][1];

		for (int i = 0; i < loginFormArray.size(); i++) {
			JSONObject users = (JSONObject) loginFormArray.get(i);
			String namefield = (String) users.get("name");
			String emailfield = (String) users.get("email");
			String phoneField = (String) users.get("Phone");

			data[i][0] = namefield + "," + emailfield + "," + phoneField;
		}

		return data;
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		if(ITestResult.FAILURE==result.getStatus())
		{
			TestNgListener.takingScreenShot(driver, result.getName());
		}
		
		driver.quit();
		
	}
}