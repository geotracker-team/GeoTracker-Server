package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.Database;
import java.sql.Statement;
import models.User;

public class UserAPI {
	
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public void createUser() {
		User user = new User();
		user.setName("user test");
		user.setIdCompany(1);
		databaseInsertion("INSERT INTO users (name, id_company) VALUES " + user.getName() + " AND " + user.getIdCompany());
	}
	public void createUser(User user) {
		String query;

		query = "INSERT INTO project (name, id_company, password)";
		query = query + " VALUES('" + user.getName() + "', " + user.getIdCompany() + "', " + user.getPassword() + ")";
		databaseExecution(query);
	}
	public void updateUser(User user) {
		String query;
		
		query = "UPDATE project " +
		        "SET name = '" + user.getName() + "'" +
		          ", id_company = " + user.getIdCompany() + ", password = " + user.getPassword() + 
		        "WHERE id = " + user.getId();
		databaseExecution(query);
	}
	public void deleteUser(User user) {
		String query;
		
		query = "DELETE FROM project WHERE id = " + user.getId();
		databaseExecution(query);
	}
	public ArrayList<User> getAllUsers() throws SQLException{
		ArrayList<User> users = new ArrayList<>();
		//ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id_company = " + idCompany);
		
		Statement statement = null;	
		ResultSet resultSet = null;
		String query = "SELECT * FROM users";
		//ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id = " + id);
		Database.connect();
		statement = (Statement) Database.connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		while(resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setIdCompany(resultSet.getInt(3));
			user.setPassword(resultSet.getString(4));
			users.add(user);
		}
		statement.close();
		Database.disconnect();
		return users;		
	}
	public User getUserById(int id) throws SQLException
	{
		User user = new User();
		Statement statement = null;	
		ResultSet resultSet = null;
		String query = "SELECT * FROM users WHERE id = '" + id + "'";
		//ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id = " + id);
		Database.connect();
		statement = (Statement) Database.connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		
		if(resultSet.next() == true)
		{
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setIdCompany(resultSet.getInt(3));
			user.setPassword(resultSet.getString(4));
		}
		statement.close();
		Database.disconnect();
		return user;
	}
	public User getUserByName(String name) throws SQLException
	{
		User user = new User();
		Statement statement = null;	
		ResultSet resultSet = null;
		String query = "SELECT * FROM users WHERE name = '" + name + "'";
		//ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id = " + id);
		Database.connect();
		statement = (Statement) Database.connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		
		if(resultSet.next() == true)
		{
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setIdCompany(resultSet.getInt(3));
			user.setPassword(resultSet.getString(4));
		}
		statement.close();
		Database.disconnect();
		return user;
	}
	
	private void databaseExecution(String query) { // remove static
		try {
			Database.connect();
			statement = (Statement) Database.connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println("Error during the query execution: " + e.getErrorCode());
		} finally {
			try {
				statement.close();
				Database.disconnect();
			} catch (Exception e) {
				System.err.println("Error closing the connection: " + e.getMessage());
			}
		}
	}
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

