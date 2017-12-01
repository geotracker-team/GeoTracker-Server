package database;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;

public class Database {
	public static Connection connection;
	
	
	public static void connect() {
		if(connection == null){
			String status = "ok";
			try{
				InitialContext cxt = new InitialContext();
				if(cxt!=null)
				{
					DataSource ds = (DataSource) cxt.lookup("java:jboss/PostgresXA");
					if (ds==null) status ="Error lookup";
					else
					{
						connection = ds.getConnection();	
					}
				}
			}catch(Exception e){ 
				e.printStackTrace(); 
				status = e.getMessage();
				System.out.println(status);
			}
		}
	}
	
	public static void disconnect() throws SQLException{
		connection.close();
		connection = null;
	}
	
	
}
