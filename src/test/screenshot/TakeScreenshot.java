package test.screenshot;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class TakeScreenshot {

//	WebDriver driver;
	int counter = 0;
	
//	public TakeScreenshot(WebDriver driver) {
//	    this.driver = driver;
//	  }

	
	 public void takeScreenshot(WebDriver driver) throws IOException {
		    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		    String fileName = driver.findElement(By.xpath(".//*[@id='"+activity_launch+"']"))).getText().toString();
		    File targetFile = new File("C:\\Users\\shashankverma\\workspace\\BookshelfQA\\Failed_Screenshots\\screenshot" + counter
		        + ".jpg");
		    FileUtils.copyFile(scrFile, targetFile);
		    counter = counter+1;
		  }
}
