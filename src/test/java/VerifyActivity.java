package test.java;
//import test.screenshot.*;

import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.naming.PartialResultException;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.ElementNotFoundException;
//import com.thoughtworks.selenium.Selenium;
//import com.thoughtworks.selenium.Wait;
//import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
//import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;


//import org.openqa.jetty.html.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import test.screenshot.TakeScreenshot;

public class VerifyActivity {
	
	public String BaseURL = "https://online.vitalsource.com/signin?return=/books";
	WebDriver driver = null;
	Properties obj = new Properties();
	
@BeforeTest
public void BrowserOpen () throws InterruptedException
{
//	System.setProperty("webdriver.chrome.driver", "C:\\Work\\Selenium\\chromedriver_win32\\chromedriver.exe");
//	driver = new ChromeDriver();
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get(BaseURL);
//	try {
//		obj.load(new FileInputStream("C:\\Users\\shashankverma\\workspace\\BookshelfQA\\src\\test\\resources\\Elements.properties"));
//		
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
}
	
	
@Test(priority=0)
public void LoginApplication () throws InterruptedException

{
	driver.findElement(By.id("email")).sendKeys("shashankverma@qainfotech.net");
	driver.findElement(By.id("password")).sendKeys("qainfotech");
	driver.findElement(By.xpath(".//*[@id='page']/form/div/div[@class='signin_inputs inputs']/div[@class='submit clear']/button/span")).click();
		}

@Test(priority=1)

public void VerifyHomePageTitle () throws InterruptedException

{
	String Expected_HomePage_title = "Bookshelf";
	Thread.sleep(2000);
	String Actual_HomePage_title = driver.findElement(By.xpath(".//*[@id='brand_title']/a/span")).getAttribute("innerHTML");
	System.out.println(Actual_HomePage_title);
	Assert.assertEquals(Actual_HomePage_title, Expected_HomePage_title);
	}

@Test(priority=2)

public void VerifyActivityStatus () throws InterruptedException

{

	 WebElement iframe1;
	 WebElement iframe2;
	 String Activity_Pass = "Pass";
	 String Activity_Not_Clickable = "Fail, Activity icon not clickable";
	 String Activity_Location_incorrect = "Fail, Activity icon is not located correctly";
	 String Activity_Not_Loading = "Fail, Activity is Not Loading";
	 String Other_icon_present1 = "Audio player is working";
	 String Other_icon_present2 = "Audio player is NOT working";
	 String Video_player_working = "Video Player is working";
	 String Video_player_not_working = "Video Player is NOT working";
	 String Loading_issue = "AUDIO/VIDEO/ACTIVITY DOES NOT LAUNCH";
	List <WebElement> elems  = driver.findElements(By.xpath(".//*[@id='all_titles_grid']/li/div[@class ='griditem']/a/div[@class='title']"));
	int booksize=0;
	booksize=elems.size();
	Cell result_cell;
	String actual_bookname;
	
	TakeScreenshot failscreenshot = new TakeScreenshot();

	for (int i=1; i<=booksize;i++)
		{
		actual_bookname = driver.findElement(By.xpath(".//*[@id='all_titles_grid']/li["+i+"]/div[@class ='griditem']/a/div[@class='title']")).getText().toString();
		Thread.sleep(2000);
		if (actual_bookname.contains("Life 2: American English"))
		{
			driver.findElement(By.xpath(".//*[@id='all_titles_grid']/li["+i+"]/div[@class ='griditem']/a/div[@class='title']")).click();
			Thread.sleep(2000);
			
			try {
 			
			FileInputStream file = new FileInputStream(
					new File("C:\\Work\\Bookshelf\\Automation\\Automation Life AmE Level 2\\Automation_Coverage_Life_AmE_Level_2.xls"));

			
			// Get the workbook instance for XLS file
			HSSFWorkbook filename = new HSSFWorkbook(file);

			// Get first sheet from the workbook
			HSSFSheet sheet = filename.getSheetAt(0);

		        for (int j=1; j<=sheet.getLastRowNum(); j++) 
		        {
			                Row row = sheet.getRow(j);
			                double pagen = row.getCell(1).getNumericCellValue();
			                int page_number = (int)pagen;
			                System.out.println("Page Number: "+page_number);
			                String activity_launch = row.getCell(0).getStringCellValue().toString().trim();
			                System.out.println("Activity Launched: "+activity_launch);
			             
//			                List<WebElement> frameList=driver.findElements(By.tagName("iframe"));
//			                System.out.println(frameList.size());
			                driver.switchTo().defaultContent();
			                
			                driver.findElement(By.xpath(".//*[@id='printed-page-number-input']")).clear();
//			                System.out.println(activity_launch+"2");
			                driver.findElement(By.xpath(".//*[@id='printed-page-number-input']")).sendKeys(""+page_number+"");
//			                System.out.println(activity_launch+"3");
			                driver.findElement(By.xpath(".//*[@id='paging_container_inner']/form/button")).click();
//			                System.out.println(activity_launch+"4");
			                Thread.sleep(5000);
			                	
			                
			                
			                iframe1 = driver.findElement(By.xpath(".//*[@id='epub-content']"));
			                
			                driver.switchTo().frame(iframe1);
			                System.out.println("Switched frame 1");
			                Thread.sleep(2000);
			                
			                
			                try
			                {
			                	WebDriverWait wait = new WebDriverWait(driver, 20);
			                	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='"+activity_launch+"']")));
			                	
			                }
			                catch(Exception al)
			                {
			                	System.out.println("Activity icon location is incorrect");
			                	result_cell = sheet.getRow(j).getCell(2);
						        result_cell.setCellValue(Activity_Location_incorrect);
//						        VerifyActivity obj = new VerifyActivity();
						        failscreenshot.takeScreenshot(driver);
						        continue;
			                }
			                
			                
			                
			                try
			                {
			                	driver.findElement(By.xpath(".//*[@id='"+activity_launch+"']")).click();
			                	System.out.println("Activity icon is clickable");
			                	
			                }
			                catch(Exception al1)
			                {
			                	System.out.println("Activity icon is not clickable");
			                	result_cell = sheet.getRow(j).getCell(2);
						        result_cell.setCellValue(Activity_Not_Clickable);
//						        VerifyActivity obj = new VerifyActivity();
						        failscreenshot.takeScreenshot(driver);
						     
						        continue;
			                }					                
			                
			                					                
//			            Thread.sleep(10000);
  
//			            System.out.println("Test");
			            Thread.sleep(3000);
			            
			            
			            driver.switchTo().defaultContent();
			            driver.switchTo().frame(iframe1);
			            Thread.sleep(1000);

			            try{
			            	iframe2 = driver.findElement(By.cssSelector(".frame>iframe"));
			            	driver.switchTo().frame(iframe2);
			            }
			            catch(Exception al2)
		                {
		                	System.out.println("Audio/Video icon is present or Activity not launched");
//		                	iframe3 = driver.findElement(By.cssSelector("html>body>div#page-overlay>div.audio-popup.vst-draggable>div.frame-holder>div.frame"));
//		                	Thread.sleep(2000);
//		                	driver.switchTo().frame(0);
//		                	Thread.sleep(2000);
		                	result_cell = sheet.getRow(j).getCell(2);
		                	try
		                	{
		                	driver.findElement(By.cssSelector("#audio-player_controlbar_elapsed")).isDisplayed();
		                		                
		                		
		                	try
		                	{
		                	driver.findElement(By.cssSelector(".jwplay>span>button")).click();
					        Thread.sleep(3000);
					             
		                	driver.findElement(By.cssSelector(".jwplay.jwtoggle>span>button")).click();
		                	System.out.println("Audio player is working");
		                	result_cell.setCellValue(Other_icon_present1);
		                	continue;
					        }
		                	catch(Exception exc)
		                	{
		                		System.out.println("Audio player is NOT working");
		                		result_cell.setCellValue(Other_icon_present2);
//		                		VerifyActivity obj = new VerifyActivity();
						        failscreenshot.takeScreenshot(driver);
						     continue;
		                	}}
		                	catch(Exception abcd)
		                	{
		                		try
			                	{
			                	driver.findElement(By.cssSelector("#video-player_display_button")).isDisplayed();
			                	       
			                		
			                	try
			                	{
			                	driver.findElement(By.cssSelector("#video-player_display_button_play")).click();
						        Thread.sleep(3000);
						             
			                	driver.findElement(By.cssSelector("#video-player_display")).isDisplayed();
			                	System.out.println("Video player is working");
			                	result_cell.setCellValue(Video_player_working);
			                	continue;
			                	}
			                	catch(Exception exc)
			                	{
			                		System.out.println("Video player is NOT working");
			                		result_cell.setCellValue(Video_player_not_working);
//			                		VerifyActivity obj = new VerifyActivity();
							        failscreenshot.takeScreenshot(driver);
			                		continue;
			                	}
			                	}
		                		catch(Exception acbde)
		                		{
		                		System.out.println("AUDIO/VIDEO/ACTIVITY DOES NOT LAUNCH");
		                		result_cell.setCellValue(Loading_issue);
//		                		VerifyActivity obj = new VerifyActivity();
						        failscreenshot.takeScreenshot(driver);
		                		continue;
		                		}
		                	}}
		                		
		                	
		                	
		                		  	
		                	
	                	
//		                	Thread.sleep(5000);
//		                	result_cell = sheet.getRow(j).getCell(2);
//					        result_cell.setCellValue(Other_icon_present1);
					        
			            
			            System.out.println("Switched frame 2");
			          
			           Thread.sleep(10000);
//			           instruction_appear =  driver.findElement(By.cssSelector(".instructions-btn"));
			            
			            
			            	try
			            	{
			            	WebDriverWait wait2 = new WebDriverWait(driver, 20);
			            	wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".instructions-btn")));
			                		
//			            	instruction_appear.isDisplayed())
			            	}
			            	catch(Exception al3)
			            {
//			            	Assert.assertFalse(instruction_appear.isDisplayed());
			            	System.out.println("Activity Not Loaded Successfully");
			            	result_cell = sheet.getRow(j).getCell(2);
					        result_cell.setCellValue(Activity_Not_Loading);
					        failscreenshot.takeScreenshot(driver);
					        continue;
			            }
			            Thread.sleep(1000);
			              
			            System.out.println("Activity Loaded Successfully");
		                result_cell = sheet.getRow(j).getCell(2);
					    result_cell.setCellValue(Activity_Pass);
		            	
			            
			            driver.switchTo().defaultContent();
			            
			             System.out.println("Back to Original Frame");
		        }
		        
		        FileOutputStream outFile =new FileOutputStream(new File("C:\\Work\\Bookshelf\\Automation\\Automation Life AmE Level 2\\Automation_Coverage_Life_AmE_Level_2_Test_Results.xls"));
		        filename.write(outFile);
		        outFile.close();
		        break;
		    }
			catch (FileNotFoundException exc) 
 		{
			exc.printStackTrace();
		}
 		
 		catch (IOException exc) 
 		
 		{
			exc.printStackTrace();
		}
			
	}
		else 
			System.out.println("Book not available");
		}
	
}

@AfterTest

public void BrowserClose () throws InterruptedException
{
	driver.quit();
	}
}