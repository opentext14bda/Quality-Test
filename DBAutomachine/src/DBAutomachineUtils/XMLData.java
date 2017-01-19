package DBAutomachineUtils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLData 
{
	private Document doc;

    public XMLData()
    {
    	doc = null;
    	
    	File fXmlFile = new File("src/sample.xml");
        
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
    }
    
    public String getXMLData(String...args)
    {
    	String result = null;
		
		XPath xPath =  XPathFactory.newInstance().newXPath();
        String ele = String.format("/data/"+String.join("/",args));

        Node node = null;
        
	    try
	    {
	        node = (Node) xPath.compile(ele).evaluate(doc, XPathConstants.NODE);
	    } 
	    catch (XPathException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    result = node != null ? (node.getTextContent()) : "XMLERROR";
	    return result;
    }
}