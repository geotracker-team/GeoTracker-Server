package api;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Database;
import models.ExtraField;

public class ExtraFieldAPI {
	private ResultSet resultSet = null;
		
	/*public static void main(String [] args){ test code
		createCompany();
	}*/
	
	
	// INSERTION 
	public ArrayList<ExtraField> getAllExtraFields() throws SQLException{
		ArrayList<ExtraField> extrafields = new ArrayList<>();
		
		Statement statement = null;	
		ResultSet resultSet = null;
		//ResultSet resultSet= databaseSelection("SELECT * FROM register");
		String query = "SELECT * FROM extrafield";
		//ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id = " + id);
		Database.connect();
		statement = (Statement) Database.connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		
		while(resultSet.next()) {
			ExtraField eField = new ExtraField();
			eField.setId(resultSet.getInt(1));
			eField.setIdRegister(resultSet.getInt(2));
			eField.setType(resultSet.getString(3));
			eField.setValue(resultSet.getString(4));		
			extrafields.add(eField);
		}
		statement.close();
		Database.disconnect();
		return extrafields;		
	}
	
	public ArrayList<ExtraField> getAllExtraFieldsById(int id) throws SQLException {
	ArrayList<ExtraField> extrafields = new ArrayList<>();
		
		Statement statement = null;	
		ResultSet resultSet = null;
		//ResultSet resultSet= databaseSelection("SELECT * FROM register");
		String query = "SELECT * FROM extrafield WHERE id_register = " + id;
		//ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id = " + id);
		Database.connect();
		statement = (Statement) Database.connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		
		while(resultSet.next()) {
			ExtraField eField = new ExtraField();
			eField.setId(resultSet.getInt(1));
			eField.setIdRegister(resultSet.getInt(2));
			eField.setType(resultSet.getString(3));
			eField.setValue(resultSet.getString(4));		
			extrafields.add(eField);
		}
		statement.close();
		Database.disconnect();
		return extrafields;		
	}
	
	// PRIVATE METHODS	
	/*private static void databaseInsertion(String query) {  // remove static
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
	*/
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
