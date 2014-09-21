package com.NFHS.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

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

}
