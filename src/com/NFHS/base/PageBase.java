package com.NFHS.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;





public class PageBase
{
	Properties prop;
	FileInputStream fis;
	WebDriver driver;
	public PageBase(WebDriver driver)
	{
		this.driver=driver;
	}

	public WebElement webElementProperty(String webElement) throws Exception
	{
		prop=new Properties();
		fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\com\\NFHS\\config\\Element_Locator.properties"));
		prop.load(fis);
		String elementProperty=prop.getProperty(webElement);
		String Locator[]=elementProperty.split(",");
		String locatorType=Locator[0];
		String locatorValue=Locator[1];
	    
		if(locatorType.equalsIgnoreCase("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else if(locatorType.equalsIgnoreCase("id"))
			return driver.findElement(By.id(locatorValue));
		else if(locatorType.equalsIgnoreCase("name"))
			return driver.findElement(By.name(locatorValue));
		else if(locatorType.equalsIgnoreCase("linktext"))
			return driver.findElement(By.linkText(locatorValue));
		else if(locatorType.equalsIgnoreCase("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if(locatorType.equalsIgnoreCase("tagname")||locatorType.equalsIgnoreCase("tag"))
			return driver.findElement(By.tagName(locatorValue));
		else if(locatorType.equalsIgnoreCase("cssSelector")||locatorType.equalsIgnoreCase("css"))
			return driver.findElement(By.cssSelector(locatorValue));
		else if(locatorType.equalsIgnoreCase("class")||locatorType.equalsIgnoreCase("classname"))
			return driver.findElement(By.className(locatorValue));
	    else
				throw new Exception("Unable to find the element");		
	   }
	
	
	  public String  siteUrls(String siteName) throws IOException
	  {
		  prop=new Properties();
		  fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\com\\NFHS\\config\\Environment.properties"));
		  prop.load(fis);
		  String Url=prop.getProperty(siteName);
		  return Url;  
	  }
	  
	  public static void takeSnapShot(WebDriver driver,String filepath) throws IOException
	  {
		  TakesScreenshot srcshot=((TakesScreenshot)driver);
		  File srcFile=srcshot.getScreenshotAs(OutputType.FILE);
		  
		  File destFile=new File(filepath);
		  FileUtils.copyFile(srcFile, destFile);
	  }
	
}
