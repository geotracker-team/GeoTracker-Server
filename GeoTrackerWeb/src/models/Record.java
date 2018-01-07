package models;

import java.util.ArrayList;

public class Record {
	private int id;
	private String description;
	private String date;
	private int userId;
	private int projectId;
	private float latitude;
	private float longitude;
	private ArrayList<ExtraField> otherFields;
	
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
		return userId;
	}
	public void setIdUser(int idUser) {
		this.userId = idUser;
	}
	public int getIdProject() {
		return projectId;
	}
	public void setIdProject(int idProject) {
		this.projectId = idProject;
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
		this.userId = idUser;
		this.projectId = idProject;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public ArrayList<ExtraField> getOtherFields() {
		return otherFields;
	}
	public void setOtherFields(ArrayList<ExtraField> otherFields) {
		this.otherFields = otherFields;
	}
	
	
	
}
