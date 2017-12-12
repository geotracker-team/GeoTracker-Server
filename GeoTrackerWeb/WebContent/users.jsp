<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>GeoTracker - Users</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/geotracker.css" rel="stylesheet" type="text/css">
	<script language="javascript" type="text/javascript" src="js/mm_functions.js"></script>
	<script language="javascript" type="text/javascript" src="js/users.js"></script>
</head>
<%@ page import="java.util.ArrayList, java.util.Iterator" %>
<%@ page import="api.UserAPI, models.User" %>
<%
	User user;
	ArrayList<User> users;
	
	UserAPI api = new UserAPI();
	users = api.getAllUsers();
//	users = null;
%>
<body leftmargin="0" topmargin="0" onLoad="MM_preloadImages('img/new.gif','img/new_up.gif','img/save.gif','img/save_up.gif','img/delete16_up.gif','img/delete32.gif','img/delete32_up.gif','img/edit16.gif','img/edit16_up.gif','img/edit32.gif','img/edit32_up.gif')">
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
						<td class="screen_title">Users</td>
					</tr>
				</table> 
				<form action="UserServlet" id="form_data">
				<br/>
				<input type="hidden" id="action" name="action"/> 
				
				  <img src="img/blanc.gif" width="10" height="10">
				
				
				


				
<table align='center' cellspacing=2 cellpadding=5 id="data_table" border=1 class="data_table">
<tr>
<th>Code</th>
<th>Name</th>
<th>Password</th>
</tr>
<%
			if (users != null) {
				Iterator<User> iterator = users.iterator();
				while (iterator.hasNext()) {
					user = (User) iterator.next();
%>

<tr id="row<%=user.getId() %>">
<td class="data_col_code" id="name_row<%=user.getId() %>"><%=user.getId() %></td>
<td class="data_col_code" id="nameRow<%=user.getId() %>"><%=user.getName() %></td>
<td class="data_col_code" id="passRow<%=user.getId() %>"><%=user.getPassword() %></td>
<td class="data_col_code"><img src="img/edit32.gif" width="16" height="16" onclick="edit_row(<%=user.getId() %>)" id="edit_button<%=user.getId() %>"></td>
<td class="data_col_code"><img src="img/delete32.gif" width="16" height="16" onclick="deleteProject(<%=user.getId() %>)" id="delete_button<%=user.getId() %>"></td>
</tr>

<% 		} 
	}
	%>
<tr>
<td class="data_col_code"><input type="text" id="new_code"></td>
<td class="data_col_code"><input type="text" id="new_name"></td>
<td class="data_col_code"><input type="text" id="new_pass"></td>
<td class="data_col_code"><img src="img/new16.gif" class="add" onclick="add();" width="16" height="16"></td>
</tr>
</table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>