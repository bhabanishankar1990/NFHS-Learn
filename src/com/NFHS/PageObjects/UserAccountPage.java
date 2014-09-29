package com.NFHS.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.NFHS.base.PageBase;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class UserAccountPage extends PageBase

{
       WebDriver driver;
	public UserAccountPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;	
	}
	
	public String signedIn() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return webElementProperty("password_changed_success").getText();
	}
	
	public HomePage signOut() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webElementProperty("nfhs_logout_drop_down").click();
		webElementProperty("nfhs_logout").click();
		return new HomePage(driver);
	}
	
	public String  myCourses_Distribution_Licenses_Display() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webElementProperty("mycourses").click();
		Thread.sleep(1000);
		webElementProperty("distribution_link").click();
		return webElementProperty("distribution_licenses_text").getText();
	}
	public boolean check_Available_Courses(String invoicenumber) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webElementProperty("mycourses").click();
		Thread.sleep(1000);
		webElementProperty("distribution_link").click();
		Thread.sleep(1000);
		webElementProperty("avaliable_link").click();
		Select invoice=new Select(webElementProperty("select_dropdown"));
		invoice.selectByVisibleText(invoicenumber);
		return webElementProperty("available_box").isDisplayed();
	}
	public String send_Available_Course_Blank(String invoicenumber) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webElementProperty("mycourses").click();
		Thread.sleep(1000);
		webElementProperty("distribution_link").click();
		Thread.sleep(1000);
		webElementProperty("avaliable_link").click();
		Select invoice=new Select(webElementProperty("select_dropdown"));
		invoice.selectByVisibleText(invoicenumber);
		webElementProperty("send_button").click();
		return webElementProperty("invalid_mail_address_error").getText();
		
	}
	public String send_Available_Course_Firstname_Lastname(String invoicenumber,String firstname,String lastname,String email) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webElementProperty("mycourses").click();
		Thread.sleep(1000);
		webElementProperty("distribution_link").click();
		Thread.sleep(1000);
		webElementProperty("avaliable_link").click();
		Select invoice=new Select(webElementProperty("select_dropdown"));
		invoice.selectByVisibleText(invoicenumber);
		webElementProperty("first_name_box").sendKeys(firstname);
		webElementProperty("last_name_box").sendKeys(lastname);
		webElementProperty("email_box").sendKeys(email);
		webElementProperty("send_button").click();
		Thread.sleep(1000);
		return webElementProperty("invalid_mail_address_error").getText();
	}
	public String send_Available_Course_Pending_State(String invoicenumber,String firstname,String lastname,String email) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webElementProperty("mycourses").click();
		Thread.sleep(1000);
		webElementProperty("distribution_link").click();
		Thread.sleep(1000);
		webElementProperty("avaliable_link").click();
		Select invoice=new Select(webElementProperty("select_dropdown"));
		invoice.selectByVisibleText(invoicenumber);
		webElementProperty("first_name_box").sendKeys(firstname);
		webElementProperty("last_name_box").sendKeys(lastname);
		webElementProperty("email_box").sendKeys(email);
		webElementProperty("send_button").click();
		Thread.sleep(1000);
		return webElementProperty("user_already").getText();
		
		
		
	}
	public void oneEnabledTwoDisabled() throws Exception
	{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(webElementProperty("1breadcrumb").isEnabled() || !webElementProperty("2breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			WebElement nextbtn=webElementProperty("nextbutton");
			if(nextbtn.isEnabled())
			{
				nextbtn.click();
			}
		}
	}
	public void twoEnabledThreeDisabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("2breadcrumb").isEnabled() || !webElementProperty("3breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
	}
	public void threeEnabledFourDisabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("3breadcrumb").isEnabled() || !webElementProperty("4breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
	}
	public void fourEnabledFiveDisabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("4breadcrumb").isEnabled() || !webElementProperty("5breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
	}
	
	public void isOneEnabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("1breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
	}
	public void isTwoEnabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("2breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
		
	}
	public void isThreeEnabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("3breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
		
	}
	public void isFourEnabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("4breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
		
	}
	public void isFiveEnabled() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("5breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
		
	}
	public void nextButtonClick() throws Exception
	{
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
	}
	
	public void courseEvaluation() throws Exception
	{
		Actions builder=new Actions(driver);
		builder.click(webElementProperty("evaluation_rb1")).perform();
		builder.click(webElementProperty("evaluation_rb2")).perform();
		builder.click(webElementProperty("evaluation_rb3")).perform();
		builder.click(webElementProperty("evaluation_rb4")).perform();
		builder.click(webElementProperty("evaluation_rb5")).perform();
		builder.click(webElementProperty("evaluation_rb6")).perform();
		
	}
	
	
	public void coursePage() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webElementProperty("mycourses").click();
		webElementProperty("completecoursebutton").click();	
		
		if(webElementProperty("introduction").getText().equals("Introduction"))
		{
			System.out.println("next button is disabled");
			System.out.println(webElementProperty("nextbutton").isEnabled());
          Actions builder =new Actions(driver);
		  builder.click(webElementProperty("1strdbtn")).perform();
	
		
		  if(webElementProperty("cc_submitbutton").isEnabled())
		{
			  driver.manage().timeouts().implicitlyWait(5,TimeUnit.MINUTES);	
			webElementProperty("cc_submitbutton").click();
		}
		//2 question selected
		Thread.sleep(2000);
		builder.click(webElementProperty("1strdbtn")).perform();
		
		if(webElementProperty("cc_submitbutton").isEnabled())
		{
			webElementProperty("cc_submitbutton").click();
		}
		Thread.sleep(2000);
		//3 question selected
		builder.click(webElementProperty("1strdbtn")).perform();
		if(webElementProperty("cc_submitbutton").isEnabled())
		{
			webElementProperty("cc_submitbutton").click();
		}
		Thread.sleep(2000);
		//4 question selecte
		builder.click(webElementProperty("1strdbtn")).perform();
		
		if(webElementProperty("cc_submitbutton").isEnabled())
		{
			webElementProperty("cc_submitbutton").click();
		}
		
		Thread.sleep(2000);
		//5 question selected
		 builder.click(webElementProperty("1strdbtn")).perform();
			
		 if(webElementProperty("cc_submitbutton").isEnabled())
			{
				webElementProperty("cc_submitbutton").click();
			}
			
			
			Thread.sleep(2000);
		//6 question selected
			
         builder.click(webElementProperty("1strdbtn")).perform();
			
         if(webElementProperty("cc_submitbutton").isEnabled())
 		{
 			webElementProperty("cc_submitbutton").click();
 		}
			
			Thread.sleep(2000);
		//7 question selected
			  builder.click(webElementProperty("1strdbtn")).perform();
				
			  if(webElementProperty("cc_submitbutton").isEnabled())
				{
					webElementProperty("cc_submitbutton").click();
				}
				
				Thread.sleep(2000);
		//8 question selected
				  builder.click(webElementProperty("1strdbtn")).perform();
					
				  if(webElementProperty("cc_submitbutton").isEnabled())
					{
						webElementProperty("cc_submitbutton").click();
					}
					Thread.sleep(2000);
		//9 question selected
					  builder.click(webElementProperty("1strdbtn")).perform();
						
					  if(webElementProperty("cc_submitbutton").isEnabled())
						{
							webElementProperty("cc_submitbutton").click();
						}
						Thread.sleep(2000);
		//10 question selected
						  builder.click(webElementProperty("1strdbtn")).perform();
						  if(webElementProperty("cc_submitbutton").isEnabled())
							{
								webElementProperty("cc_submitbutton").click();
							}
							Thread.sleep(2000);
		//11 question selected
							  builder.click(webElementProperty("1strdbtn")).perform();
							  if(webElementProperty("cc_submitbutton").isEnabled())
								{
									webElementProperty("cc_submitbutton").click();
								}
							  Thread.sleep(1000);
							  nextButtonClick();
							 	
	}
		
		/*System.out.println("Before 4 if enabled");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		if(webElementProperty("3breadcrumb").isEnabled() || !webElementProperty("4breadcrumb").isEnabled())
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		WebElement nextbtn=webElementProperty("nextbutton");
		if(nextbtn.isEnabled())
		{
			nextbtn.click();
		}
		}
		
		 System.out.println("before 5 is enabled"); 
		 
		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			if(webElementProperty("4breadcrumb").isEnabled() || !webElementProperty("5breadcrumb").isEnabled())
			{
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			WebElement nextbtn=webElementProperty("nextbutton");
			if(nextbtn.isEnabled())
			{
				nextbtn.click();
			}
			} 
		
		  Thread.sleep(2000);
		  
		   
		 
		  
		 oneEnabledTwoDisabled();
		  
		  twoEnabledThreeDisabled();
		  
	      isThreeEnabled();
	      
		  oneEnabledTwoDisabled();
		  
		  twoEnabledThreeDisabled();
		  
		  isThreeEnabled();
		  
		  nextButtonClick();
		  
		  twoEnabledThreeDisabled();
		  
		  threeEnabledFourDisabled();
		  
		  isFourEnabled();
		  
		  oneEnabledTwoDisabled();
		  
		  twoEnabledThreeDisabled();
		  
		  threeEnabledFourDisabled();
		  
		  isFourEnabled();
		  
		  oneEnabledTwoDisabled();
		 
		  isTwoEnabled();
		  
		  oneEnabledTwoDisabled();
		  
		  twoEnabledThreeDisabled();
		  
		  threeEnabledFourDisabled();
		  
		  isFourEnabled();
		  
		  oneEnabledTwoDisabled();
		  
		  webElementProperty("unit3_Img1").click();
		  webElementProperty("unit3_Img2").click();
		  
		  nextButtonClick();
		  
		  isThreeEnabled();
		  
		  nextButtonClick();
		  
		  nextButtonClick();
		  
		  isOneEnabled();
		  
		  if(webElementProperty("1breadcrumb").isEnabled())
			{
			
			Thread.sleep(5000);
	        //1 question selected
	        Actions builder =new Actions(driver);
			builder.click(webElementProperty("1strdbtn")).perform();
		
			
			if(webElementProperty("cc_submitbutton").isEnabled())
			{
				webElementProperty("cc_submitbutton").click();
			}
			//2 question selected
			Thread.sleep(2000);
			builder.click(webElementProperty("1strdbtn")).perform();
			
			if(webElementProperty("cc_submitbutton").isEnabled())
			{
				webElementProperty("cc_submitbutton").click();
			}
			Thread.sleep(2000);
			builder.click(webElementProperty("1strdbtn")).perform();
			//3 question selected
			
			if(webElementProperty("cc_submitbutton").isEnabled())
			{
				webElementProperty("cc_submitbutton").click();
			}
			Thread.sleep(2000);
			//4 question selecte
			builder.click(webElementProperty("1strdbtn")).perform();
			
			if(webElementProperty("cc_submitbutton").isEnabled())
			{
				webElementProperty("cc_submitbutton").click();
			}
			
			Thread.sleep(2000);
			//5 question selected
			 builder.click(webElementProperty("1strdbtn")).perform();
				
			 if(webElementProperty("cc_submitbutton").isEnabled())
				{
					webElementProperty("cc_submitbutton").click();
				}
				
				
				Thread.sleep(2000);
			//6 question selected
				
	         builder.click(webElementProperty("1strdbtn")).perform();
				
	         if(webElementProperty("cc_submitbutton").isEnabled())
	 		{
	 			webElementProperty("cc_submitbutton").click();
	 		}
				
				Thread.sleep(2000);
			//7 question selected
				  builder.click(webElementProperty("1strdbtn")).perform();
					
				  if(webElementProperty("cc_submitbutton").isEnabled())
					{
						webElementProperty("cc_submitbutton").click();
					}
					
					Thread.sleep(2000);
			//8 question selected
					  builder.click(webElementProperty("1strdbtn")).perform();
						
					  if(webElementProperty("cc_submitbutton").isEnabled())
						{
							webElementProperty("cc_submitbutton").click();
						}
						Thread.sleep(2000);
			//9 question selected
						  builder.click(webElementProperty("1strdbtn")).perform();
							
						  if(webElementProperty("cc_submitbutton").isEnabled())
							{
								webElementProperty("cc_submitbutton").click();
							}
							Thread.sleep(2000);
			//10 question selected
							  builder.click(webElementProperty("1strdbtn")).perform();
							  if(webElementProperty("cc_submitbutton").isEnabled())
								{
									webElementProperty("cc_submitbutton").click();
								}
								Thread.sleep(2000);
			//11 question selected
								  builder.click(webElementProperty("1strdbtn")).perform();
									
								  if(webElementProperty("cc_submitbutton").isEnabled())
									{
										webElementProperty("cc_submitbutton").click();
									}
								  WebElement nextbtn1=webElementProperty("nextbutton");
									if(nextbtn1.isEnabled())
									{
										nextbtn1.click();
									}
			
			
		}
		  
		    JavascriptExecutor js=(JavascriptExecutor)driver;
		    js.executeScript("scroll(0,200)");
		    
		    nextButtonClick();
		    
		    oneEnabledTwoDisabled();
		    
		    isTwoEnabled();
		    
		    isOneEnabled();
		    isOneEnabled();
		    
		    courseEvaluation();
		    
		    webElementProperty("evaluation_submit").click();
		    
		    webElementProperty("download_certificate").click();*/
		    
		    
		    
		   
		    
		    
			
			
		}
		
	}
	
	
