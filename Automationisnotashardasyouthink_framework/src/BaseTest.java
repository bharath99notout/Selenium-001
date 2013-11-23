import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;


public class BaseTest {
	
	String value = null;
	WebDriverWait wd;

	
	public boolean IsElementPresent(By Locator, WebDriver Wd)
	{
		try
		{
			Wd.findElement(Locator);
			return true;
		}
		
		catch (Exception ex)
		{
			System.out.println("Error is " + " " + ex.getMessage());
			return false;
		}
	}	
	
	public String getValue(String Key, String Propname) throws IOException
	{
		Properties prp = new Properties();
		
		String path = "C:/Users/Bharath/git/selenium/Automationisnotashardasyouthink_framework/Objects/Homepage.properties";
               	
		String newpath = path.replaceAll("Propfile", Propname);
	
		FileInputStream in = new FileInputStream(newpath);
		
		prp.load(in);
	
		for(@SuppressWarnings("unused") Object str: prp.keySet())
		{
			value = prp.getProperty(Key);
			//System.out.println(value);
		}
	
		return value;
		
	}
	
	public void clickElement(WebDriver driver, By by) throws IOException
	{
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	@SuppressWarnings("unchecked")
	public boolean waitForPagetoLoad_Element(WebDriver driver, int timeout, ExpectedCondition<WebElement> expectedCondition)
	{
		wd = new WebDriverWait(driver, timeout);
		wd.until(expectedCondition);		
		return true;
	}
	

	@SuppressWarnings("unchecked")
	public boolean waitForPagetoLoad_Element(WebDriver driver, int timeout, ExpectedCondition<Boolean> expectedCondition, boolean NotWebElement)
	{
		wd = new WebDriverWait(driver, timeout);
		wd.until(expectedCondition);
		return true;
	}
	
	public boolean waitForPagetoLoad_Element(WebDriver driver, int timeout, By by)
	{
		wd = new WebDriverWait(driver, timeout);		
		WebElement element = driver.findElement(by);		
		return true;
	}
	
	//To be used at the driver level
	public boolean waitForPagetoLoad_Element_Implicit(WebDriver driver, int timeout, By by)			
	{
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);		
		WebElement element = driver.findElement(by);
		return true;
	}	
}
