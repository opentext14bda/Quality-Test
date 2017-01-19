package DBAutomachineUtils;

import java.sql.*;

public class DBUtils 
{
	Connection c;
	
	public DBUtils()
	{
	}
	
	public Connection getConnection(String databaseType,String server,String port,String username,String password,String table)
	{
		Connection c = null;
		
		try
		{
			if(databaseType.equals("PostgreSQL"))
			{
				String db = "jdbc:postgresql://"+server+":"+port+"/"+table;
				c = DriverManager.getConnection(db, username, password);
			}
		}
		catch(SQLException e) {}
		return c;
	}
	
	public ResultSet Query(Connection c,String sql_command)
	{
		ResultSet rs = null;
		try
		{
			Statement stmt = c.createStatement();
			rs = stmt.executeQuery(sql_command);
		}
		catch(SQLException e) {}
		return rs;
	}
}
