package models;

public class RecordResponse extends Record{ // deprecated
	private String userName;
	private String projectName;
	
	public RecordResponse() {}
	
	public RecordResponse(String userName, String projectName) {
		this.userName = userName;
		this.projectName = projectName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
