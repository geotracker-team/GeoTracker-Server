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
		createAssignation(assignation);
	}

	public void createAssignation(Assigned assignation) {
		databaseInsertion("INSERT INTO assigned (id_project, id_user) "
				+ " VALUES (" + assignation.getIdProject() + ", " + assignation.getIdUser() + ");");
	}
	
	public void deleteAssignation(Assigned assignation) {
		databaseInsertion("DELETE FROM assigned WHERE id = " + assignation.getId());
	}
	
	public JResponse createRecord(String userName, String password, Record record) throws SQLException {
		JResponse response;
		JResponse jr = authenticate(userName, password);
		if(jr.isOk()) {
			if(record != null) {
				ResultSet resultSet = databaseSelection("SELECT id FROM users WHERE name = \'" + userName + "\';");
				resultSet.next();
				String query = "INSERT INTO register (description, date, id_project, id_user, latitiude, longitude) VALUES (\'" + record.getDescription() + "\',"
						+ " \'" + record.getDate() + "\', " + record.getIdProject() + ", " + resultSet.getInt(1) + ", " +record.getLatitude()
						+ "," + record.getLongitude() + ");";
				
				response = databaseInsertion(query);
				resultSet = databaseSelection("SELECT MAX(id) FROM register");
				resultSet.next();
				int idRegister = resultSet.getInt(1);
				response.setExtra(idRegister);
				if (response.isOk()) {
					JResponse responseExtra;
					for(ExtraField extra : record.getOtherFields()) {
						responseExtra = databaseInsertion("INSERT INTO extrafield (id_register, type, value, title) VALUES (" + idRegister 
							+ ", \'" + extra.getType() + "\', \'" + extra.getValue() + "\', \'" + extra.getTitle() + "\')");
						if(!response.isOk()) {
							return responseExtra;
						}
					}					
					return response;
				}				
			}
			return new JResponse(false, "Creation rejected, null record recieved");			
		}
		return jr; 
	}
	
	public JResponse editRecord(String userName, String password, Record record, int idRecord) throws SQLException {
		JResponse response = null;
		JResponse jr = authenticate(userName, password);
		if(jr.isOk()) {
			ResultSet rs = databaseSelection("SELECT COUNT(1) FROM register WHERE id = "+ idRecord); 
			rs.next();
			if(rs.getBoolean(1)) {
				if(record != null) {
					ResultSet resultSet = databaseSelection("SELECT id FROM users WHERE name = \'" + userName + "\';");
					resultSet.next();
					String query = "UPDATE register SET description = \'" + record.getDescription() + "\' , date = \'" + record.getDate() 
							+ "\', id_project = " + record.getIdProject() + ", id_user = " + resultSet.getInt(1) + ", latitiude = " 
							+ record.getLatitude() + ", longitude = " + record.getLongitude() + " WHERE id = " + idRecord;
					
					response = databaseInsertion(query);
					if (response.isOk()) {
						for(ExtraField extra : record.getOtherFields()) {
							response = databaseInsertion("UPDATE extrafield SET type = \'" + extra.getType() + "\', value = \'" +
									extra.getValue() + "\' WHERE title = \'" + extra.getTitle() +"\' AND id_register = " + idRecord);
							if(!response.isOk()) break;
						}						
					}
					return response;
				}
				return new JResponse(false, "Edition rejected, null record recieved");				
			}
			return new JResponse(false, "The project does not exist");
		}
		return jr; 
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
	
	public JResponse authenticate(String userName, String password) throws SQLException {		
		ResultSet resultSet= databaseSelection("SELECT password, is_manager FROM users WHERE name = \'" + userName + "\'");		
		String message = "";
		if(resultSet.next()) {
			System.out.println(resultSet.getString(1) + " " + resultSet.getBoolean(2));
			if(password.equals(resultSet.getString(1))) {
				return new JResponse(true, resultSet.getBoolean(2));				
			}
			else {
				message = "User incorrectly authenticated";
			}
		}
		else {
			message = "The user " + userName + " does not exist";
		}	
		closeConnection();
		return new JResponse(false, message); 
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
		register.setId(resultSet.getInt("id"));
		register.setDescription(resultSet.getString("description"));
		register.setIdProject(resultSet.getInt("id_project"));
		register.setIdUser(resultSet.getInt("id_user"));
		register.setLatitude(resultSet.getDouble("latitiude"));
		register.setLongitude(resultSet.getDouble("longitude"));
		register.setDate(resultSet.getString("date"));		
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
	
	public JResponse getAllProjectsByUser(String userName, String password) throws SQLException{		
		JResponse jr = authenticate(userName, password);
		if(jr.isOk()) {
			ArrayList<Project> projects = new ArrayList<>();
			ResultSet resultSet= databaseSelection("SELECT p.* FROM project p, assigned a, users u"
					+ " WHERE u.name = \'" + userName + "\' AND u.id = a.id_user AND a.id_project = p.id");
			while(resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt(1));
				project.setName(resultSet.getString(2));
				project.setIdCompany(resultSet.getInt(3));
				projects.add(project);
				System.out.println(project.getId() + " " + project.getName() + " " + project.getIdCompany());
			}
			jr.setExtra(projects);
			closeConnection();
		}		
		return jr;		
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
			assignation.setIdUser(resultSet.getInt(3));
			assignations.add(assignation);
			System.out.println(assignation.getId() + " " + assignation.getIdProject() + " " + assignation.getIdUser());
		}
		closeConnection();
		return assignations;		
	}
	
	public ArrayList<Record> getAllRegistersByUser(int idUser) throws SQLException{
		return getAllRegisters("SELECT * FROM register WHERE id_user = " + idUser);
	}
	
	public ArrayList<Record> getAllRegistersByUserName(String name) throws SQLException{
		return getAllRegisters("SELECT r.* FROM register r, users u WHERE u.name = \'" + name +
				"\' AND u.id = r.id_user");
	}
	
	public JResponse getAllRegistersByProject(String userName, String password, int idProject) throws SQLException{
		JResponse jr = authenticate(userName, password);
		if(jr.isOk()) {
			ResultSet rs = databaseSelection("SELECT COUNT(1) FROM project WHERE id = "+ idProject); 
			rs.next();
			if(rs.getBoolean(1)) {
				rs.close();
			
				String query = "SELECT r.*, u.name username, p.name projectname FROM register r, project p, users u WHERE r.id_project = "
						+ idProject +	" AND u.id = r.id_user AND p.id = r.id_project";
				ArrayList<RecordResponse> records = getAllRegistersResponse(query);
				if(records.isEmpty()) {
					rs = databaseSelection("SELECT name FROM project WHERE id = "+ idProject + " GROUP BY name"); 
					rs.next();
					jr.setExtra("The project " + rs.getString(1) + " does not have any record yet");
					jr.setOk(false);
				}
				else {				
					ArrayList<RecordResponse> responses = new ArrayList<>();
					for(RecordResponse r : records) {
						r.setOtherFields(getAllExtByRegister(r.getId()));
						responses.add(r);
					}				
					jr.setExtra(responses);				
				}
			}
			else {
				jr.setExtra("This project does not exist");
				jr.setOk(false);
			}			
		}
		return jr;
	}
	
	private ArrayList<RecordResponse> getAllRegistersResponse(String query) throws SQLException{
		ArrayList<RecordResponse> records = new ArrayList<>();
		ResultSet resultSet= databaseSelection(query);
		while(resultSet.next()) {
			RecordResponse record = new RecordResponse();
			record.setId(resultSet.getInt("id"));
			record.setDescription(resultSet.getString("description"));
			record.setDate(resultSet.getString("date"));		
			record.setIdProject(resultSet.getInt("id_project"));
			record.setIdUser(resultSet.getInt("id_user"));
			record.setLatitude(resultSet.getFloat("latitiude"));
			record.setLongitude(resultSet.getFloat("longitude"));	
			record.setUserName(resultSet.getString("username"));
			record.setProjectName(resultSet.getString("projectname"));
			records.add(record);
			System.out.println(record.getId() + " " + record.getDescription() + " " +  record.getIdProject() + " " + record.getIdUser() + " " +
					record.getLatitude() + " " + record.getLongitude() + " " + record.getDate());
		}
		closeConnection();
		return records;		
	}
	
	private ArrayList<Record> getAllRegisters(String query) throws SQLException{
		ArrayList<Record> records = new ArrayList<>();
		ResultSet resultSet= databaseSelection(query);
		while(resultSet.next()) {
			Record record = new Record();
			record.setId(resultSet.getInt("id"));
			record.setDescription(resultSet.getString("description"));
			record.setDate(resultSet.getString("date"));		
			record.setIdProject(resultSet.getInt("id_project"));
			record.setIdUser(resultSet.getInt("id_user"));
			record.setLatitude(resultSet.getFloat("latitiude"));
			record.setLongitude(resultSet.getFloat("longitude"));	
			records.add(record);
		}
		closeConnection();
		return records;		
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
			extra.setTitle(resultSet.getString(5));
			extras.add(extra);
			System.out.println(extra.getId() + " " + extra.getIdRegister() + " " + extra.getType() + " " + extra.getValue());
		}
		closeConnection();
		return extras;		
	}
	
	// PRIVATE METHODS	
	private JResponse databaseInsertion(String query) {  // remove static
		Statement statement = null;		
		boolean isOk = false;
		String extra = "";
		
		try {
			Database.connect();
			statement = (Statement) Database.connection.createStatement();
			statement.executeUpdate(query);
			isOk = true;
			extra = "Insertion/update done correctly";
		}
		catch(SQLException e){
			extra = "Error during the query execution: " + e.getMessage();
		}
		finally{
			try {
				Database.disconnect();		
				statement.close();
			}catch(Exception e){
				extra = "Error closing the connection: " + e.getMessage();
			}	
		}	
		System.err.println(extra);		
		
		return new JResponse(isOk, extra);
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
