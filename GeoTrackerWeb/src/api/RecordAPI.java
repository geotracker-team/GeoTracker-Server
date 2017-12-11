package api;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Database;
import models.Record;
import models.User;

public class RecordAPI {
		
	/*public static void main(String [] args){ test code
		createCompany();
	}*/
	
	
	// INSERTION 
	public ArrayList<Record> getAllRecords() throws SQLException{
		ArrayList<Record> records = new ArrayList<>();
		
		Statement statement = null;	
		ResultSet resultSet = null;
		//ResultSet resultSet= databaseSelection("SELECT * FROM register");
		String query = "SELECT * FROM register";
		//ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id = " + id);
		Database.connect();
		statement = (Statement) Database.connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		
		while(resultSet.next()) {
			Record record = new Record();
			record.setId(resultSet.getInt(1));
			record.setDescription(resultSet.getString(2));
			record.setDate(resultSet.getString(3));
			record.setIdUser(resultSet.getInt(4));
			record.setIdProject(resultSet.getInt(5));
			record.setLatitude(resultSet.getFloat(6));
			record.setLongitude(resultSet.getFloat(7));			
			records.add(record);
		}
		statement.close();
		Database.disconnect();
		return records;		
	}
	
	// PRIVATE METHODS	
	private static void databaseInsertion(String query) {  // remove static
		Statement statement = null;		
		
		try {
			Database.connect();
			statement = (Statement) Database.connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(SQLException e){
			System.err.println("Error during the query execution: " + e.getErrorCode());
		}			
		finally{
			try {
				Database.disconnect();		
				statement.close();
			}catch(Exception e){
				System.err.println("Error closing the connection: " + e.getMessage());
			}	
		}		
	}
	
	private ResultSet databaseSelection(String query) {		
		Statement statement = null;	
		ResultSet resultSet = null;
		
		try {
			Database.connect();
			statement = (Statement) Database.connection.createStatement();
			resultSet = statement.executeQuery(query);
		}
		catch(SQLException e){
			System.err.println("Error during the query execution: " + e.getErrorCode());
		}			
		finally{
			try {		
				Database.disconnect();		
				statement.close();
			}catch(Exception e){
				System.err.println("Error closing the connection: " + e.getMessage());
			}	
		}	
		return resultSet;
	}
}
