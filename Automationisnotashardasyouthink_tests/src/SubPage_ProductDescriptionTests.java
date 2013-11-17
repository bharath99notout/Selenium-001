import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Stopwatch;


public class SubPage_ProductDescriptionTests extends Helper_Subpages {
	
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
	
	
	@Test
	public void MouseHover_Cars_VerifyYearandDetails() throws IOException, InterruptedException
	{	
		
		ClickonMouseHover(driver, "subpage.MotorsMenu", "subpage.CarsandTrucksSubMenu", "subpage.CarsandTrucksTitle", "Subpages");		
		assertTrue(IsElementPresent(By.xpath(getValue("subpage.CarsandTrucksBreadcrumb", "Subpages")), driver));		
		
		for (int i = 1; i < 3; ) {
			String xpath_desc = getValue("subpage.FirstProductDescription", "Subpages");
			String xpathofelmt = String.format(xpath_desc, String.valueOf(i));
			
		
		WebElement element = driver.findElement(By.xpath(xpathofelmt));
		String proddesc = element.getAttribute("title");
		System.out.println(proddesc);
		
		//Compare year now and do it for two carsandtrucks without change in the xpath
		Pattern p = Pattern.compile("([0-9]{4})");
		Matcher m = p.matcher(proddesc);
		int year = 0;
		if (m.find())
		{
			String found = m.group(1);
			year = Integer.parseInt(found);
		}
		
		System.out.println(year);
		
		String xpath_desc1 = getValue("subpage.Year", "Subpages");
		String xpathofelmt1 = String.format(xpath_desc1, String.valueOf(i));
		
		WebElement yr = driver.findElement(By.xpath(xpathofelmt1));
		String year_actual = yr.getText();
		int yr_actual = Integer.parseInt(year_actual);
		System.out.println(yr_actual);	
		
		assertEquals(year, yr_actual);
		
		i++;
	}
	}
	
	@Test
	public void Grid_Cameras_Verifybyname_Xpath() throws IOException, InterruptedException
	{
		ClickonMouseHover(driver, "subpage.ElectronicsMenu", "subpage.CamerasandPhotoSubmenu", "subpage.CamerasandPhotoTitle", "Subpages");
		clickElement(driver, By.xpath(getValue("subpage.DigitalCamerasLink","Subpages")));
		waitForPagetoLoad_Element(driver, 120, EC.titleContains(getValue("subpage.DigitalCameraTitle", "Subpages")), true);
		clickElement(driver, By.xpath(getValue("subpage.FirstProductLink","Subpages")));
		waitForPagetoLoad_Element(driver, 120, By.xpath(getValue("subpage.BacktoSearchresultslink", "Subpages")));
		String URL = driver.getCurrentUrl();
		Document doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/22.0")
	              .referrer(URL)	
	              .timeout(120000)
			      .get();
		
		Elements elements_1 = doc.select(".section > table > tbody > tr");		
		int count1 = elements_1.size();
		System.out.println(count1);	
		
		GridKeyvalue(driver,doc, elements_1, count1);		
	}
	

	@Test
	public void Grid_Cameras_Verifybyname_CSS() throws IOException, InterruptedException
	{		
		ClickonMouseHover(driver, "subpage.ElectronicsMenu", "subpage.CamerasandPhotoSubmenu", "subpage.CamerasandPhotoTitle", "Subpages");
		clickElement(driver, By.cssSelector(getValue("subpage.DigitalCamerasLinkCss","Subpages")));
		waitForPagetoLoad_Element(driver, 120, EC.titleContains(getValue("subpage.DigitalCameraTitle", "Subpages")), true);
		clickElement(driver, By.cssSelector(getValue("subpage.FirstProductLinkCss","Subpages")));
		waitForPagetoLoad_Element(driver, 120, By.cssSelector(getValue("subpage.BacktoSearchresultslinkCss", "Subpages")));
		String URL = driver.getCurrentUrl();
		Document doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/22.0")
	              .referrer(URL)	
	              .timeout(120000)
			      .get();
		
		Elements elements_1 = doc.select(".section > table > tbody > tr");		
		int count1 = elements_1.size();
		System.out.println(count1);	
		
		GridKeyvalueCss(driver,doc, elements_1, count1);
	}
	
	@Test
	public void Grid_Bicycles_Verifybyname_Xpath() throws IOException, InterruptedException
	{
		ClickonMouseHover(driver, "subpage.SportingGoodsMenu", "subpage.CyclingSubmenu", "subpage.CyclingTitle", "Subpages");		
		clickElement(driver, By.xpath(getValue("subpage.BicyclesLink","Subpages")));
		waitForPagetoLoad_Element(driver, 120, EC.titleContains(getValue("subpage.BicyclesTitle", "Subpages")), true);
		clickElement(driver, By.xpath(getValue("subpage.FirstProductLink_Bicycles","Subpages")));
		waitForPagetoLoad_Element(driver, 120, By.xpath(getValue("subpage.BacktoSearchresultslink", "Subpages")));
		String URL = driver.getCurrentUrl();
		Document doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/22.0")
	              .referrer(URL)	
	              .timeout(120000)
			      .get();
		
		Elements elements_1 = doc.select(".section > table > tbody > tr");		
		int count1 = elements_1.size();
		System.out.println(count1);	
		
		GridKeyvalue(driver,doc, elements_1, count1);
	}
		@Test
		public void Grid2_Cameras_Verifybyname() throws IOException, InterruptedException
		{
			ClickonMouseHover(driver, "subpage.ElectronicsMenu", "subpage.CamerasandPhotoSubmenu", "subpage.CamerasandPhotoTitle", "Subpages");
			clickElement(driver, By.xpath(getValue("subpage.DigitalCamerasLink","Subpages")));
			waitForPagetoLoad_Element(driver, 120, EC.titleContains(getValue("subpage.DigitalCameraTitle", "Subpages")), true);
			clickElement(driver, By.xpath(getValue("subpage.FirstProductLink","Subpages")));
			waitForPagetoLoad_Element(driver, 120, By.xpath(getValue("subpage.BacktoSearchresultslink", "Subpages")));
			WebElement tableElement = driver.findElement(By.xpath(getValue("subpage.GridKeyValue_2","Subpages")));
			List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
			for(WebElement row : rows){

			    List<WebElement> columns = row.findElements(By.tagName("td"));

			          for(WebElement column : columns) {
			          System.out.println(column.getText());
			          }
			}
		}

}
