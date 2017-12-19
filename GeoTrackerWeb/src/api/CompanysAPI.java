package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import database.Database;
import models.Company;

public class CompanysAPI {
	private Statement statement = null;
	private ResultSet resultSet = null;	

	//	PUBLIC METHODS

	public void createCompany() {  // remove static
		Company company = new Company();
		company.setName("company test");
		databaseInsertion("INSERT INTO company (name) VALUES (\'" + company.getName() + "\');");		
	}
	
	public Company getCompanyById(int id) throws SQLException {
		ResultSet resultSet= databaseSelection("SELECT * FROM company WHERE id = " + id);
		Company company = new Company();
		resultSet.next();
		company.setId(id);
		company.setName(resultSet.getString(2));
		System.out.println(company.getId() + " " + company.getName());
		closeConnection();
		return company;
	}
	
	public ArrayList<Company> getAllCompanies() throws SQLException {		
		ArrayList<Company> companies = new ArrayList<>();
		ResultSet resultSet= databaseSelection("SELECT * FROM company");
		while(resultSet.next()) {
			Company company = new Company();
			company.setId(resultSet.getInt(1));
			company.setName(resultSet.getString(2));
			companies.add(company);
		}
		closeConnection();
		
		return companies;		
	}
	
	// PRIVATE METHODS	
	
	private void closeConnection() {
		try {
//			statement = resultSet.getStatement();
			resultSet.close();
			statement.close();
			Database.disconnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	private void databaseInsertion(String query) {  // remove static		
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
				statement.close();				
				Database.disconnect();		
			}catch(Exception e){
				System.err.println("Error closing the connection: " + e.getMessage());
			}	
		}		
	}
	
	
	private ResultSet databaseSelection(String query) {		
		try {
			Database.connect();
			statement = (Statement) Database.connection.createStatement();
			resultSet = statement.executeQuery(query);
		}
		catch(SQLException e){
			System.err.println("Error during the query execution: " + e.getErrorCode());
		}			

		return resultSet;
	}
	
}
