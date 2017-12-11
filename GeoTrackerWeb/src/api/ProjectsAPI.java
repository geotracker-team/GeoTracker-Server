package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;
import models.Project;

public class ProjectsAPI {
	private Statement statement = null;
	private ResultSet resultSet = null;

	// PUBLIC METHODS

	public void createProject(Project project) {
		String query;

		query = "INSERT INTO project (name, id_company)";
		query = query + " VALUES('" + project.getName() + "', " + project.getIdCompany() + ")";
		databaseExecution(query);
	}
	
	public void deleteProject(Project project) {
		String query;
		
		query = "DELETE FROM project WHERE id = " + project.getId();
		databaseExecution(query);
	}
	
	public void updateProject(Project project) {
		String query;
		
		query = "UPDATE project " +
		        " SET name = '" + project.getName() + "'" +
		          ", id_company = " + project.getIdCompany() +
		        " WHERE id = " + project.getId();
		databaseExecution(query);
	}

	public ArrayList<Project> getAllProjects() throws SQLException {
		return getAllProjects(0);
	}

	public ArrayList<Project> getAllProjects(int idCompany) throws SQLException {
		ArrayList<Project> projects = new ArrayList<>();
		ResultSet resultSet;
		String query;
		int id;
		String name;

		query = "SELECT * FROM project";
		if (idCompany != 0)
			query = query + " WHERE id_company = " + idCompany;
		query = query + " ORDER BY id";

		resultSet = databaseSelection(query);

		while (resultSet.next()) {
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			idCompany = resultSet.getInt("id_company");

			Project project = new Project();
			project.setId(id);
			project.setName(name);
			project.setIdCompany(idCompany);

			projects.add(project);
		}
		closeConnection();

		return projects;
	}

	public Project getProjectById(int id) throws SQLException {
		Project project = null;
		resultSet = databaseSelection("SELECT * FROM project WHERE id = '" + id + "'");
		if (resultSet.next()) {
			project = new Project();
			project.setId(resultSet.getInt(1));
			project.setName(resultSet.getString(2));
			project.setIdCompany(resultSet.getInt(3));
		}
		return project;
	}

	// PRIVATE METHODS

	private void closeConnection() {
		try {
			// statement = resultSet.getStatement();
			resultSet.close();
			statement.close();
			Database.disconnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
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

	private ResultSet databaseSelection(String query) {
		try {
			Database.connect();
			statement = (Statement) Database.connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Error during the query execution: " + e.getErrorCode());
		}

		return resultSet;
	}

}
