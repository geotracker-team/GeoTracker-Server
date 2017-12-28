<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" type="text/javascript" src="js/search.js"></script>
<script language="javascript" type="text/javascript" src="js/record.js"></script>
<link href="css/geotracker.css" rel="stylesheet" type="text/css">
<title>Records</title>
</head>
<%@ page import="java.util.ArrayList, java.util.Iterator, java.util.HashMap" %>
<%@ page import="api.*, models.*" %>
<%


  if((session == null) || (session.getAttribute("username") == null)) {			
		response.sendRedirect("login.jsp");
	}
  
	Record record;
	User user;
	Project project;
	ArrayList<Record> records;
	
	ExtraField efield;
	ArrayList<ExtraField> efields;
	
	ExtraFieldAPI api2 = new ExtraFieldAPI();
	efields = null;
	//efields = api2.getAllExtraFieldsById(2);
	
	RecordAPI api = new RecordAPI();
	records = api.getAllRecords();
	
	UserAPI usersAPI = new UserAPI();
	ArrayList<User> users = usersAPI.getAllUsers();
	
	ProjectsAPI projectsAPI = new ProjectsAPI();
	ArrayList<Project> projects = projectsAPI.getAllProjects();
	
	HashMap<Integer, User> usersMap = new HashMap<Integer, User>();
	for (User u: users) {
		usersMap.put(new Integer(u.getId()), u);
	}	
	HashMap<Integer, Project> projectsMap = new HashMap<Integer, Project>();
	for (Project p: projects) {
		projectsMap.put(new Integer(p.getId()), p);
	}	
%>
<body>

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
		bgcolor="#660033">
		<tr>
			<td colspan="2" nowrap><%@ include file="capcelera.jsp"%>
			</td>
		</tr>
		<tr>
			<td width="20%" valign="top" class="menu_panel">
				<img src="img/blanc.gif" width="10" height="26"> 
				<%@ include file="menu.jsp" %>
				<p>&nbsp;</p>
			</td>
			<td valign="top" bgcolor="#FFFFFF">
			
				<table id="title_frame" width="100%" border="0" align="center"
					cellpadding="2" cellspacing="1">
					<tr>
						<td class="screen_title">Record history</td>
					</tr>
				</table> 
				<form action="#">
				<br/>
				<input type="hidden" id="action" name="action"/> 
				
				  <img src="img/blanc.gif" width="10" height="10">
				
				 <label for="search" id="txt">Type for search(userid or projectid)</label>  <input type="text" name="search" placeholder="search" id="search" onkeyup="Search()"><br><br><br>
				

<table align='center' cellspacing=2 cellpadding=5 id="data_table" border=1 class="data_table">
<tr>

<th class="data_header">ID</th>	
<th class="data_header">Description</th>
<th class="data_header">Date</th>
<th class="data_header">Project</th>
<th class="data_header">User</th>
<th class="data_header">Latitude</th>
<th class="data_header">Longitude</th>
<th width="8%" class="data_header">&nbsp;</th>

</tr>
<%
			if (records != null) {
				Iterator<Record> iterator = records.iterator();
				
				while (iterator.hasNext()) {
					record = (Record) iterator.next();
					user = (User) users.get(new Integer(record.getIdUser()));
					project = (Project) projects.get(new Integer(record.getIdProject()));
					
					
%>

<tr id="row">
<td class="data_col_code"><%=record.getId() %></td>
<td class="data_col_code"><%=record.getDescription() %></td>
<td class="data_col_code"><%=record.getDate() %></td>
<td class="data_col_code">
<input type="hidden" value="<%=record.getIdProject() %>" />	
<%							if (project != null) 
								out.println(project.getName()); 
%>
</td>
<td class="data_col_code">
<input type="hidden" value="<%=record.getIdUser() %>" />	
<%							if (user != null) 
								out.println(user.getName()); 
%>
<td class="data_col_code"><%=record.getLatitude() %></td>
<td class="data_col_code"><%=record.getLongitude() %></td>
<td class="data_col_code"><button id="efield<%=record.getId() %>" onclick="openDetails(<%=record.getId() %>)">Extra<%=record.getId() %></button>
</tr>

<% 		} 
	}
	%>

</table>
</form>
			</td>
		</tr>
	</table>

</body>
</html>