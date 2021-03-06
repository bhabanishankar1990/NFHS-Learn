package com.NFHS.PageObjects;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.NFHS.base.PageBase;
public class HomePage extends PageBase 
{
  
  WebDriver driver;
     
     public HomePage(WebDriver driver) throws IOException
     {
    	 super(driver);
    	 this.driver=driver;
    	 driver.manage().window().maximize();
    	 driver.get(siteUrls("nfhslearn"));
    	 if(driver.toString().contains("InternetExplorer"))
    	 {
          driver.get("javascript:document.getElementById('overridelink').click();");
    	 }
    	}
     
     public String Title()
     {
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	 return driver.getTitle();
     }
     
     public RegistrationPage registerLinkClick() throws Exception
     {
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	 webElementProperty("registerlink").click();
    	 return new RegistrationPage(driver);
    	 
     }
     
    
    
   
    
    
	
  
    
	
}
