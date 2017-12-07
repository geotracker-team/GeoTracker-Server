package api;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Database;
import models.*;

public class API {
		
	/*public static void main(String [] args){ test code
		createCompany();
	}*/
	private Statement statement = null;
	
	// INSERTION 
	public void createCompany() {  // remove static
		Company company = new Company();
		company.setName("company test");
		databaseInsertion("INSERT INTO company (name) VALUES (\'" + company.getName() + "\');");		
	}
	
	public void createProject() {
		Project project = new Project();
		project.setName("project test");
		project.setIdCompany(1);
		databaseInsertion("INSERT INTO project (name, id_company) VALUES (\'" + project.getName() + "\'," + project.getIdCompany() + ");");
	}	
	
	public void createUser() {
		User user = new User();
		user.setName("user test");
		user.setIdCompany(1);
		databaseInsertion("INSERT INTO users (name, id_company) VALUES (\'" + user.getName() + "\'," + user.getIdCompany() + ");");
	}
	
	public void createAssignation() {
		Assigned assignation = new Assigned();
		assignation.setIdProject(1);
		assignation.setIdUser(1);
		databaseInsertion("INSERT INTO assigned (id_project, id_user) VALUES (" + assignation.getIdProject() + ", " + assignation.getIdUser() + ");");
	}
		
	public void createRegister() {
		Register register = new Register();
		register.setDescription("register test");
		register.setIdProject(1);
		register.setIdUser(1);
		register.setLatitude(0.0);
		register.setLatitude(0.0);

		databaseInsertion("INSERT INTO register (description, id_project, id_user, latitude, longitude, date) VALUES (\'" + register.getDescription() + "\', " + register.getIdProject()
			+ ", " + register.getIdUser() + ", " + register.getLatitude() + ", " + register.getLongitude() + ",\'" + register.getDate() + "\');");
	}
	
	public void createExtraField() {
		ExtraField extra = new ExtraField();
		extra.setIdRegister(1);
		extra.setType("type test");
		extra.setValue("value test");
		databaseInsertion("INSERT INTO extrafield (id_register,type, value) VALUES (" + extra.getIdRegister() + ",\'" + extra.getType() + "\', \'" + extra.getValue() + "\');");
	}
	
	// RETRIEVING BY ID
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
	
	public Project getProjectById(int id) throws SQLException {
		ResultSet resultSet= databaseSelection("SELECT * FROM project WHERE id = " + id);
		Project project = new Project();
		resultSet.next();
		project.setId(resultSet.getInt(1));
		project.setName(resultSet.getString(2));
		project.setIdCompany(resultSet.getInt(3));
		System.out.println(project.getId() + " " + project.getName() + " " + project.getIdCompany());
		closeConnection();
		return project;
	}
	
	public User getUserById(int id) throws SQLException {
		ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id = " + id);
		User user = new User();
		resultSet.next();
		user.setId(resultSet.getInt(1));
		user.setName(resultSet.getString(2));
		user.setIdCompany(resultSet.getInt(3));
		System.out.println(user.getId() + " " + user.getName() + " " + user.getIdCompany());
		closeConnection();
		return user;
	}
	
	public Assigned getAssignedById(int id) throws SQLException {
		ResultSet resultSet= databaseSelection("SELECT * FROM assigned WHERE id = " + id);
		Assigned assigned = new Assigned();
		resultSet.next();
		assigned.setId(resultSet.getInt(1));
		assigned.setIdProject(resultSet.getInt(2));
		assigned.setIdUser(resultSet.getInt(3));
		System.out.println(assigned.getId() + " " + assigned.getIdProject() + " " + assigned.getIdUser());
		closeConnection();
		return assigned;
	}
	
	public Register getRegisterById(int id) throws SQLException {
		ResultSet resultSet= databaseSelection("SELECT * FROM register WHERE id = " + id);
		Register register = new Register();
		resultSet.next();
		register.setId(resultSet.getInt(1));
		register.setDescription(resultSet.getString(2));
		register.setIdProject(resultSet.getInt(3));
		register.setIdUser(resultSet.getInt(4));
		register.setLatitude(resultSet.getDouble(5));
		register.setLongitude(resultSet.getDouble(6));
		register.setDate(resultSet.getString(7));		
		System.out.println(register.getId() + " " + register.getDescription() + " " +  register.getIdProject() + " " + register.getIdUser() + " " +
				register.getLatitude() + " " + register.getLongitude() + " " + register.getDate());
		closeConnection();
		return register;
	}
	
	public ExtraField getExtrarById(int id) throws SQLException {
		ResultSet resultSet= databaseSelection("SELECT * FROM extrafield WHERE id = " + id);
		ExtraField extra = new ExtraField();
		resultSet.next();
		extra.setId(resultSet.getInt(1));
		extra.setIdRegister(resultSet.getInt(2));
		extra.setType(resultSet.getString(3));
		extra.setValue(resultSet.getString(4));
		System.out.println(extra.getId() + " " + extra.getIdRegister() + " " + extra.getType() + " " + extra.getValue());
		closeConnection();
		return extra;
	}	
	
