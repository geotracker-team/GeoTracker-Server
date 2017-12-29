package webService;

//import java.io.InputStream;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.JResponse;
import models.Record;
//import models.Record;
import api.API;

@RequestScoped
@Path("")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class WebService {

	private final API api = new API();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/{name}/{pass}")
	public JResponse login (@PathParam("name") String name, @PathParam("pass") String pass) throws SQLException
	{			
		return api.authenticate(name, pass);
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/projects/{name}/{pass}")
	public JResponse getProjectsOfUser(@PathParam("name") String name, @PathParam("pass") String pass) throws SQLException	
	{
		return api.getAllProjectsByUser(name, pass);	
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/records/{name}/{pass}/{idProject}")
	public JResponse getRecordsOfProject(@PathParam("name") String name, @PathParam("pass") String pass, @PathParam("idProject") int idProject) throws SQLException	
	{
		return api.getAllRegistersByProject(name, pass, idProject);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addRecord/{name}/{pass}")
	public JResponse addRecord(@PathParam("name") String name, @PathParam("pass") String pass, Record record) throws SQLException	
	{		
		return api.createRecord(name, pass, record);
	}	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/editRecord/{name}/{pass}/{idRecord}")
	public JResponse editRecord(@PathParam("name") String name, @PathParam("pass") String pass, @PathParam("idRecord") int idRecord, Record record) throws SQLException
	{
		return api.editRecord(name, pass, record, idRecord);
	}
}
