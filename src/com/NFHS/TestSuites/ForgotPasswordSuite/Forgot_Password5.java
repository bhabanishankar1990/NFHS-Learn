package com.NFHS.TestSuites.ForgotPasswordSuite;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.NFHS.PageObjects.ForgotPasswordPage;
import com.NFHS.PageObjects.HomePage;
import com.NFHS.base.PageBase;
import com.NFHS.util.ErrorUtil;
import com.NFHS.util.TestUtil;

public class Forgot_Password5 extends TestSuiteBase
{

	String Runmodes[];
	int count=0;
	static boolean skip=false;
	static boolean fail=false;
	static boolean pass=false;
	static boolean isTestfail=false;
	static boolean isTestSkip=false;
	static boolean isSheetExist=false;
	
	HomePage homepage;
	ForgotPasswordPage forgotpassword;
	
	
	
	@BeforeTest
	public void isTestSkipped() throws Exception
	{
		APP_LOGS.info("----Checking the Runmode of :"+this.getClass().getSimpleName()+"----");
		//System.out.println(TestUtil.isTestCaseRunnable(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password","Test Cases",this.getClass().getSimpleName()));
		if(!TestUtil.isTestCaseRunnable(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password","Test Cases",this.getClass().getSimpleName()))
		{
			isTestSkip=true;
			throw new SkipException("--Runmode is set to NO for given testcase--");
		}
		APP_LOGS.info("----Test is excuteable----");
		isSheetExist=TestUtil.isSheetExists(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password",this.getClass().getSimpleName());
		if(isSheetExist)
		{
		Runmodes=TestUtil.getRunmodes(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password",this.getClass().getSimpleName());
		}
		APP_LOGS.info("----Launching Web Browser----");
		
		
	}
	
	
	
	@Test(dataProvider="forgot_Password5_Test_Data")
	public void forgot_Password_5(String emailaddress,String gpassword) throws Exception
	{   
		
		count++;
		APP_LOGS.info("----Checking Runmode of Testdata:"+this.getClass().getSimpleName()+"----");
		 if(isSheetExist && !Runmodes[count].equalsIgnoreCase("yes"))
		 {
			 skip=true;
			 throw new SkipException("Runmode of this test data is set to NO");	
		 }
		
		APP_LOGS.info("----Executing Testcase---- :"+this.getClass().getSimpleName()+"----");
	         
	         homepage=new HomePage(driver);
	     try
	          {
	               forgotpassword=homepage.forgotPasswordLinkClick();
	              String emailtext=forgotpassword.emailAddressValid(emailaddress);
	               Assert.assertEquals(emailtext,"You will receive an email with instructions about how to reset your password in a few minutes.");
	              
	          
	         }
	       catch(Throwable t)
	          {
	        	  fail=true;
	        	 ErrorUtil.addVerificationFailure(t); 
	          }
	     try
	      {
	    	    driver.navigate().to("http://gmail.com");
   	            PageBase pb=new PageBase(driver);
   	            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   	            pb.webElementProperty("gmail_email").sendKeys(emailaddress);
   			    pb.webElementProperty("gmail_password").sendKeys(gpassword);
   			    pb.webElementProperty("gmail_submit").click();
   			    pb.webElementProperty("inbox").click();
   			    Thread.sleep(1000);
   			    driver.findElement(By.className("gbqfif")).sendKeys("email@weboapps.com ");
   			    pb.webElementProperty("searchbox_button").click();
   			    List<WebElement> elements=driver.findElements(By.xpath("//span[@class='y2']/b"));
   			    elements.get(0).click();
	    	    pb.webElementProperty("change_my_password_link").click();
	    	    Thread.sleep(2000);
	    	    String parentwindow=driver.getWindowHandle();
	    		Set<String> allwindows=driver.getWindowHandles();
	    		System.out.println("Window value"+allwindows.isEmpty());
	    			if(!allwindows.isEmpty())
	    			{
	    			for(String windowid:allwindows)
	    			{
	    				if(driver.switchTo().window(windowid).getTitle().equals("NfhsLearn"))
	    				{
	    					
	    				    String changepwdtext=pb.webElementProperty("change_password_text").getText();
	    				    Assert.assertEquals(changepwdtext,"Change your password");
	    				    driver.close();
	    				    break;
	    				}
	    			}
	    				
	    			}
	    			driver.switchTo().window(parentwindow);
	    			pb.webElementProperty("gmail_logout_drop").click();
	    			Thread.sleep(1000);
	    			pb.webElementProperty("gmail_signout").click();
	    			 }
	      catch(Throwable t)
          {
        	  fail=true;
        	 ErrorUtil.addVerificationFailure(t); 
          }
	                  
	
	}	
	@AfterMethod
	public void reporter() throws InvalidFormatException, IOException
	{
	
		if(isSheetExist)
		if(skip)
		{
		TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password", this.getClass().getSimpleName(), count, "Results", "skipped");
		}
		else if(fail)
		{
			
			isTestfail=true;
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password", this.getClass().getSimpleName(), count, "Results", "fail");
		
		}
		else 
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password", this.getClass().getSimpleName(), count, "Results", "pass");
		}
			skip=false;
			fail=false;
	    
		
		APP_LOGS.info("---Closing Web browser---");
		driver.close();
		driver.quit();
		
		
		
	}
	
	@AfterTest
	public void reportTestCaseResult() throws InvalidFormatException, IOException
	{

		int rowNo=TestUtil.getRowNumber(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password", "Test Cases",this.getClass().getSimpleName());
		if(!isTestfail)
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password", "Test Cases", rowNo-1,"Results", "Pass");
		}
		else if(isTestfail)
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password", "Test Cases", rowNo-1,"Results", "Fail");
		}
		else if(isTestSkip)
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password", "Test Cases", rowNo-1,"Results", "Skipped");
			
		}
		isTestfail=false;	
	}
	
	@DataProvider
	public Object[][] forgot_Password5_Test_Data()
	{
		Object[][] data=TestUtil.getData(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Forgot Password","Forgot_Password5");
	    return(data);
	}
}
