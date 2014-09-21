package test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CaptureElement 
{
	WebDriver driver;
	@BeforeMethod
	public void launch()
	{
		driver=new FirefoxDriver();
		driver.get("http://www.gmail.com");
	}
	
	@Test
	public void test() throws IOException
	{
		WebElement username=driver.findElement(By.xpath("//input[@id='Email']"));
		
		//Get the WrapsDriver of the WebElement
		WrapsDriver  wrapsDriver=(WrapsDriver) username;
		
		
		//Get the entire Screenshot from the driver of passed WebElement
		
		File  screen=((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
		
		//Create an instance of Buffered Image from captured screenshot
		
		BufferedImage  img=ImageIO.read(screen);
		
		// Get the Width and Height of the WebElement using
		
		int width=username.getSize().width;
		int height=username.getSize().height;
		
		//Create a rectangle using Width and Height
		
		Rectangle rect = new Rectangle(width, height);
		
		//Get the Location of WebElement in a Point.
		
		//This will provide X & Y co-ordinates of the WebElement
		
		Point p = username.getLocation();
		//Create image by for element using its location and size.
		//This will give image data specific to the WebElement
		
		BufferedImage dest=img.getSubimage(p.getX(), p.getY(), rect.width,rect.height);
		
		//Write back the image data for element in File object
		ImageIO.write(dest,"png", screen);
		
		//Return the File object containing image data
		FileUtils.copyFile(screen,new File("D:\\RAFIQ\\Project\\Linde_Au\\src\\test\\a.png"));
		
	}

}
