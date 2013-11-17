import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Helper_Subpages extends BaseTest {
	
	ExpectedConditions EC;
		
	public void ClickonMouseHover(WebDriver driver, String Key1, String Key2, String Key3, String properties) throws IOException, InterruptedException
	{
		Actions actions = new Actions(driver);		
		WebElement MotorsMenu = driver.findElement(By.linkText(getValue(Key1, properties)));				
		actions.moveToElement(MotorsMenu).build().perform();		
		WebElement Submenu = driver.findElement(By.linkText(getValue(Key2, properties)));		
		
		Submenu.click();
		Thread.sleep(3000);
		actions.release(MotorsMenu);
		waitForPagetoLoad_Element(driver, 60, EC.titleContains(getValue(Key3, properties)), true);
	}
	
	public void GridKeyvalue(WebDriver driver, Document doc, Elements elements, int count1) throws IOException
	{
		int l = 1, m = 0, n = 0, y = 0, k = 1, countth = 0;		
		Element text = null;
		String texts = "empty";
		WebElement element;
		try
		{
			text = elements.select("th").first();
			texts = text.text();
			System.out.println(texts);		
						
		}
		
		catch(NullPointerException ex)
		{
			System.out.println(ex.getMessage());			
		}
		
		
		if (texts.equals("Condition:"))
		{
			Elements elements_3 = doc.select(".section > #itmSellerDesc > tbody > tr");
			countth = elements_3.size();
			for (int j = 1 ; j <= countth ; j++)
			{
				String xpath_desc = getValue("subpage.GridTHKey", "Subpages");
				String xpathofelmt = String.format(xpath_desc, String.valueOf(j));
				element = driver.findElement(By.xpath(xpathofelmt));
				String Key = element.getText();
				System.out.println("Key is " + Key);
				
				String xpath_desc1 = getValue("subpage.GridTHValue", "Subpages");
				String xpathofelmt1 = String.format(xpath_desc1, String.valueOf(j));
				element = driver.findElement(By.xpath(xpathofelmt1));
				String Value = element.getText();
				System.out.println("Value is " + Value);					
			}			
			l++;			
		}
			
		int finalcount = count1 - countth;
		System.out.println(finalcount);
		
			for (int i = 0; i < finalcount ; i++) {				
			
				for (y = 1; y <= 2; y++) {				
				
				if (y == 1)
				{						
					m = 1;
				}
				
				else 
				{
					m = 3;
				}						
				
				String xpath_desc = getValue("subpage.GridKeyValue", "Subpages");
				String xpathofelmt = String.format(xpath_desc, String.valueOf(l), String.valueOf(k), String.valueOf(m));
								
				try
				{
					element = driver.findElement(By.xpath(xpathofelmt));
					System.out.println("Key is " + element.getText());
				}
				
				catch (NoSuchElementException ex)
				{
					//System.out.println(ex.getMessage());
					break;
				}
							
				if (y == 1)
				{ 
					n = 2;
				}
				
				else 
				{
					n = 4;
				}	
				
				String xpath_desc2 = getValue("subpage.GridKeyValue", "Subpages");
				String xpathofelmt1 = String.format(xpath_desc2, String.valueOf(l), String.valueOf(k), String.valueOf(n));
				
				try
				{
					element = driver.findElement(By.xpath(xpathofelmt1));
					System.out.println("Value is " + element.getText() + "\n");				
				}
				
				catch (NoSuchElementException ex)
				{
					//System.out.println(ex.getMessage());
					break;
				}
				
				
			}
				
				k++;
		}		
	}
	
	public void GridKeyvalueCss(WebDriver driver, Document doc, Elements elements, int count1) throws IOException
	{
		int l = 1, m = 0, n = 0, y = 0, k = 1, countth = 0;		
		Element text = null;
		String texts = "empty";
		WebElement element;
		try
		{
			text = elements.select("th").first();
			texts = text.text();
			System.out.println(texts);		
						
		}
		
		catch(NullPointerException ex)
		{
			System.out.println(ex.getMessage());			
		}
		
		
		if (texts.equals("Condition:"))
		{
			Elements elements_3 = doc.select(".section > #itmSellerDesc > tbody > tr");
			countth = elements_3.size();
			for (int j = 1 ; j <= countth ; j++)
			{
				String xpath_desc = getValue("subpage.GridTHKeyCss", "Subpages");
				String xpathofelmt = String.format(xpath_desc, String.valueOf(j));
				element = driver.findElement(By.cssSelector(xpathofelmt));
				String Key = element.getText();
				System.out.println("Key is " + Key);
				
				String xpath_desc1 = getValue("subpage.GridTHValueCss", "Subpages");
				String xpathofelmt1 = String.format(xpath_desc1, String.valueOf(j));
				element = driver.findElement(By.cssSelector(xpathofelmt1));
				String Value = element.getText();
				System.out.println("Value is " + Value);					
			}			
			l++;			
		}
			
		int finalcount = count1 - countth;
		System.out.println(finalcount);
		
			for (int i = 0; i < finalcount ; i++) {				
			
				for (y = 1; y <= 2; y++) {				
				
				if (y == 1)
				{						
					m = 1;
				}
				
				else 
				{
					m = 3;
				}						
				
				String xpath_desc = getValue("subpage.GridKeyValueCss", "Subpages");
				String xpathofelmt = String.format(xpath_desc, String.valueOf(l), String.valueOf(k), String.valueOf(m));
								
				try
				{
					element = driver.findElement(By.cssSelector(xpathofelmt));
					System.out.println("Key is " + element.getText());
				}
				
				catch (NoSuchElementException ex)
				{
					//System.out.println(ex.getMessage());
					break;
				}
							
				if (y == 1)
				{ 
					n = 2;
				}
				
				else 
				{
					n = 4;
				}	
				
				String xpath_desc2 = getValue("subpage.GridKeyValueCss", "Subpages");
				String xpathofelmt1 = String.format(xpath_desc2, String.valueOf(l), String.valueOf(k), String.valueOf(n));
				
				try
				{
					element = driver.findElement(By.cssSelector(xpathofelmt1));
					System.out.println("Value is " + element.getText() + "\n");				
				}
				
				catch (NoSuchElementException ex)
				{
					//System.out.println(ex.getMessage());
					break;
				}
				
				
			}
				
				k++;
		}		
	}
} 
