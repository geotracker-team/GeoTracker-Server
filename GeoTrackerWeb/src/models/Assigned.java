package models;

public class Assigned {
	private int id;
	private int idProject;
	private int idUser;
	
	public Assigned(int id, int idProject, int idUser) {
		this.id = id;
		this.idProject = idProject;
		this.idUser = idUser;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdProject() {
		return idProject;
	}
	
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}	
	
}
