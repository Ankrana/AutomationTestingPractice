package Selenium_ecomm;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	
	@Test(dataProvider= "dp")
	public void login(String data) throws InterruptedException {
		String users[] = data.split(",");
		
		driver.findElement(By.xpath("//input[@type='text' and @id='name']")).sendKeys(users[0]); 
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys(users[1]); 
		Thread.sleep(2000);
		
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
        FileReader reader = new FileReader("C:\\Users\\Ankit Rana\\eclipse-workspace\\Automation_Maven_Project\\TestData.json");

        Object obj = jsonParser.parse(reader);
        JSONObject loginFormJsonobj = (JSONObject) obj;
        JSONArray loginFormArray = (JSONArray) loginFormJsonobj.get("loginForm");

        Object[][] data = new Object[loginFormArray.size()][1];

        for (int i = 0; i < loginFormArray.size(); i++) {
            JSONObject users = (JSONObject) loginFormArray.get(i);
            String namefield = (String) users.get("name");
            String emailfield = (String) users.get("email");

            data[i][0] = namefield + "," + emailfield;
        }

        return data;
    }

	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}	
}