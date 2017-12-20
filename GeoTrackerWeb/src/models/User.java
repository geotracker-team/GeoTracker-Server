package models;

public class User {
	private int id;
	private String name;
	private int idCompany;
	private String password;
	private boolean manager;
	
	
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
	
	public boolean IsManager(){return manager;}

	public void setManager(boolean manager){this.manager = manager;}
	
	public int getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		
	}
	public User(int id, String name, int idCompany, String password, boolean manager) {
		super();
		this.id = id;
		this.name = name;
		this.idCompany = idCompany;
		this.password = password;
		this.manager = manager;
	}
	
	
	
}
