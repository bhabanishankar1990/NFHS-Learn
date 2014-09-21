package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HighlightingElement 
{
	WebDriver driver;
	@BeforeMethod
	 public void launch()
	{
		driver=new FirefoxDriver();
		driver.get("http://www.gmail.com");
	}
	
	@Test
	public void test() throws InterruptedException
	{
		WebElement element=driver.findElement(By.xpath("//input[@id='Email']"));
		for(int i=0;i<5;i++)
		{
			WrapsDriver wrappedElement=(WrapsDriver)element;
			JavascriptExecutor js=(JavascriptExecutor)wrappedElement.getWrappedDriver();
		    js.executeScript("arguments[0].setAttribute('style',arguments[1]);",element,"color:green;border:2px solid yellow;");
		    Thread.sleep(2000);
		    js.executeScript("arguments[0].setAttribute('style',arguments[1]);",element,"");
		}
	}
	 

}
