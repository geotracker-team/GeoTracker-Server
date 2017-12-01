package models;

public class Register {
	private int id;
	private String description;
	private String date;
	private int idUser;
	private int idProject;
	private double latitude;
	private double longitude;
	
	public Register(int id, String description, String date, int idUser, int idProject, double latitude,
			double longitude) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.idUser = idUser;
		this.idProject = idProject;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public int getIdProject() {
		return idProject;
	}
	
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}	
	
}
