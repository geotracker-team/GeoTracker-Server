package webService;

import java.io.InputStream;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.JResponse;
import models.Record;
import api.API;

@RequestScoped
@Path("")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class WebService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/{name}/{pass}")
	public JResponse login (@PathParam("name") String name, @PathParam("pass") String pass) throws SQLException{
		System.out.println("login");
		API api = new API();		
		return api.authenticate(name, pass);
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/projects/{name}/{pass}")
	public JResponse getProjectsOfUser(@PathParam("name") String name, @PathParam("pass") String pass) throws SQLException	
	{
		API api = new API();
		return api.getAllProjectsByUser(name, pass);	
		
		//TEST CODE
		/*ArrayList<Project> projects = new ArrayList<>();
				
		Project project = new Project();
		project.setName("project test");
		project.setId(1);
		project.setIdCompany(1);
		projects.add(project);
		
		Project project2 = new Project();
		project2.setId(2);
		project2.setName("project test 2");
		project2.setIdCompany(2);
		projects.add(project2);	*/	
		
		//return new JResponse(true, projects);
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/records/{name}/{pass}/{idProject}")
	public JResponse getRecordsOfProject(@PathParam("name") String name, @PathParam("pass") String pass, @PathParam("idProject") int idProject) throws SQLException	
	{
		API api = new API();
		return api.getAllRegistersByProject(name, pass, idProject);
		
		//TEST CODE
		/*ArrayList<Record> records = new ArrayList<>();
		
		Record record = new Record();
		record.setId(1);
		record.setDescription("rest service project description test 1");
		record.setDate("17/12/2017");
		record.setIdUser(1);
		record.setIdProject(1);
		record.setLatitude(1);
		record.setLongitude(1);	
		records.add(record);
		
		Record record2 = new Record();
		record2.setId(2);
		record2.setDescription("rest service project description test 2");
		record2.setDate("17/13/2017");
		record2.setIdUser(2);
		record2.setIdProject(2);
		record2.setLatitude(-9999);
		record2.setLongitude(9999);	
		records.add(record2);
		
		Record record3 = new Record();
		record3.setId(3);
		record3.setDescription("rest service project description test 3");
		record3.setDate("17/14/2017");
		record3.setIdUser(3);
		record3.setIdProject(2);
		record3.setLatitude(-3333);
		record3.setLongitude(3333);	
		records.add(record3);
			
		return records.toArray(new Record[0]);*/
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addRecord/{name}/{pass}")
	public JResponse addRecord(@PathParam("name") String name, @PathParam("pass") String pass) throws SQLException	
	{
		
		Record record = new Record();
		//record.setId(resultSet.getInt(1));
		record.setDescription("rest service description test");
		record.setDate("17/12/2017");
		record.setIdUser(1);
		record.setIdProject(1);
		record.setLatitude(1);
		record.setLongitude(1);	
		
		System.out.println("addRecord");			
		API api = new API();
		return api.createRecord(name, pass, record);

	}	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/editRecord/{name}/{pass}/{idRecord}")
	public boolean editRecord(@PathParam("name") String name, @PathParam("pass") String pass,
			@PathParam("idRecord") int idRecord, InputStream incomingData){
		System.out.println("editRecord");
		return true;
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
