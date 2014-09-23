package com.NFHS.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.NFHS.base.PageBase;

public class ForgotPasswordPage extends PageBase
{
	
	WebDriver driver;
	public ForgotPasswordPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public String forgotPasswordText() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return webElementProperty("forgotyourpasswordtext").getText();
	}
	public boolean emailAddressField() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		if(webElementProperty("Forgot_emailaddress").isDisplayed())
			return true;
		else 
			return false;
	}
	public boolean emailAddressBlankError() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		webElementProperty("Forgot_confirmbutton").click();
		if(webElementProperty("Forgot_emailerror").getText().contains("Email can't be blank"))
		return true;
		else 
		return false;	
	}
	public boolean emailAddressInvalidError(String emailaddress) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		webElementProperty("Forgot_emailaddress").sendKeys(emailaddress);
		webElementProperty("Forgot_confirmbutton").click();
		if(webElementProperty("Forgot_emailerror").getText().contains("Email is not present in system,please try another one."))
		return true;
		else 
		return false;
	}

}
