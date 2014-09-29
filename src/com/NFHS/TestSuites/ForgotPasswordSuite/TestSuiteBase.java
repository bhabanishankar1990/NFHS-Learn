package com.NFHS.TestSuites.ForgotPasswordSuite;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.NFHS.base.PageBase;
import com.NFHS.base.TestBase;
import com.NFHS.util.TestUtil;


public class TestSuiteBase extends TestBase
{
	WebDriver driver;
	String Runmodes[];
	
	@BeforeSuite
	public void isSuiteSkipped() throws IOException
	{
		initialize();
		APP_LOGS.info("----Checking run mode of suite----");
		if(!TestUtil.isSuiteRunnable(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Suite","Test Suite","Forgot Password"))
			throw new SkipException("Runmode has set to no for given suite");		
	    //APP_LOGS.info("----Suite is executable---");
	}
    @BeforeMethod
	@Parameters("browser")
	public void launch_Browser(String browser)
		{
		APP_LOGS.info("----Launching Web Browser----");
		if(browser.equalsIgnoreCase("IE"))
		{
		  System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\jar\\IEDriverServer.exe");
		   driver=new InternetExplorerDriver();
		}
		else if(browser.equalsIgnoreCase("FF"))
		{
		    driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("CHROME"))
		{

		      System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\jar\\chromedriver.exe");
		      ChromeOptions options=new ChromeOptions();
		      options.addArguments("--test-type");
	            driver=new ChromeDriver(options);
		 }
        }
	
	 /* @BeforeMethod
	  public void launch_Browser()
	  {
		  driver=new FirefoxDriver();
	  }*/
	 public static void takeSnapShot(WebDriver driver,String filepath) throws IOException
	  {
		  TakesScreenshot srcshot=((TakesScreenshot)driver);
		  File srcFile=srcshot.getScreenshotAs(OutputType.FILE);
		  
		  File destFile=new File(filepath);
		  FileUtils.copyFile(srcFile, destFile);
	  }
	
	
	@AfterSuite
	public void reportSuiteResult()
	{
	
	}
}
