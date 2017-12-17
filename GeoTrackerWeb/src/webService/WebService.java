package webService;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Project;
import models.Record;
import api.API;
import api.ProjectsAPI;

@RequestScoped
@Path("")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class WebService {

		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/projects/{name}")
	public Project[] getProjectsOfUser(@PathParam("name") String name) throws SQLException	
	{
		ProjectsAPI api = new ProjectsAPI();
		ArrayList<Project> projects = api.getAllProjectsByUser(name);	
		
		return projects.toArray(new Project[0]);
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/records/{name}")
	public Record[] getRecordsOfUser(@PathParam("name") String name) throws SQLException	
	{
		API api = new API();
		ArrayList<Record> projects = api.getAllRegistersByUserName(name);	
		
		return projects.toArray(new Record[0]);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/record")
	public String addRegister() throws SQLException	
	{
		Record record = new Record();
		//record.setId(resultSet.getInt(1));
		record.setDescription("rest service description test");
		record.setDate("17/12/2017");
		record.setIdUser(3);
		record.setIdProject(3);
		record.setLatitude(1);
		record.setLongitude(1);	
						
		API api = new API();
		api.createRegister(record);;	
		
		return "Record added succesfully";
	}	

	/*@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text")
	public String hello()
	{
		return "Hello World";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json/{username}")
	public User[] helloJSon(@PathParam("username") String userName)	
	{
		User users[] = new User[1];
		users[0] = new User();
		users[0].setId(1);
		users[0].setName(userName);
		users[0].setIdCompany(1);
		users[0].setPassword("123123");
		
		return users;
	}*/
}
