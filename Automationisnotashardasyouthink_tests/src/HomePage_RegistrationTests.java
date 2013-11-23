import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.ErrorHandler.UnknownServerException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Stopwatch;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HomePage_RegistrationTests extends Helper_Subpages {
	
	WebDriver driver;
	ExpectedConditions EC;	
	
	@Before
	public void OpenUrl() throws IOException {
		
		driver = new FirefoxDriver();
	    driver.manage().window().maximize(); 
	    driver.get("http://www.ebay.com");
		waitForPagetoLoad_Element(driver, 60, EC.elementToBeClickable(By.linkText(getValue("homepage.registerlink", "HomePage"))));	
		
		
		}	
	
	@After 
	public void QuitDriver() {		      
        driver.quit();  	
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void Click_Register_VerifyRegistrationPage() throws IOException, InterruptedException {
		
		
		clickElement(driver, By.linkText(getValue("homepage.signinlink","HomePage")));
	
		waitForPagetoLoad_Element(driver, 60, EC.titleContains(getValue("homepage.registerpagetitle_value", "HomePage")), true);
		
		assertTrue(IsElementPresent(By.xpath(getValue("homepage.registerpagetitle_element", "HomePage")), driver));
	}
	
	

}



