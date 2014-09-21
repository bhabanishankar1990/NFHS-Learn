package com.NFHS.TestSuites.RegistrationSuite;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.NFHS.PageObjects.HomePage;
import com.NFHS.PageObjects.RegistrationPage;
import com.NFHS.util.ErrorUtil;
import com.NFHS.util.TestUtil;

public class Registration_Account_Setup extends TestSuiteBase
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
	RegistrationPage rp;
	
	
	@BeforeTest
	public void isTestSkipped() throws Exception
	{
		APP_LOGS.info("----Checking the Runmode of :"+this.getClass().getSimpleName()+"----");
		//System.out.println(TestUtil.isTestCaseRunnable(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration","Test Cases",this.getClass().getSimpleName()));
		if(!TestUtil.isTestCaseRunnable(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration","Test Cases",this.getClass().getSimpleName()))
		{
			isTestSkip=true;
			throw new SkipException("--Runmode is set to NO for given testcase--");
		}
		APP_LOGS.info("----Test is excuteable----");
		isSheetExist=TestUtil.isSheetExists(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration",this.getClass().getSimpleName());
		if(isSheetExist)
		{
		Runmodes=TestUtil.getRunmodes(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration",this.getClass().getSimpleName());
		}
		APP_LOGS.info("----Launching Web Browser----");
		
		
	}
	
	
	
	@Test(dataProvider="registration_Account_Setup_Test_Data")
	public void Registration_of_New_User(String emailaddress,String confirmemailaddress,String  password,String confirmpassword) throws Exception
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
	                  rp=homepage.registerLinkClick();
	                  boolean breadcrubmval=rp.registerLinkVerify();
	                  Assert.assertEquals(breadcrubmval, true);    
	          
	         }
	        catch(Throwable t)
	          {
	        	  fail=true;
	        	 ErrorUtil.addVerificationFailure(t); 
	          }
	                  
	          try
	           {
	                  String accounttext=rp.accountSetUpSelected();
	                  Assert.assertEquals(accounttext, "Account Setup");
	                  
	                  boolean checkallfields=rp.checkFieldsInAccountSetUpLayout();
	                  Assert.assertEquals(checkallfields, true);
	           }
	     	    catch(Throwable t)
	     	       {
	     	        	  fail=true;
	     	        	 ErrorUtil.addVerificationFailure(t); 
	     	       }
	               try
		    	       {
	                      boolean errormessage=rp.errorMessageOnNextClick();
	                      Assert.assertEquals(errormessage, true);
		    	      }
	                  catch(Throwable t)
		     	       {
		     	        	  fail=true;
		     	        	 ErrorUtil.addVerificationFailure(t); 
		     	       }
	                     try
			    	       {
	                       String emailaddresserror=rp.registerUserError(emailaddress, confirmemailaddress, password, confirmpassword);   
	                      Assert.assertEquals(emailaddresserror,"Please enter a valid email address.");
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
		TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration", this.getClass().getSimpleName(), count, "Results", "skipped");
		}
		else if(fail)
		{
			
			isTestfail=true;
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration", this.getClass().getSimpleName(), count, "Results", "fail");
		
		}
		else 
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration", this.getClass().getSimpleName(), count, "Results", "pass");
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

		int rowNo=TestUtil.getRowNumber(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration", "Test Cases",this.getClass().getSimpleName());
		if(!isTestfail)
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration", "Test Cases", rowNo-1,"Results", "Pass");
		}
		else if(isTestfail)
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration", "Test Cases", rowNo-1,"Results", "Fail");
		}
		else if(isTestSkip)
		{
			TestUtil.updateResult(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration", "Test Cases", rowNo-1,"Results", "Skipped");
			
		}
		isTestfail=false;
	
		
		
	}
	   @DataProvider 
	   public Object[][] registration_Account_Setup_Test_Data()
	   {
		   Object[][] data=TestUtil.getData(System.getProperty("user.dir")+"\\src\\com\\NFHS\\xls","Registration","Registration_Account_Setup");
	       return(data);
	   }
	
}


