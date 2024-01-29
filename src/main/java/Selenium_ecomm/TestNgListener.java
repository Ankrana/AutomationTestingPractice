package Selenium_ecomm;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestNgListener {
	
	public static void takingScreenShot(WebDriver driver, Object name) throws IOException {
		// TODO Auto-generated method stub
		
		File screenshotfile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotfile, new File ("./Screenshots/" +name+" .png"));
		
	}
	
}
