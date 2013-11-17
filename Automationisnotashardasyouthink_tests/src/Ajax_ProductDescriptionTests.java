import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class Ajax_ProductDescriptionTests extends Helper_Subpages {
	
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
	
	
	//Ajax - 1
	// Go to the Product Description page in ebay for a item who's bidding is ending
	// Use WaitFor method and wait until bidding has ended
	// Assert on the text that indicates that Bidding has ended on this item
	
	@Test
	public void Ajax1_Cameras_VerifyMessageafterbidtimeisover() throws IOException, InterruptedException
	{
		ClickonMouseHover(driver, "subpage.ElectronicsMenu", "subpage.CamerasandPhotoSubmenu", "subpage.CamerasandPhotoTitle", "Subpages");
		clickElement(driver, By.xpath(getValue("subpage.DigitalCamerasLink","Subpages")));
		waitForPagetoLoad_Element(driver, 120, EC.titleContains(getValue("subpage.DigitalCameraTitle", "Subpages")), true);
		String timeleft = null;
		for (int i = 1; i <= 3; i++) {
			
			String xpath_desc1 = getValue("subpage.TimeLeft", "Subpages");
			String xpathofelmt1 = String.format(xpath_desc1, String.valueOf(i));
			
			WebElement element_tl = driver.findElement(By.xpath(xpathofelmt1));
			timeleft = element_tl.getText();
			System.out.println(timeleft);
			if (!timeleft.isEmpty())
			{
				String xpath_desc = getValue("subpage.ProductLink_Cameras", "Subpages");
				String xpathofelmt = String.format(xpath_desc, String.valueOf(i));
				
			    clickElement(driver, By.xpath(getValue("subpage.FirstProductLink","Subpages")));
				waitForPagetoLoad_Element(driver, 120, By.xpath(getValue("subpage.BacktoSearchresultslink", "Subpages")));
				
				break;
			}				
		}		
		
		Pattern p = Pattern.compile("([0-9]{2})");
		Matcher m = p.matcher(timeleft);
		int timeinminutes = 0;
		if (m.find())
		{
			String found = m.group(1);
			timeinminutes = Integer.parseInt(found);
		}
		
		System.out.println(timeinminutes);			
		int timeinseconds = timeinminutes * 60;			
		System.out.println(timeinseconds);
		waitForPagetoLoad_Element(driver, timeinseconds, By.xpath(getValue("subpage.BiddingEndtext", "Subpages")));
		WebElement element_bidend = driver.findElement(By.xpath(getValue("subpage.BiddingEndtext", "Subpages")));
		String BidendtextObserved = element_bidend.getText();
		String BidendtextExpected = "Bidding has ended on this item.";
		assertEquals(BidendtextExpected, BidendtextObserved);	
	}
	
	//Ajax - 2
	
	// Go to the Product Description page in ebay
	// Click on Shipping and Payments tab
	// Change country to US and check if the zip code text field appears
		// Use Select option of the webdriver to traverse the options in the combo box
	// Click on Get Rates (Using Submit option)
		// Verify if the grid below gets refreshed automatically
	
	@Test
	public void Ajax2_Cameras_CheckZipCodeandGrid() throws IOException, InterruptedException
	{
		ClickonMouseHover(driver, "subpage.ElectronicsMenu", "subpage.CamerasandPhotoSubmenu", "subpage.CamerasandPhotoTitle", "Subpages");
		clickElement(driver, By.xpath(getValue("subpage.DigitalCamerasLink","Subpages")));
		waitForPagetoLoad_Element(driver, 120, EC.titleContains(getValue("subpage.DigitalCameraTitle", "Subpages")), true);
		String timeleft = null;
		for (int i = 1; i <= 3; i++) {				
			String xpath_desc1 = getValue("subpage.TimeLeft", "Subpages");
			String xpathofelmt1 = String.format(xpath_desc1, String.valueOf(i));
			
			WebElement element_tl = driver.findElement(By.xpath(xpathofelmt1));
			timeleft = element_tl.getText();				
			if (!timeleft.isEmpty())
			{
				String xpath_desc = getValue("subpage.ProductLink_Cameras", "Subpages");
				String xpathofelmt = String.format(xpath_desc, String.valueOf(i));
				
			    clickElement(driver, By.xpath(getValue("subpage.FirstProductLink","Subpages")));
				waitForPagetoLoad_Element(driver, 120, By.xpath(getValue("subpage.BacktoSearchresultslink", "Subpages")));
				
				break;
			}
		}
		
		clickElement(driver, By.xpath(getValue("subpage.ShoppingandPaymentsLink", "Subpages")));
		waitForPagetoLoad_Element(driver, 10, By.xpath(getValue("subpage.ShoppingText", "Subpages")));
		Select select = new Select(driver.findElement(By.id("shCountry")));
		//Selecting United States and asserting that the Get Rates Button is present
		select.selectByVisibleText("United States");
		assertTrue(IsElementPresent(By.xpath(getValue("subpage.GetRatesButton", "Subpages")), driver));
		WebElement element1 = driver.findElement(By.xpath(getValue("subpage.GetRatesButton", "Subpages")));
		//Selecting -Select- option, Get Rates button disappears 
		select.selectByVisibleText("-Select-");			
		assertTrue(element1.getText().equals(""));
		//Select Another country United States, enter zip code if present, submit. Check grid for AJAX behaviour
		select.selectByVisibleText("United States");
		WebElement zipcode = driver.findElement(By.xpath(getValue("subpage.ZipCodeTextBox", "Subpages"))); 
		if (zipcode.isDisplayed())
		{
			zipcode.sendKeys("95054");				
		}
		element1.click();			
		waitForPagetoLoad_Element(driver, 10, By.xpath(getValue("subpage.CountryName", "Subpages")));
		WebElement countryname = driver.findElement(By.xpath(getValue("subpage.CountryName", "Subpages")));
		System.out.println(countryname.getText());
		assertTrue(countryname.getText().equals("United States"));			
	}

}
