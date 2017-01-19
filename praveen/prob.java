import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult; 

import org.w3c.dom.Element;

import java.util.List;
 class Read_Xml_File {

    public static String ReadFile(String xmlname,String root,String subtag,String name)
{

        String testinput = null;
        File fXmlFile = new File("C:\\Users\\pmutchar\\Desktop\\"+xmlname);
       // System.out.println(fXmlFile);
        
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        
        
	    try 
	    {
	        dBuilder = dbFactory.newDocumentBuilder();
	    } 
	    catch (ParserConfigurationException e)
	    {
	        e.printStackTrace();
	    }
	    
	    
	    Document doc= null;
	    try
	    {
	        doc = dBuilder.parse(fXmlFile);
	    } 
	    catch (SAXException e)
	    {
	        e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    } 
	    
	    
        XPath xPath =  XPathFactory.newInstance().newXPath();
        String ele = String.format("/"+root+"/"+subtag+"/"+name);

        Node node = null;
        
	    try
	    {
	        node = (Node) xPath.compile(ele).evaluate(doc, XPathConstants.NODE);
	    } 
	    catch (XPathExpressionException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    testinput = node != null ? (node.getTextContent()) : "cannot read the test case xml file";
	        return testinput;

}

}
 class WriteXMl
 {
	 Document doc=null;
	 Element rootElement=null;
 public WriteXMl(String root)
 {
 	try
 	{
 		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
 		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 		doc = docBuilder.newDocument();
 		rootElement = doc.createElement(root);
 		createRootTag();
 	}
 	  catch (ParserConfigurationException pce) 
	  {
		pce.printStackTrace();
	  } 
	
 }
 public void XMLFormat()
 {
	 try
	 {
 		TransformerFactory transformerFactory = TransformerFactory.newInstance();
 		Transformer transformer = transformerFactory.newTransformer();
 	
 	
 		DOMSource source = new DOMSource(doc);
 		StreamResult result = new StreamResult(new File("C:\\Users\\pmutchar\\Desktop\\outputXML.xml"));

 		transformer.transform(source, result);
 		
 		System.out.println("File saved!");
 		
 	}
	 catch(Exception e)
	 {
		 
	 }

 	}
 public void createRootTag()
 {
 	
 	doc.appendChild(rootElement);
 }
 public void createChildTags(String suptag,String subtag,String status)
 {
	Element supname=doc.createElement(suptag);
 	//Element firstname = doc.createElement(subtag);
	Element firstname=(Element) supname.appendChild(doc.createElement(subtag));
 	firstname.appendChild(doc.createTextNode(status));
 	rootElement.appendChild(supname);
 	}
 }
class Connection{
	
	WebDriver driver;
	public Connection() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\pmutchar\\Downloads\\chromedriver.exe");
		 driver = new ChromeDriver();
	}
	public  WebDriver openBrowser() throws InterruptedException
	{
	    driver.get(Read_Xml_File.ReadFile("url.xml","url" ,"urls","BigDataAnalytics"));
	    driver.manage().window().maximize();
	    Thread.sleep(1000);
		return driver;
	}
	public void closeBrowser()
	{
		driver.close();
	}
	
}
public class BDATesting {
	WebDriver driver;
	Connection conn;
	public BDATesting(Connection conn) throws InterruptedException
	{
		this.conn=conn;
		driver=conn.openBrowser();
		driver.findElement(By.id("username")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","credentials","username"));
	    driver.findElement(By.xpath("//*[@id='login']/div/div/div[2]/div[1]/input[2]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","credentials","pwd"),Keys.ENTER);
	    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[1]/div/div/div[7]")).click(); //Load
	    Thread.sleep(5000);
	}
    public String instanceValidation(String st,String ds) throws InterruptedException
    {
    	int cf = 0;
    	if(ds.equals("Oracle"))
    		cf=4;
    	else if(ds.equals("Ms-SQL"))
    		cf=5;
    	else if(ds.equals("MySQL"))
    		cf=11;
    	else if(ds.equals("Postgresql"))
    		cf=12;
	     // Load operation
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[2]/div[2]")).click(); //data source addition using + operator
	    Thread.sleep(1000);
	    
	    List<WebElement> webele=driver.findElements(By.xpath("/html/body/div[3]/div[3]/div[2]/div/div/div/div")); //postgres selection
	    int i=1;
	    try
	    {
	    for(WebElement el:webele)
	    {	

	    	if(cf==i)
	    	{
	    		System.out.println(cf);
	    		el.click();
	    		Thread.sleep(1000);
	    		break;
	    	}
	    	i++;
	    }
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    Thread.sleep(2000);
	    System.out.println(Read_Xml_File.ReadFile("testData.xml","Data",st,"Name"));
	
	    int number = 0;
		if(st.equals("postgres"))
    		number=6;
	    else if(st.equals("oracle"))
    		number=10;
	    else if(st.equals("mysql"))
    		number=17;
	    else if(st.equals("sqlserver"))
    		number=15;
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[1]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data",st,"Name")); 
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[2]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","postgres","Description"));
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[3]")).clear();
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[3]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data",st,"Server"));
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[5]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data",st,"User"));
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[6]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data",st,"Password"));
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[7]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data",st,"Database"));
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/input[8]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data",st,"Options"));
	    driver.findElement(By.xpath("/html/body/div[3]/div["+number+"]/div[2]/div[1]/div[9]/div")).click();
	    Thread.sleep(2000);
	    
	    String validate =driver.findElement(By.xpath("/html/body/div[3]/div[7]/div[2]/div[1]/div/div")).getText();        // Test operation
	    
	    if(validate.equals("Connection test finished successfully"))
	    {
		   System.out.println("verified");
	    }
	    else
	    {
		   System.out.println("failed");
	    }
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[7]/div[2]/div[2]")).click();   // pressing ok in test operation
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[3]")).click();      //pressing ok in form
	    Thread.sleep(1000);

	    String li = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[2]/div[3]/div//div[1]")).getText();   // Retrieving created instances
	    String l2[]= li.split("\n");
	    for(String str:l2)
	    {
	    if(str.equals(Read_Xml_File.ReadFile("testData.xml","Data",st,"Name")))
		  return "passed";
	    }
	    return "failed";
	   
		 
    }
    public String exploreObject() throws InterruptedException
    {
    	// Actions action = new Actions(driver);
    	List<WebElement> li=driver.findElements(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[2]/div[3]/div/div/div"));
    	for(WebElement ele:li)
    	{
    		System.out.println(ele.getText());
    		if(ele.getText().equals("localpost"))
    		{
    			
    			ele.click();;
    			//action.doubleClick(ele).perform();
    		//	System.out.println(ele.getText());
    			Thread.sleep(1000);
    			break;
    		}
    	}
    	
    	 driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[2]/div[3]/div/div/div[3]/div[3]")).click();   //ok
    	 Thread.sleep(1000);
    	 System.out.println(driver.findElement(By.xpath("//html/body/div[3]/div[3]/div[1]/div")).getText());
    	 List<WebElement> lis=driver.findElements(By.xpath("//html/body/div[3]/div[3]//div")); //table
    	 for(WebElement l:lis)
    	 {
    		 if(l.getText().equals("Tables"))
    		 {
    			 
    			 System.out.println(l.getText());
    			 l.click();
    			 break;
    	 }
    	 }
    	 Thread.sleep(2000);
    	 List<WebElement> eles=driver.findElements(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[4]/div[4]/div[1]/div/div")); //tables   
    	 for(WebElement ele:eles)
    	 {
    		 System.out.println(eles.size());
			 if(ele.getText().equals("businessentity"))
			 {
				 System.out.println(ele.getText());
				 ele.click();
				 Thread.sleep(1000);
				 break;
			 }
    	 }
    	 Thread.sleep(2000);
    	 driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[4]/div[4]/div[1]/div/div[3]/div[1]")).click();
    	 Thread.sleep(1000);
    	 		
	     driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[4]/div[4]/div[1]/div/div[3]/div[3]")).click(); 
	     Thread.sleep(2000);//click ok image    	 				
	     driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div")).click();               //explore
	     Thread.sleep(1000);
	     String str=driver.findElement(By.xpath("/html/body/div[3]/div[5]/div[2]/div[2]/div/div[1]/div[1]/div/div[1]/div/div")).getText();  // 
	     
	     driver.findElement(By.xpath("/html/body/div[3]/div[8]/div[2]/div[2]/div/div[2]")).click();
	     if(str.split("\n").length>0)
	    	 return "passed";
			 
    	 
    	return "FAILED";
    
    }
    public String customQuery() throws InterruptedException
    {
    	
    	Thread.sleep(1000);
    	 driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[4]/div[3]")).click();
    	 Thread.sleep(2000);
    	 driver.findElement(By.xpath("/html/body/div[3]/div[11]/div[2]/div/div[1]/div[1]/input")).sendKeys(Read_Xml_File.ReadFile("customquery.xml","query","postgres","postgresqueryname"));
    	 driver.findElement(By.xpath("/html/body/div[3]/div[11]/div[2]/div/div[1]/div[2]/div[2]/div[2]/iframe")).sendKeys(Read_Xml_File.ReadFile("customquery.xml","query","postgres","postgresquery"));
    	 driver.findElement(By.xpath("/html/body/div[3]/div[11]/div[2]/div/div[1]/div[2]/div[2]/div[3]/div")).click();
    	 Thread.sleep(1000);
    	 driver.findElement(By.xpath("/html/body/div[3]/div[11]/div[2]/div[2]/div[2]/div[2]")).click();
    	 
		return null;
    	
    }
    
    
	public static void main(String[] args) throws InterruptedException {
		Connection conn=new Connection();
	    BDATesting testobj=new BDATesting(conn);
	    WriteXMl xmlobj=new WriteXMl("data");
		String instancestatus=testobj.instanceValidation("postgres","Postgresql");
		
		xmlobj.createChildTags("connection","postgres",instancestatus);
		
		String explorestatus=testobj.exploreObject();
		xmlobj.createChildTags("explore","postgres",explorestatus);
		String instancestatus1=testobj.instanceValidation("oracle","Oracle");
		xmlobj.createChildTags("connection","oracle",instancestatus1);
		String instancestatus2=testobj.instanceValidation("sqlserver","Ms-SQL");
		xmlobj.createChildTags("connection","sqlserver",instancestatus2);
		String instancestatus3=testobj.instanceValidation("mysql","Mysql");
		xmlobj.createChildTags("connection","mysql",instancestatus3);
		String out=testobj.exploreObject();
		xmlobj.createChildTags("explore","postgres",out);
		xmlobj.XMLFormat();
		testobj.customQuery();
		
	}

}