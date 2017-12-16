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
import models.User;
import api.API;

@RequestScoped
@Path("")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class WebService {

	private static final API api = new API();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/projects")
	public Project[] getProjectsOfUser() throws SQLException	
	{
		ArrayList<Project> projects = api.getAllProjects(1);
		
		return projects.toArray(new Project[0]);
	}	



	@GET
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
	}
}
