import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import DBAutomachineUtils.*;

class ExtraFunctions
{
	private static AutomachineUtils amutils;
	private static DBUtils dbutils;
	private static XMLData xml;
	
	
	public ExtraFunctions()
	{
		amutils = new AutomachineUtils();
		dbutils = new DBUtils();
		xml = new XMLData();
	}
	
	public void loginToOpenTextBDA()
	{
		amutils.loadWebLink(xml.getXMLData("WebLink"));
		
		amutils.utilSendKeys(xml.getXMLData("LogIn","Username","XPath"),xml.getXMLData("LogIn","Username","Data"));
		amutils.utilSendKeys(xml.getXMLData("LogIn","Password","XPath"),xml.getXMLData("LogIn","Password","Data"));
		
		amutils.utilClick(xml.getXMLData("LogIn","SignIN","XPath"));
	}
	
	public void addSQLDatabaseToOpenTextBDA(String databaseType,String details)
	{
		amutils.utilClick(xml.getXMLData("Load","XPath"));
		amutils.utilClick(xml.getXMLData("Load","NewDatabase","XPath"));
		amutils.utilClick(xml.getXMLData("Load","NewDatabase","Database",databaseType));
		
		amutils.utilSendKeys(xml.getXMLData("Load","NewDatabase","Data","Name"), xml.getXMLData(details,"Name"));
		amutils.utilSendKeys(xml.getXMLData("Load","NewDatabase","Data","Server"), xml.getXMLData(details,"Server"));
		amutils.utilSendKeys(xml.getXMLData("Load","NewDatabase","Data","Username"), xml.getXMLData(details,"Username"));
		amutils.utilSendKeys(xml.getXMLData("Load","NewDatabase","Data","Password"), xml.getXMLData(details,"Password"));
		amutils.utilSendKeys(xml.getXMLData("Load","NewDatabase","Data","Database"), xml.getXMLData(details,"Database"));
		
		amutils.utilClick(xml.getXMLData("Load","NewDatabase","Create"));
	}
	
	public String getDatabaseXpath(String details)
	{
		for(WebElement e : amutils.getElements(xml.getXMLData("Load","Databases")))
		{
			if(e.getText().equals(xml.getXMLData(details,"Name")))
				return e.getAttribute("xpath");
		}
		System.out.println(amutils.getElements(xml.getXMLData("Load","Databases")).size());
		return null;
	}
	
	public void selectSQLDatabaseFromOpenTextBDA(String databaseType,String details)
	{
		amutils.utilClick(xml.getXMLData("Load","XPath"));
		amutils.DragAndDrop(getDatabaseXpath(details), xml.getXMLData("Load","Base"));
	}
	
	public int rowCountFromDatabase(String databaseType,String details)
	{
		int rowCount = 0;
		
		Connection c = dbutils.getConnection(databaseType, xml.getXMLData(details,"Server"), xml.getXMLData(details,"Port"), xml.getXMLData(details,"Username"), xml.getXMLData(details,"Password"), xml.getXMLData(details,"Database"));
		
		if(c==null)
			System.out.println("Unable to Create Connection");
		else
		{
			ResultSet rs = dbutils.Query(c, "select * from " + xml.getXMLData(details,"Table"));
			
			try
			{
				while(rs.next())
				{
					rowCount += 1;
				}
			}
			catch(SQLException e) {e.printStackTrace();}
		}
		return rowCount;
	}
	
	public int rowCountFromOpentextBDA(String databaseType,String details)
	{
		loginToOpenTextBDA();
		selectSQLDatabaseFromOpenTextBDA(databaseType,details);
		
		
		return -1;
	}
	
	public void rowCountCheck(String databaseType,String details)
	{
		//assert rowCountFromDatabase(databaseType,details)==rowCountFromOpentextBDA(databaseType,details) : "Testcase Failed";
		System.out.println(rowCountFromDatabase(databaseType,details));
		System.out.println(rowCountFromOpentextBDA(databaseType,details));
	}
}

class SolveChallange extends ExtraFunctions
{
	public void F1()
	{
		rowCountCheck("PostgreSQL","Test1");
	}
	
	public void F2()
	{
		
	}
	
	public void F3()
	{
		
	}
	
	public void F4()
	{
		
	}
}

public class DBAutomachine 
{
	public static void main(String[] args)
	{
		SolveChallange obj = new SolveChallange();
		
		obj.F1();
		
		obj.F2();
		
		obj.F3();
		
		obj.F4();
	}
}