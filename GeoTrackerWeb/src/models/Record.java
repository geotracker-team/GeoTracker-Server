package models;

public class Record {
	private int id;
	private String description;
	private String date;
	private int idUser;
	private int idProject;
	private float latitude;
	private float longitude;
	
	
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
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public Record()
	{
		
	}
	public Record(int id, String description, String date, int idUser, int idProject, float latitude, float longitude) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.idUser = idUser;
		this.idProject = idProject;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	
}
