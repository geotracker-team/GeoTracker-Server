package models;

public class User {
	private int id;
	private String name;
	private int idCompany;
	private String password;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		
	}
	public User(int id, String name, int idCompany, String password) {
		super();
		this.id = id;
		this.name = name;
		this.idCompany = idCompany;
		this.password = password;
	}
	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.idCompany = 1;
		this.password = password;
	}
	
	
	
}
