package DBAutomachineUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomachineUtils 
{
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static Actions action;
	
	public AutomachineUtils()
	{
	    System.setProperty("webdriver.chrome.driver","C:\\Users\\cseshu\\Desktop\\Chrome Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		wait = new WebDriverWait(driver, 60);
		action = new Actions(driver);
		
		driver.manage().window().maximize();
	}
	
	public void loadWebLink(String url)
	{
		driver.get(url);
	}
	
	public void utilSendKeys(String xpathData,String keyData)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathData)));
		driver.findElement(By.xpath(xpathData)).clear();
		driver.findElement(By.xpath(xpathData)).sendKeys(keyData);
	}
	
	public void utilClick(String xpathData)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathData)));
		driver.findElement(By.xpath(xpathData)).click();
	}
	
	public void utilDoubleClick(String xpathData)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathData)));
		action.doubleClick(driver.findElement(By.xpath(xpathData))).perform();
	}
	
	public void utilDoubleClick(WebElement e)
	{
		action.doubleClick(e).perform();
	}
	
	public WebElement getElement(String xpathData)
	{
		System.out.println(xpathData);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathData)));
		return driver.findElement(By.xpath(xpathData));
	}
	
	public List<WebElement> getElements(String xpathData)
	{
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathData)));
		List<WebElement> elements = driver.findElements(By.xpath(xpathData));
		return elements;
	}
	
	public void DragAndDrop(String xpath1,String xpath2)
	{
		WebElement element = getElement(xpath1); 

		WebElement target = getElement(xpath2);

		(new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
	public void DragAndDrop(WebElement element,String xpath2)
	{
		WebElement target = getElement(xpath2);

		(new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
	public void rightClick(WebElement e)
	{
		action.contextClick(e).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
	}
	
	public void closeSession()
	{
		driver.close();
	}
}
