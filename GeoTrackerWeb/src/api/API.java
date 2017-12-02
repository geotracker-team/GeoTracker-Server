package api;

import java.sql.Statement;
import java.sql.SQLException;
import database.Database;
import models.*;

public class API {
		
	public static void main(String [] args) {
		System.out.println("running");
		createCompany();
	}
	
	private static void createCompany() {
		Company company = new Company();
		company.setName("company test");
		databaseInsertion("INSERT INTO company (name) VALUES " + company.getName());		
	}
	
	public void createProject() {
		Project project = new Project();
		project.setName("project test");
		databaseInsertion("INSERT INTO project (name, id_company) VALUES " + project.getName() + " AND " + project.getIdCompany());
	}	
	
	public void createUser() {
		User user = new User();
		user.setName("user test");
		user.setIdCompany(1);
		databaseInsertion("INSERT INTO user (name, id_company) VALUES " + user.getName() + " AND " + user.getIdCompany());
	}
	
	public void createAssignation() {
		Assigned assignation = new Assigned();
		assignation.setIdProject(1);
		assignation.setIdUser(1);
		databaseInsertion("INSERT INTO assigned (id_project, id_user) VALUES " + assignation.getIdProject() + " AND " + assignation.getIdUser());
	}
		
	public void createRegister() {
		Register register = new Register();
		register.setDescription("register test");
		register.setIdProject(1);
		register.setIdUser(1);
		register.setLatitude(0.0);
		register.setLatitude(0.0);

		databaseInsertion("INSERT INTO register (description, id_project, id_user, latitude, longitude) VALUES " + register.getDescription() + " AND " + register.getIdProject()
			+ " AND " + register.getIdUser() + " AND " + register.getLatitude() + " AND " + register.getLongitude());
	}
	
	public void createExtraField() {
		ExtraField extra = new ExtraField();
		extra.setType("type test");
		extra.setValue("value test");
		databaseInsertion("INSERT INTO company (type, value) VALUES " + extra.getType() + " AND " + extra.getValue());
	}
	
	private static void databaseInsertion(String query) {
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
}
