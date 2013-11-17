import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import mx4j.log.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.seleniumhq.jetty7.util.log.Log;

import com.google.common.base.Stopwatch;


public class DynamicNewWindowAlertBox_BetFair_Tests extends Helper_Subpages {
	
	WebDriver driver;
	ExpectedConditions EC;	
	
	@Before
	public void OpenUrl() throws IOException {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.betfair.com/exchange/");
		waitForPagetoLoad_Element(driver, 60, By.xpath(getValue("betfair.SportsLink", "BetFairPage")));			
	}
	
	@After
	public void QuitDriver() {		      
        driver.quit();  	
	}
	
	
	@Test
	public void BetFair_DynamicIds_Test_UsingXPath() throws IOException, InterruptedException
	{	
		//Id's are not used as they change every time after page loads
		Stopwatch stopwatch = new Stopwatch().start();	
		clickElement(driver, By.xpath(getValue("betfair.CricketTab", "BetFairPage")));
		stopwatch.stop();
		long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		System.out.println("Xpath in milli seconds " + millis);
		assertTrue(IsElementPresent(By.xpath(getValue("betfair.CricketTabCheck", "BetFairPage")), driver));
		clickElement(driver, By.xpath(getValue("betfair.CricketFirstLink", "BetFairPage")));
		assertTrue(IsElementPresent(By.xpath(getValue("betfair.ExchangeOddsLink", "BetFairPage")), driver));		
	}
	
	@Test
	public void BetFair_DynamicIds_Test_UsingCSS() throws IOException, InterruptedException
	{	
		//Id's are not used as they change every time after page loads	
		Stopwatch stopwatch = new Stopwatch().start();
		clickElement(driver, By.cssSelector(getValue("betfair.CricketTabCss", "BetFairPage")));
		stopwatch.stop();
		long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		System.out.println("Css in milli seconds " + millis);
		assertTrue(IsElementPresent(By.cssSelector(getValue("betfair.CricketTabCheckCss", "BetFairPage")), driver));
		clickElement(driver, By.cssSelector(getValue("betfair.CricketFirstLinkCss", "BetFairPage")));
		assertTrue(IsElementPresent(By.cssSelector(getValue("betfair.ExchangeOddsLinkCss", "BetFairPage")), driver));		
	}
	
	@Test
	public void BetFair_Feedback_HandlingNewWindow() throws IOException, InterruptedException
	{	
		String basewindow = driver.getWindowHandle();
		clickElement(driver, By.xpath(getValue("betfair.FeedbackLink", "BetFairPage")));
		String popup = null;			
		Set<String> windowIterator = driver.getWindowHandles();
		if (windowIterator.size() == 2)
		{
		 	for (String WindowHandle : windowIterator)
		 	{
		 		if (!WindowHandle.equals(basewindow))		
		 		{
		 			popup = WindowHandle;
		 			driver.switchTo().window(popup);
		 		}
		 	}
		}
			
		assertTrue(IsElementPresent(By.xpath(getValue("betfair.PostIdeaLink", "BetFairPage")), driver));
		clickElement(driver, By.xpath(getValue("betfair.CloseLink", "BetFairPage")));
		Set<String> windowIterator_AfterClosingpopup = driver.getWindowHandles();
		assertTrue(windowIterator_AfterClosingpopup.size() == 1);		
	}
	
	@Test
	public void BetFair_OldSite_HandlingPopUpAlerts() throws IOException, InterruptedException
	{	
		clickElement(driver, By.xpath(getValue("betfair.BackgammonLink", "BetFairPage")));
		waitForPagetoLoad_Element(driver, 60, By.xpath(getValue("betfair.BackgammonText", "BetFairPage")));
		clickElement(driver, By.xpath(getValue("betfair.PlayNowLink", "BetFairPage")));
		Alert alert = driver.switchTo().alert();
		alert.accept();			
		try
		{
			alert.getText();
		}
		
		catch (NoAlertPresentException ex)
		{
			assertTrue(ex.getMessage().contains("No alert is present"));
		}		
	}
}
