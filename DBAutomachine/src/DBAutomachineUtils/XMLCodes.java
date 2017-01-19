package DBAutomachineUtils;

public enum XMLCodes 
{
	URL("WebLink"),
	
	LogIn("LogIn"),
	
	Username("Username"),
	Password("Password"),
	SignIN("(SignIN"),
	
	Data("Data"),
	XPath("XPath"),
	
	Start("Start"),
	Load("Load"),
	Explore("Explore"),
	
	NewDatabase("NewDatabase"),
	SelectDataSourceType("SelectDataSourceType"),
	
	Databases("Databases"),
	CreateNewDatabase("CreateNewDatabase"),
	
	PostgreSQL("PostgreSQL"),
	MySQL("MySQL"),
	Oracle("Oracle"),
	DB2("DB2"),
	NewDataSource("NewDataSource"),
	
	NameXPath("NameXPath"),
	ServerXPath("ServerXPath"),
	UserXPath("UserXPath"),
	PasswordXPath("PasswordXPath"),
	DatabaseXPath("DatabaseXPath"),
	
	Test1("Test1"),
	
	Name("Name"),
	Server("Server"),
	Database("Database"),
	Query("Query");
	
	private final String XMLCode;
	
	private XMLCodes(String str)
    {
    	XMLCode = str;
    }
	
	public String getCode()
	{
		return XMLCode;
	}
}