	// RETIRIEVE ALL
	public ArrayList<Company> getAllCompanies() throws SQLException {		
		ArrayList<Company> companies = new ArrayList<>();
		ResultSet resultSet= databaseSelection("SELECT * FROM company");
		while(resultSet.next()) {
			Company company = new Company();
			company.setId(resultSet.getInt(1));
			company.setName(resultSet.getString(2));
			companies.add(company);
			System.out.println(company.getId() + " " + company.getName());
		}
		closeConnection();
		return companies;		
	}
	
	public ArrayList<Project> getAllProjects(int idCompany) throws SQLException{
		ArrayList<Project> projects = new ArrayList<>();
		ResultSet resultSet= databaseSelection("SELECT * FROM project WHERE id_company = " + idCompany);
		while(resultSet.next()) {
			Project project = new Project();
			project.setId(resultSet.getInt(1));
			project.setName(resultSet.getString(2));
			project.setIdCompany(resultSet.getInt(3));
			projects.add(project);
			System.out.println(project.getId() + " " + project.getName() + " " + project.getIdCompany());
		}
		closeConnection();
		return projects;		
	}
	
	public ArrayList<User> getAllUsers(int idCompany) throws SQLException{
		ArrayList<User> users = new ArrayList<>();
		ResultSet resultSet= databaseSelection("SELECT * FROM users WHERE id_company = " + idCompany);
		while(resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setIdCompany(resultSet.getInt(3));
			users.add(user);
			System.out.println(user.getId() + " " + user.getName() + " " + user.getIdCompany());
		}
		closeConnection();
		return users;		
	}
	
	public ArrayList<Assigned> getAllAssignationsByUser(int idUser) throws SQLException{
		return getAllAssignations("SELECT * FROM assigned WHERE id_user = " + idUser);
	}
	
	public ArrayList<Assigned> getAllAssignationsByProject(int idProject) throws SQLException{
		return getAllAssignations("SELECT * FROM assigned WHERE id_project = " + idProject);
	}
	
	private ArrayList<Assigned> getAllAssignations(String query) throws SQLException{
		ArrayList<Assigned> assignations = new ArrayList<>();
		ResultSet resultSet= databaseSelection(query);
		while(resultSet.next()) {
			Assigned assignation = new Assigned();
			assignation.setId(resultSet.getInt(1));
			assignation.setIdProject(resultSet.getInt(2));
			assignation.setIdUser(3);
			assignations.add(assignation);
			System.out.println(assignation.getId() + " " + assignation.getIdProject() + " " + assignation.getIdUser());
		}
		closeConnection();
		return assignations;		
	}
	
	public ArrayList<Register> getAllRegistersByUser(int idUser) throws SQLException{
		return getAllRegisters("SELECT * FROM register WHERE id_user = " + idUser);
	}
	
	public ArrayList<Register> getAllRegistersByProject(int idProject) throws SQLException{
		return getAllRegisters("SELECT * FROM register WHERE id_project = " + idProject);
	}
	
	private ArrayList<Register> getAllRegisters(String query) throws SQLException{
		ArrayList<Register> registers = new ArrayList<>();
		ResultSet resultSet= databaseSelection(query);
		while(resultSet.next()) {
			Register register = new Register();
			register.setId(resultSet.getInt(1));
			register.setDescription(resultSet.getString(2));
			register.setIdProject(resultSet.getInt(3));
			register.setIdUser(resultSet.getInt(4));
			register.setLatitude(resultSet.getDouble(5));
			register.setLongitude(resultSet.getDouble(6));
			register.setDate(resultSet.getString(7));			
			registers.add(register);
			System.out.println(register.getId() + " " + register.getDescription() + " " +  register.getIdProject() + " " + register.getIdUser() + " " +
					register.getLatitude() + " " + register.getLongitude() + " " + register.getDate());
		}
		closeConnection();
		return registers;		
	}
	
	public ArrayList<ExtraField> getAllExtByRegister(int idRegister) throws SQLException{
		ArrayList<ExtraField> extras = new ArrayList<>();
		ResultSet resultSet= databaseSelection("SELECT * FROM extrafield WHERE id_register = " + idRegister);
		while(resultSet.next()) {
			ExtraField extra = new ExtraField();
			extra.setId(resultSet.getInt(1));
			extra.setIdRegister(resultSet.getInt(2));
			extra.setType(resultSet.getString(3));
			extra.setValue(resultSet.getString(4));
			extras.add(extra);
			System.out.println(extra.getId() + " " + extra.getIdRegister() + " " + extra.getType() + " " + extra.getValue());
		}
		closeConnection();
		return extras;		
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
			System.err.println("Error during the query execution: " + e.getMessage());
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
		ResultSet resultSet = null;
		
		try {
			Database.connect();
			statement = (Statement) Database.connection.createStatement();
			resultSet = statement.executeQuery(query);
		}
		catch(SQLException e){
			System.err.println("Error during the query execution: " + e.getMessage());
		}
		
		return resultSet;
	}
	
	private void closeConnection() {
		try {		
			System.out.println("clossing bd");
			Database.disconnect();		
			statement.close();
		}catch(Exception e){
			System.err.println("Error closing the connection: " + e.getMessage());
		}	
	}
}
