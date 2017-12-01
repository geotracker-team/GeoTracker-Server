package models;

public class ExtraField {
	private int id;
	private int idRegister;
	private String type;
	private String value;
	
	
	public ExtraField(int id, int idRegister, String type, String value) {
		this.id = id;
		this.idRegister = idRegister;
		this.type = type;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdRegister() {
		return idRegister;
	}
	
	public void setIdRegister(int idRegister) {
		this.idRegister = idRegister;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
