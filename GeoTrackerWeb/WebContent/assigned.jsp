<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>GeoTracker - Projects detail</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link href="css/geotracker.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="js/mm_functions.js" ></script>
	<script language="javascript" type="text/javascript" src="js/project_detail.js" ></script>
</head>

<%@ page import="java.util.ArrayList, java.util.Iterator, java.util.HashMap" %>
<%@ page import="api.*, models.*" %>
<%	
	int idProject;
	String projectName;
	Assigned assigned;
	User user;
	String userName;
	
	if (request.getParameter("id") != null)
		idProject = Integer.parseInt(request.getParameter("id"));
	else 
		idProject = 0;
	
	ProjectsAPI projectsApi = new ProjectsAPI();
	Project project = projectsApi.getProjectById(idProject);
	if (project != null)
		projectName = project.getName();
	else
		projectName = "";
	
	API api = new API();
	ArrayList<Assigned> assigneds = api.getAllAssignationsByProject(idProject);
	
	UserAPI userAPI = new UserAPI();
	ArrayList<User> users = userAPI.getAllUsers();
	
	HashMap<Integer, User> usersMap = new HashMap<Integer, User>();
	for (User u: users) {
		usersMap.put(new Integer(u.getId()), u);
	}	
%>
<body onload="MM_preloadImages('img/edit24_up.gif','img/save24_up.gif','img/delete24_up.gif')">
	<table id="title_frame" width="100%" border="0" align="center"
		cellpadding="2" cellspacing="1">
		<tr>
			<td class="screen_title">Project details</td>
		</tr>
	</table> 
	<form action="AssignedServlet" method="post" id="form_data" name="form_data">
		<input type="hidden" id="action" name="action" value="" /> 
		<br/>
		<table width="75%" border="0" align="center">
			<tr>
				<td>
					<table width="100%" cellpadding="10" cellspacing="0" class="search_frame">
						<tr >
							<td width="10%" nowrap="nowrap" class="seach_label">Project name:</td>
							<td nowrap="nowrap" class="search_input"><b><%=projectName %></b></td>
					  </tr>
				  </table>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>
				<table align='center' cellspacing="2" cellpadding="5" id="data_table" border="0" class="data_table" width="100%">
					<tr>
						<th>User</th>
						<th width="5%"></th>
					</tr>		
<%
			if (assigneds != null) {
				Iterator<Assigned> iterator = assigneds.iterator();
				while (iterator.hasNext()) {
					assigned = (Assigned) iterator.next();
					user = (User) usersMap.get(new Integer(assigned.getIdUser()));
					if (user != null)
						userName = user.getName();
					else
						userName = "";
%>
					<tr>
						<td><%=userName %></td>
						<td>
							<a  id="btnDelete<%=assigned.getId() %>" name="btnDelete<%=assigned.getId() %>"
								onclick="return deleteProject(<%=assigned.getId() %>);" 
								onmouseout="MM_swapImgRestore()" style="cursor: pointer;"
								onmouseover="MM_swapImage('btnDeleteImg<%=assigned.getId() %>','','img/delete24_up.gif',1)">
								<img src="img/delete24.gif" alt="Delete user" id="btnDeleteImg<%=assigned.getId() %>"
								 name="btnDeleteImg<%=assigned.getId() %>" width="24" height="24" border="0" /></a>						
						</td>
					</tr>
<% 		} 
	}
	%>					
					<tr>
					  <td>
						  <select id="newUser" name="newUser" style="width:200px">
	                        <option value="0" selected="selected">(Select user)</option>
	                      </select>
                      </td>
					  <td>
					  		<a onclick="return add();" style="cursor: pointer;"
								onmouseout="MM_swapImgRestore()" 
								onmouseover="MM_swapImage('btnNew','','img/new24_up.gif',1)">
								<img src="img/new24.gif" alt="New user" name="btnNew" width="24" height="24" border="0" /></a>
					  </td>
				  </tr>
				  
				</table>
				</td>
			</tr>
	  </table>
		
	
		<p>&nbsp;</p>
	</form>
</body>
</html>
