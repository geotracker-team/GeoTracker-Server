package models;

public class Project {
	private int id;
	private String name;
	private int idCompany;
	
	public Project() {
		
	}
	
	public Project(int id, String name, int idCompany) {
		this.id = id;
		this.name = name;
		this.idCompany = idCompany;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}
}
